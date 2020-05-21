package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import com.dilant.mediator.util.collector.PayloadCollectors;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class MapMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        return PayloadHelper.getJsonArrayStreamWithIndex(mc)
                .map(indexedJsonElement -> {
                    JsonObject currentObject = indexedJsonElement.getElement().getAsJsonObject();
                    JsonObject object = new JsonObject();
                    object.add("index", new JsonPrimitive(indexedJsonElement.getIndex()));
                    object.add("Full Name", new JsonPrimitive(currentObject.get("FirstName").getAsString() + " " + currentObject.get("LastName").getAsString()));
                    object.add("Company", currentObject.get("Company"));
                    return object;
                })
                .collect(PayloadCollectors.toJsonArray(mc));

    }
}
