package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonPrimitive;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.UUID;

public class UuidMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        String uuid = UUID.randomUUID().toString();

        try {
            PayloadHelper.setJsonPayload(mc, new JsonPrimitive(uuid));
        } catch (AxisFault axisFault) {
            getLog(mc).error(axisFault);
            return false;
        }
        return true;
    }
}
