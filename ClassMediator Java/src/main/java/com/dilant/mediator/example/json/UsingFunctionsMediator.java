package com.dilant.mediator.example.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class UsingFunctionsMediator extends AbstractMediator {

    private final JsonParser parser;

    public UsingFunctionsMediator() {

        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {

        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        String companyName = jsonObject.get("Company").getAsString();
        String result = function(companyName);

        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                result, true, true);

        return true;
    }

    private String function(String arg) {

        if (arg.equals("MuleSoft")) {
            return "Match";
        } else {
            return "No Match";
        }
    }
}
