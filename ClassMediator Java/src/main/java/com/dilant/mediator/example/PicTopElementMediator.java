package com.dilant.mediator.example;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

import java.util.Comparator;

public class PicTopElementMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        try {

            JsonObject root = PayloadHelper.getPayloadJsonElement(mc).getAsJsonObject();
            int limit = root.get("availablePositions").getAsInt();

            return PayloadHelper.getJsonArrayStream(mc, "$.candidates[*]")
                    .map(JsonElement::getAsJsonObject)
                    .sorted(Comparator.comparingInt(o -> o.get("score").getAsInt()))
                    .limit(limit)
                    .collect(PayloadHelper.toJsonPayloadAsArray(mc));


        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
    }
}
