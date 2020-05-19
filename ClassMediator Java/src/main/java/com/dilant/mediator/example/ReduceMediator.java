package com.dilant.mediator.example;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonElement;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class ReduceMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        int sum = JsonHelper.getJsonArrayStream(mc)
                .map(JsonElement::getAsInt)
                .reduce(0, Integer::sum);

        JsonHelper.setJsonPayload(mc, String.valueOf(sum));
        return true;
    }
}
