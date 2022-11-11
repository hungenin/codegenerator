package com.lpsolutions.codegenerator.model.htmlelement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlElement {
    private final String type;
    private final String properties;
    private final List<HtmlElement> children = new ArrayList<>();
    private boolean singleLine;
    private Integer tabNumber = 0;

    public HtmlElement(String type) {
        this.type = type;
        this.singleLine = false;
        this.properties = "";
    }

    public HtmlElement(String type, String properties) {
        this.type = type;
        this.properties = properties;
        this.singleLine = false;
    }

    public HtmlElement(String type, boolean singleLine) {
        this.type = type;
        this.properties = "";
        this.singleLine = singleLine;
    }

    public HtmlElement(String type, String properties, boolean singleLine) {
        this.type = type;
        this.properties = properties;
        this.singleLine = singleLine;
    }

    public void setTabNumber(Integer tabNumber) {
        this.tabNumber = tabNumber;
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }

    public void addChild(HtmlElement child) {
        children.add(child);
    }

    @Override
    public String toString() {
        String tabs = generateTabs(tabNumber);

        this.children.forEach(htmlElement -> {
            htmlElement.setTabNumber(singleLine ? 0 : tabNumber + 1);
            if (singleLine) htmlElement.setSingleLine(true);
        });

        String children = this.children.stream().map(HtmlElement::toString).collect(Collectors.joining("\n"));
        String properties = this.properties.equals("") ? "" : " " + this.properties;

        if (singleLine) return String.format("%s<%s%s>%s</%s>", tabs, type, properties, children, type);
        if (children.isEmpty()) return String.format("%s<%s%s>\n%s</%s>", tabs, type, properties, tabs, type);
        return String.format("%s<%s%s>\n%s\n%s</%s>", tabs, type, properties, children, tabs, type);
    }

    private static String generateTabs(Integer tabCounter) {
        return tabCounter == null || tabCounter < 1 ? "" : "\t".repeat(tabCounter);
    }
}
