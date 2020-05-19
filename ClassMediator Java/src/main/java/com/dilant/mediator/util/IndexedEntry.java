package com.dilant.mediator.util;

import com.google.gson.JsonElement;

import java.util.Map;

public class IndexedEntry {
    private int index;
    private Map.Entry<String, JsonElement> entry;

    public IndexedEntry(int index, Map.Entry<String, JsonElement> entry) {
        this.index = index;
        this.entry = entry;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Map.Entry<String, JsonElement> getEntry() {
        return entry;
    }

    public void setEntry(Map.Entry<String, JsonElement> entry) {
        this.entry = entry;
    }
}
