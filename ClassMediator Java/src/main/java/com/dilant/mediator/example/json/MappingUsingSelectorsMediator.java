package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

public class MappingUsingSelectorsMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        try {
            JsonArray array = PayloadHelper.getPayloadJsonElement(mc, "$..users[*]").getAsJsonArray();

            JsonObject obj1 = new JsonObject();
            obj1.add("accountInfo", array);
            JsonArray arr1 = new JsonArray();
            arr1.add(obj1);

            PayloadHelper.setJsonPayload(mc, arr1);

        } catch (JaxenException | AxisFault e) {
            getLog(mc).error(e);
            return false;
        }

        return true;
    }
}
