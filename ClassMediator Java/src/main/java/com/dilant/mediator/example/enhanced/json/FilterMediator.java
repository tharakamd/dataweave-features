package com.dilant.mediator.example.enhanced.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.stream.StreamSupport;

public class FilterMediator extends AbstractMediator {

    private final JsonParser parser;

    public FilterMediator() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonArray jsonArray = parser.parse(jsonPayload).getAsJsonArray();

        JsonArray results = new JsonArray();
        StreamSupport.stream(jsonArray.spliterator(), false)
                .map(JsonElement::getAsInt)
                .filter(item -> item >= 2)
                .forEach(results::add);

        String transformedJson = results.toString();
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                transformedJson, true, true);

        return true;
    }
}
