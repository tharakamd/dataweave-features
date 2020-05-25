package com.dilant.mediator.example.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.stream.StreamSupport;

public class DynamicElementMediator extends AbstractMediator {

    private final JsonParser parser;

    public DynamicElementMediator() {

        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {

        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        JsonArray entries = jsonObject.get("entries").getAsJsonArray();
        JsonArray results = new JsonArray();
        StreamSupport.stream(entries.spliterator(), false)
                .map(JsonElement::getAsJsonObject)
                .filter(obj -> obj.get("Company").getAsString().equals("MuleSoft"))
                .forEach(results::add);

        String transformedJson = results.toString();
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                transformedJson, true, true);

        return true;
    }
}
