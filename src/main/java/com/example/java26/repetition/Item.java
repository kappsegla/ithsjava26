package com.example.java26.repetition;

public class Item implements TextGetter {
    protected String text;

    public Item() {
        this.text = "";
    }

    public Item(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    static void main() {
        Item a = new Item("a");
        Item b = new AdvancedItem("b");

        System.out.println(a.getText());
        System.out.println(b.getText()); //In runtime the getText from AdvancedItem will be used
    }
}

class AdvancedItem extends Item {
    public AdvancedItem(String text) {
        super(text);
    }

    public String camelCasing() {
        return this.text.substring(0, 1).toUpperCase() + this.text.substring(1);
    }

    @Override
    public String getText() {
        return "No no no";
    }
}

interface TextGetter {
    String getText();
}
