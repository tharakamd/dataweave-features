package com.dilant.mediator.example.enhanced;

import com.dilant.mediator.util.PayloadCollectors;
import com.dilant.mediator.util.PayloadHelper;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class FilterObjectMediatorElementCollector extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        return PayloadHelper.getJsonObjectStream(mc)
                .filter(objectEntry -> objectEntry.getValue().getAsInt() >= 2)
                .collect(PayloadCollectors.toJsonObject(mc));
    }

}