package com.lpsolutions.codegenerator.model.htmlelement;

public class TextElement extends HtmlElement {
    private final String text;

    public TextElement(String text) {
        super("text");

        this.text = text;
    }

    @Override
    public void addChild(HtmlElement child) {
        throw new RuntimeException("Cannot add child to a text element!");
    }

    @Override
    public String toString() {
        return String.format("%s", text);
    }
}
