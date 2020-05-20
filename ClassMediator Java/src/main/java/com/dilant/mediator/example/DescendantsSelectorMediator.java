package com.dilant.mediator.example;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

public class DescendantsSelectorMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        try {
            JsonArray array = PayloadHelper.getPayloadJsonElement(mc, "$..name").getAsJsonArray();
            PayloadHelper.setJsonPayload(mc, array);
        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
    }
}
