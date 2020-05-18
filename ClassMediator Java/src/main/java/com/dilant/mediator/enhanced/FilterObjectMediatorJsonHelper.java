package com.dilant.mediator.enhanced;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.Map;

public class FilterObjectMediatorJsonHelper extends AbstractMediator {
    private final JsonParser parser;

    public FilterObjectMediatorJsonHelper() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        JsonObject jsonObject = JsonHelper.getPayloadJsonObject(mc);

        JsonObject result = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getValue().getAsInt() >= 2) {
                result.add(entry.getKey(), entry.getValue());
            }
        }

        JsonHelper.setJsonPayload(mc, result);

        return true;
    }

}
