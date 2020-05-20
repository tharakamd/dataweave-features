package com.dilant.mediator.example;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

public class PluckMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        try {

            return JsonHelper.getIndexedJsonObjectStream(mc, "$.accountType[0]")
                    .map(indexedEntry -> {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.add(String.valueOf(indexedEntry.getIndex()), new JsonPrimitive(indexedEntry.getEntry().getKey()));
                        jsonObject.add("accountInfo", indexedEntry.getEntry().getValue());
                        return jsonObject;
                    })
                    .collect(JsonHelper.toJsonPayloadAsArray(mc));


        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
    }
}