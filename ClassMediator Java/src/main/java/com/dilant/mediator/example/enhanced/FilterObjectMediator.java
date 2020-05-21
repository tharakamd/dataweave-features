package com.dilant.mediator.example.enhanced;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class FilterObjectMediator extends AbstractMediator {
    private final JsonParser parser;

    public FilterObjectMediator() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        JsonObject result = new JsonObject();
        jsonObject.entrySet().stream()
                .filter(objectEntry -> objectEntry.getValue().getAsInt() >= 2)
                .forEach(entry -> result.add(entry.getKey(), entry.getValue()));

        String transformedJson = result.toString();
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                transformedJson, true, true);

        return true;
    }

}
