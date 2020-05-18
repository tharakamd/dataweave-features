package com.dilant.mediator.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class UsingVariablesMediator extends AbstractMediator {

    private final JsonParser parser;

    public UsingVariablesMediator() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        String test = " is awesome";
        String companyName = jsonObject.get("Company").getAsString();

        String result = companyName + test;


        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                result, true, true);

        return true;
    }
}
