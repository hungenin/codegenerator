package com.lpsolutions.codegenerator.model.htmlelement;

import java.util.HashMap;
import java.util.Map;

public class ElementCounter {
    private final Map<String, Integer> elementCounters = new HashMap<>();
    private final Map<String, String[]> elementIndexesToRemove = new HashMap<>();

    public ElementCounter(String[] h1IndexesToRemove,
                          String[] pIndexesToRemove,
                          String[] aIndexesToRemove,
                          String[] tableIndexesToRemove,
                          String[] trIndexesToRemove,
                          String[] tdIndexesToRemove) {
        elementCounters.put("h1", 0);
        elementCounters.put("p", 0);
        elementCounters.put("a", 0);
        elementCounters.put("table", 0);
        elementCounters.put("tr", 0);
        elementCounters.put("td", 0);

        elementIndexesToRemove.put("h1", h1IndexesToRemove);
        elementIndexesToRemove.put("p", pIndexesToRemove);
        elementIndexesToRemove.put("a", aIndexesToRemove);
        elementIndexesToRemove.put("table", tableIndexesToRemove);
        elementIndexesToRemove.put("tr", trIndexesToRemove);
        elementIndexesToRemove.put("td", tdIndexesToRemove);
    }

    public void increaseCounter(String type) {
        increaseCounter(type, 1);
    }

    public void increaseCounter(String type, int multiplier) {
        if (elementCounters.containsKey(type)) elementCounters.replace(type, elementCounters.get(type) + multiplier);
    }

    public boolean showElement(String type) {
        if (!elementCounters.containsKey(type) || !elementIndexesToRemove.containsKey(type)) return true;

        Integer index = elementCounters.get(type);
        String[] indexes = elementIndexesToRemove.get(type);

        return indexes == null || (indexes.length > 0 && arrayNotContainsValue(indexes, index));
    }

    private static boolean arrayNotContainsValue(String[] array, Integer value) {
        for (String actualValue: array) {
            if (actualValue.equals(value.toString())) return false;
        }

        return true;
    }
}
