package com.dilant.mediator.example.json;

import com.dilant.mediator.util.PayloadHelper;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.UUID;

public class UuidMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        String uuid = UUID.randomUUID().toString();

        PayloadHelper.setJsonPayload(mc, uuid);
        return true;
    }
}
