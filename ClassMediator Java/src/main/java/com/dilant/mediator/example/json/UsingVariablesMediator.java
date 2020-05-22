package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class UsingVariablesMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        JsonObject jsonObject = PayloadHelper.getPayloadJsonObject(mc);

        String test = " is awesome";
        String companyName = jsonObject.get("Company").getAsString();
        String result = companyName + test;

        try {
            PayloadHelper.setJsonPayload(mc, new JsonPrimitive(result));
        } catch (AxisFault axisFault) {
            getLog(mc).error(axisFault);
            return false;
        }
        return true;
    }
}
