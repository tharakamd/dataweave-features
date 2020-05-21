package com.dilant.mediator.example.enhanced;

import com.dilant.mediator.util.PayloadCollectors;
import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class FilterMediatorJsonElementCollector extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        return PayloadHelper.getJsonArrayStream(mc)
                .map(JsonElement::getAsInt)
                .filter(item -> item >= 2)
                .map(JsonPrimitive::new)
                .collect(PayloadCollectors.toJsonArray(mc));

    }
}
