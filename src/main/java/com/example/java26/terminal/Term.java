package com.example.java26.terminal;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Term - ett litet "one class"-bibliotek för konsolen (ANSI/VT-sekvenser).
 *
 * Funktioner:
 *  - Färgad/stilad text (16 färger, RGB/truecolor, bakgrund, fet, understruken ...)
 *  - Skriva över aktuell rad (t.ex. progress bar, spinner)
 *  - Flytta markören till valfri position och skriva där
 *  - Klickbara länkar (OSC 8)
 *
 * Fungerar i Windows Terminal, macOS Terminal/iTerm2, de flesta Linux-terminaler
 * och VS Code-terminalen. Klickbara länkar kräver en terminal med OSC 8-stöd
 * (Windows Terminal, iTerm2, GNOME Terminal, kitty, VS Code m.fl.).
 *
 * Körs programmet i en IDE-konsol (IntelliJ, Eclipse, NetBeans ...) upptäcks det
 * automatiskt: färger och "skriv över rad" används fortfarande, men markörflytt
 * och OSC 8-länkar stängs av så att inga konstiga tecken visas (länkar skrivs då
 * som "text (url)"). Se setAdvanced() för att styra detta manuellt.
 * Sätt miljövariabeln NO_COLOR för att stänga av färger helt.
 *
 * Rader och kolumner är 1-baserade (1,1 = övre vänstra hörnet).
 */
public final class Term {
    private Term() {}

    private static final String ESC = "\u001b";
    private static final String CSI = ESC + "[";

    /** Färger/stilar (SGR) – stöds även av de flesta IDE-konsoler. */
    private static volatile boolean enabled = System.getenv("NO_COLOR") == null;

    /**
     * Avancerade sekvenser (markörflytt, rensa, länkar). Slås på automatiskt bara
     * om programmet körs i en riktig terminal. IDE-konsoler (IntelliJ, Eclipse ...)
     * stöder oftast inte dessa, och då används reservlösningar istället.
     */
    private static volatile boolean advanced = detectTerminal();

    private static int lastLen = 0;

    private static boolean detectTerminal() {
        java.io.Console c = System.console();
        if (c == null) return false;           // utdata/indata är inte kopplad till en terminal
        try {                                  // Java 22+: Console.isTerminal()
            return (Boolean) java.io.Console.class.getMethod("isTerminal").invoke(c);
        } catch (Exception e) {
            return true;                       // äldre Java: console != null betyder terminal
        }
    }

    private static String stripAnsi(String s) {
        return s.replaceAll("\u001b\\[[0-9;?]*[A-Za-z]|\u001b\\][^\u001b]*\u001b\\\\", "");
    }

    /** Konsolens ursprungliga kodsida på Windows (återställs vid avslut), -1 = oförändrad. */
    private static int oldCodePage = -1;

    private static final PrintStream OUT = createOut();

    private static boolean isWindows() {
        return System.getProperty("os.name", "").toLowerCase().contains("win");
    }

    /**
     * Skapar utströmmen. I en riktig Windows-konsol byts kodsidan till UTF-8 (65001)
     * så att å/ä/ö och blocktecken visas rätt; den ursprungliga kodsidan sätts tillbaka
     * när programmet avslutas. Går det inte används konsolens egen teckenkodning.
     */
    private static PrintStream createOut() {
        java.nio.charset.Charset cs = StandardCharsets.UTF_8;
        if (advanced && isWindows()) {
            try {
                Process p = new ProcessBuilder("cmd", "/c", "chcp").redirectErrorStream(true).start();
                String o = new String(p.getInputStream().readAllBytes());
                p.waitFor();
                java.util.regex.Matcher m = java.util.regex.Pattern.compile("(\\d+)").matcher(o);
                if (m.find()) oldCodePage = Integer.parseInt(m.group(1));
                if (oldCodePage > 0 && oldCodePage != 65001) {
                    int rc = new ProcessBuilder("cmd", "/c", "chcp 65001 >nul").inheritIO().start().waitFor();
                    if (rc != 0) oldCodePage = -1;
                } else {
                    oldCodePage = -1;
                }
            } catch (Exception e) {
                oldCodePage = -1;
                try {
                    cs = java.nio.charset.Charset.forName(System.getProperty("stdout.encoding",
                            java.nio.charset.Charset.defaultCharset().name()));
                } catch (Exception ignored) { }
            }
        }
        return new PrintStream(
                new BufferedOutputStream(new FileOutputStream(FileDescriptor.out), 8192), false, cs);
    }

