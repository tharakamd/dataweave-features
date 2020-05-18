package com.dilant.mediator.enhanced;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class FilterMediatorJsonHelper extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        JsonArray results = new JsonArray();

        JsonHelper.getJsonArrayStream(mc)
                .map(JsonElement::getAsInt)
                .filter(item -> item >= 2)
                .forEach(results::add);

        JsonHelper.setJsonPayload(mc, results);

        return true;
    }
}
