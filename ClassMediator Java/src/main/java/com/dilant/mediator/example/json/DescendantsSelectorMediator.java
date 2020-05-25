package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

public class DescendantsSelectorMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        try {
            JsonArray array = PayloadHelper.getPayloadJsonElement(mc, "$..name").getAsJsonArray();
            PayloadHelper.setJsonPayload(mc, array);
        } catch (JaxenException | AxisFault e) {
            getLog(mc).error(e);
            return false;
        }

        return true;
    }
}
