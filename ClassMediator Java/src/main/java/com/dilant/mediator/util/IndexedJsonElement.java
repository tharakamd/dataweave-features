package com.dilant.mediator.util;

import com.google.gson.JsonElement;

public class IndexedJsonElement {
    private int index;
    private JsonElement element;

    public IndexedJsonElement(int index, JsonElement element) {
        this.index = index;
        this.element = element;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public JsonElement getElement() {
        return element;
    }

    public void setElement(JsonElement element) {
        this.element = element;
    }
}
