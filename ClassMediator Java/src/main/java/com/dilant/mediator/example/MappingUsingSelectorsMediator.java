package com.dilant.mediator.example;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

public class MappingUsingSelectorsMediator extends AbstractMediator {
    private final JsonParser parser;

    public MappingUsingSelectorsMediator() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        try {
            JsonArray array = JsonHelper.getPayloadJsonElement(mc, "$..users[*]").getAsJsonArray();

            JsonObject obj1 = new JsonObject();
            obj1.add("accountInfo", array);
            JsonArray arr1 = new JsonArray();
            arr1.add(obj1);

            JsonHelper.setJsonPayload(mc, arr1);

        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
    }
}
