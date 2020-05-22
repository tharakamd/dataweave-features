package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class ReduceMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        int sum = PayloadHelper.getJsonArrayStream(mc)
                .map(JsonElement::getAsInt)
                .reduce(0, Integer::sum);

        try {
            PayloadHelper.setJsonPayload(mc, String.valueOf(sum));
        } catch (AxisFault axisFault) {
            getLog(mc).error(axisFault);
            return false;
        }
        return true;
    }
}
