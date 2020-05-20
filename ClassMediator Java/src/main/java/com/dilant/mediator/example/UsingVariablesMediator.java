package com.dilant.mediator.example;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class UsingVariablesMediator extends AbstractMediator {

    private final JsonParser parser;

    public UsingVariablesMediator() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        JsonObject jsonObject = PayloadHelper.getPayloadJsonObject(mc);

        String test = " is awesome";
        String companyName = jsonObject.get("Company").getAsString();
        String result = companyName + test;

        PayloadHelper.setJsonPayload(mc, result);
        return true;
    }
}
