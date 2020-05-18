package com.dilant.mediator.enhanced;

import com.dilant.mediator.util.JsonHelper;
import com.dilant.mediator.util.collector.JsonElementCollector;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class FilterObjectMediatorElementCollector extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        return JsonHelper.getJsonObjectStream(mc)
                .filter(objectEntry -> objectEntry.getValue().getAsInt() >= 2)
                .collect(JsonElementCollector.toJsonPayloadAsObject(mc));
    }

}