    static {
        // Windows: slå på VT-läge i konsolen (bästa försök, behövs i äldre conhost).
        if (advanced && isWindows()) {
            try {
                new ProcessBuilder("cmd", "/c", "").inheritIO().start().waitFor();
            } catch (Exception ignored) { }
        }
        // Återställ stil, markör och (på Windows) kodsida när programmet avslutas.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (enabled) OUT.print(CSI + "0m");
            if (advanced) OUT.print(CSI + "?25h");
            OUT.flush();
            if (oldCodePage > 0) {
                try {
                    new ProcessBuilder("cmd", "/c", "chcp " + oldCodePage + " >nul").inheritIO().start().waitFor();
                } catch (Exception ignored) { }
            }
        }));
    }

    // ------------------------------------------------------------------ typer

    public enum Color {
        BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE,
        BRIGHT_BLACK, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_YELLOW,
        BRIGHT_BLUE, BRIGHT_MAGENTA, BRIGHT_CYAN, BRIGHT_WHITE;

        int fg() { return ordinal() < 8 ? 30 + ordinal() : 90 + ordinal() - 8; }
        int bg() { return fg() + 10; }
    }

    public enum Style {
        BOLD(1), DIM(2), ITALIC(3), UNDERLINE(4), BLINK(5), INVERSE(7), STRIKE(9);
        final int code;
        Style(int code) { this.code = code; }
    }

    // ------------------------------------------------- formatering (returnerar String)

    /** Allmän formatering. fg/bg får vara null. */
    public static String fmt(String text, Color fg, Color bg, Style... styles) {
        if (!enabled) return text;
        List<String> codes = new ArrayList<>();
        for (Style s : styles) codes.add(String.valueOf(s.code));
        if (fg != null) codes.add(String.valueOf(fg.fg()));
        if (bg != null) codes.add(String.valueOf(bg.bg()));
        return wrap(text, String.join(";", codes));
    }

    public static String color(String text, Color fg) { return fmt(text, fg, null); }

    public static String bold(String text) { return fmt(text, null, null, Style.BOLD); }

    /** Textfärg i valfri RGB (truecolor). */
    public static String rgb(String text, int r, int g, int b) {
        return enabled ? wrap(text, "38;2;" + clamp(r) + ";" + clamp(g) + ";" + clamp(b)) : text;
    }

    /** Bakgrundsfärg i valfri RGB (truecolor). */
    public static String bgRgb(String text, int r, int g, int b) {
        return enabled ? wrap(text, "48;2;" + clamp(r) + ";" + clamp(g) + ";" + clamp(b)) : text;
    }

    /**
     * Klickbar länk (OSC 8). I terminaler utan stöd (t.ex. IDE-konsoler) skrivs
     * "text (url)" – IDE:er gör oftast url:en klickbar själva.
     */
    public static String link(String url, String text) {
        if (!enabled || !advanced) return text + " (" + url + ")";
        String safe = url.replaceAll("[\\x00-\\x1f\\x7f]", "");
        return ESC + "]8;;" + safe + ESC + "\\" + text + ESC + "]8;;" + ESC + "\\";
    }

    private static String wrap(String text, String codes) {
        return codes.isEmpty() ? text : CSI + codes + "m" + text + CSI + "0m";
    }

    private static int clamp(int v) { return Math.max(0, Math.min(255, v)); }

    // ----------------------------------------------------------------- utskrift

    public static void print(String s)   { OUT.print(s); OUT.flush(); }
    public static void println(String s) { OUT.println(s); OUT.flush(); }
    public static void println()         { OUT.println(); OUT.flush(); }
    public static void printf(String format, Object... args) { print(String.format(format, args)); }

    public static void print(String text, Color fg)   { print(color(text, fg)); }
    public static void println(String text, Color fg) { println(color(text, fg)); }

    public static void printLink(String url, String text) { print(link(url, text)); }

    /** Skriver över hela aktuella raden (ingen radbrytning). */
    public static void overwrite(String text) {
        int visible = stripAnsi(text).length();
        if (advanced) {
            print("\r" + CSI + "2K" + text);
        } else {
            // Ingen "rensa rad": fyll ut med mellanslag så gammal text försvinner.
            print("\r" + text + " ".repeat(Math.max(0, lastLen - visible)));
        }
        lastLen = visible;
    }

    /** Enkel progress bar på aktuell rad. fraction: 0.0 - 1.0. Anropa println() när du är klar. */
    public static void progress(String label, double fraction, int width) {
        fraction = Math.max(0, Math.min(1, fraction));
        int filled = (int) Math.round(fraction * width);
        String bar = fmt("█".repeat(filled), Color.GREEN, null)
                + fmt("░".repeat(width - filled), Color.BRIGHT_BLACK, null);
        overwrite(label + " [" + bar + "] " + String.format("%3d%%", Math.round(fraction * 100)));
    }

    // -------------------------------------------------------------------- markör

    /** Flytta markören till (rad, kolumn), 1-baserat. */
    public static void moveTo(int row, int col) { csi(Math.max(1, row) + ";" + Math.max(1, col) + "H"); }

    /** Flytta markören till kolumn på aktuell rad. */
    public static void column(int col)  { csi(Math.max(1, col) + "G"); }

    public static void up(int n)        { if (n > 0) csi(n + "A"); }
    public static void down(int n)      { if (n > 0) csi(n + "B"); }
    public static void right(int n)     { if (n > 0) csi(n + "C"); }
    public static void left(int n)      { if (n > 0) csi(n + "D"); }

    /** Spara/återställ markörposition. */
    public static void savePos()        { raw(ESC + "7"); }
    public static void restorePos()     { raw(ESC + "8"); }

    public static void hideCursor()     { csi("?25l"); }
    public static void showCursor()     { csi("?25h"); }

    /** Skriv text på en viss position och återställ sedan markören dit den var. */
    public static void printAt(int row, int col, String text) {
        if (!advanced) { println(text); return; }   // reserv: skriv bara ut texten på en ny rad
        savePos();
        moveTo(row, col);
        print(text);
        restorePos();
    }

    /** Som printAt, men markören blir kvar efter texten. */
    public static void printAtKeep(int row, int col, String text) {
        if (!advanced) { println(text); return; }
        moveTo(row, col);
        print(text);
    }

    // ------------------------------------------------------------------ rensning

    public static void clearScreen()    { csi("2J"); moveTo(1, 1); }
    public static void clearLine()      { csi("2K"); }
    public static void clearToEndOfLine() { csi("K"); }
    public static void clearToEndOfScreen() { csi("J"); }

    /** Alternativ skärmbuffert (som vim/less). Anropa leaveAltScreen() för att komma tillbaka. */
    public static void enterAltScreen() { csi("?1049h"); }
    public static void leaveAltScreen() { csi("?1049l"); }

    public static void reset()          { if (enabled) print(CSI + "0m"); }

    // ------------------------------------------------------------------ övrigt

    /** Slå av/på färger och stilar. */
    public static void setEnabled(boolean on) { enabled = on; }
    public static boolean isEnabled()         { return enabled; }

    /**
     * Slå av/på avancerade sekvenser (markörflytt, rensa, klickbara länkar).
     * Normalt automatiskt; använd detta för att tvinga på om autodetekteringen har fel.
     */
    public static void setAdvanced(boolean on) { advanced = on; }
    public static boolean isAdvanced()         { return advanced; }

    // Markörflytt, rensning m.m. skickas bara i en riktig terminal.
    private static void csi(String seq) { if (advanced) print(CSI + seq); }
    private static void raw(String seq) { if (advanced) print(seq); }

    // -------------------------------------------------------------------- demo

    public static void main(String[] args) throws InterruptedException {
        clearScreen();

        println(bold("== Term demo =="));
        println(color("Röd ", Color.RED) + color("Grön ", Color.GREEN) + color("Blå", Color.BLUE));
        println(fmt(" Vit på röd ", Color.WHITE, Color.RED, Style.BOLD)
                + " " + fmt("understruken", null, null, Style.UNDERLINE)
                + " " + fmt("kursiv", null, null, Style.ITALIC));

        StringBuilder gradient = new StringBuilder();
        for (int i = 0; i < 40; i++) gradient.append(bgRgb(" ", i * 6, 80, 255 - i * 6));
        println(gradient.toString());

        println("Länk: " + link("https://www.anthropic.com", "Klicka här (Ctrl/Cmd+klick)"));
        println();

        if (!advanced) {
            println(color("(Ingen riktig terminal upptäckt – markörflytt och klickbara länkar är avstängda.)",
                    Color.YELLOW));
            println();
        }

        hideCursor();
        for (int i = 0; i <= 100; i++) {
            progress("Laddar", i / 100.0, 30);
            if (advanced) printAt(12, 1, "Statusruta - steg " + i + "   ");
            Thread.sleep(25);
        }
        println();
        showCursor();

        if (advanced) {
            printAt(14, 20, fmt(" Text på rad 14, kolumn 20 ", Color.BLACK, Color.YELLOW));
            moveTo(16, 1);
        }
        println(color("Klart!", Color.BRIGHT_GREEN));
    }
}
