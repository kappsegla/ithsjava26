# Kravspecifikation: Dryckesmaskin

## Systemöversikt

Systemet ska hantera bryggning av olika typer av drycker via ett enhetligt gränssnitt och en garanterad processsekvens.
makeBeverage()
---

## Funktionella krav

* **FR-1: Enhetlig start**
Alla dryckesmaskiner ska kunna startas via ett gemensamt anrop, oavsett vilken dryck som tillverkas.
* **FR-2: Obligatorisk sekvens**
Varje bryggning ska utan undantag utföra följande steg i exakt ordning:
1. Koka vatten
2. Brygga dryck
3. Hälla upp i kopp


* **FR-3: Dryckesspecifikt bryggsteg**
Själva bryggsteget (steg 2) ska anpassas utifrån dryckestyp:
* **Kaffe:** Genomför kaffebryggning.
* **Te:** Genomför tedragning.


* **FR-4: Återanvändbar grundlogik**
Momenten för vattenkokning (steg 1) och upphällning (steg 3) ska vara gemensamma för alla dryckestyper så att kodupprepning undviks.
