package com.lpsolutions.codegenerator.service;

import com.lpsolutions.codegenerator.dao.RepositoryDao;
import com.lpsolutions.codegenerator.dao.UserDao;
import com.lpsolutions.codegenerator.model.User;
import com.lpsolutions.codegenerator.model.htmlelement.ElementCounter;
import com.lpsolutions.codegenerator.model.htmlelement.HtmlElement;
import com.lpsolutions.codegenerator.model.htmlelement.TextElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGeneratorService {
    private final String pageTitle = "Teszt Feladat";
    private final String headingLabel = "Teszt Feladat";
    private final String sourcecodeLinkLabel = "Megoldás";
    private final String tableTitle = "A feladat elkészítőjének adatai";
    private final String tableNameLabel = "Név";
    private final String tableContactLabel = "Elérhetőség";
    private final String lpSolutionLinkLabel = "L&P Solutions";
    private final String lpSolutionLinkUrl = "http://lpsolutions.hu";

    @Autowired
    UserDao userDao;
    @Autowired
    RepositoryDao repositoryDao;

    public String getTestCode(String[] h1, String[] p, String[] a, String[] table, String[] tr, String[] td) {
        ElementCounter elementCounter = new ElementCounter(h1, p, a, table, tr, td);

        HtmlElement html = new HtmlElement("html");

        createHead(html);
        createTestBody(html, elementCounter);

        return "<!DOCTYPE html>\n" + html;
    }

    private void createHead(HtmlElement parent) {
        HtmlElement head = new HtmlElement("head");
        parent.addChild(head);

        HtmlElement title = new HtmlElement("title", true);
        head.addChild(title);

        TextElement titleText = new TextElement(pageTitle);
        title.addChild(titleText);
    }

    private void createTestBody(HtmlElement parent, ElementCounter elementCounter) {
        HtmlElement body = new HtmlElement("body");
        parent.addChild(body);

        createHeading(body, elementCounter, headingLabel);
        createParagraphWithLink(body, elementCounter, sourcecodeLinkLabel, repositoryDao.getRepository().getUrl());
        createParagraphWithText(body, elementCounter, tableTitle);
        createUserDataTable(body, elementCounter, userDao.getUser());
        createLink(body, elementCounter, lpSolutionLinkLabel, lpSolutionLinkUrl);
    }

    private void createHeading(HtmlElement parent, ElementCounter elementCounter, String text) {
        if (elementCounter.showElement("h1")) {
            HtmlElement h1 = new HtmlElement("h1", true);
            parent.addChild(h1);

            h1.addChild(new TextElement(text));
        }

        elementCounter.increaseCounter("h1");
    }

    private void createParagraphWithLink(HtmlElement parent, ElementCounter elementCounter, String label, String url) {
        if (elementCounter.showElement("p")) {
            HtmlElement p = new HtmlElement("p", true);
            parent.addChild(p);

            createLink(p, elementCounter, label, url);
        } else {
            elementCounter.increaseCounter("a");
        }

        elementCounter.increaseCounter("p");
    }

    private void createParagraphWithText(HtmlElement parent, ElementCounter elementCounter, String text) {
        if (elementCounter.showElement("p")) {
            HtmlElement p = new HtmlElement("p", true);
            parent.addChild(p);

            p.addChild(new TextElement(text));
        }

        elementCounter.increaseCounter("p");
    }

    private void createLink(HtmlElement parent, ElementCounter elementCounter, String label, String url) {
        if (elementCounter.showElement("a")) {
            HtmlElement a = new HtmlElement("a", "href=\"" + url + "\"",  true);
            parent.addChild(a);

            TextElement text = new TextElement(label);
            a.addChild(text);
        }

        elementCounter.increaseCounter("a");
    }

    private void createUserDataTable(HtmlElement parent, ElementCounter elementCounter, User user) {
        if (elementCounter.showElement("table")) {
            HtmlElement table = new HtmlElement("table", "border=\"1px solid black\"");
            parent.addChild(table);

            createUserDataTableRow(table, elementCounter, tableNameLabel, user.getName());
            createUserDataTableRow(table, elementCounter, tableContactLabel, user.getEmail());
        } else {
            elementCounter.increaseCounter("tr", 2);
            elementCounter.increaseCounter("td", 4);
        }

        elementCounter.increaseCounter("table");
    }

    private void createUserDataTableRow(HtmlElement parent, ElementCounter elementCounter, String value1, String value2) {
        if (elementCounter.showElement("tr")) {
            HtmlElement tr = new HtmlElement("tr");
            parent.addChild(tr);

            createUserDataTableField(tr, elementCounter, value1);
            createUserDataTableField(tr, elementCounter, value2);
        } else {
            elementCounter.increaseCounter("td", 4);
        }

        elementCounter.increaseCounter("tr");
    }

    private void createUserDataTableField(HtmlElement parent, ElementCounter elementCounter, String value) {
        if (elementCounter.showElement("td")) {
            HtmlElement td = new HtmlElement("td", true);
            parent.addChild(td);

            td.addChild(new TextElement(value));
        }

        elementCounter.increaseCounter("td");
    }
}
