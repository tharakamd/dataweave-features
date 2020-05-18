package com.dilant.mediator.util.collector;

import org.apache.synapse.MessageContext;

public class JsonElementCollector {

    public static JsonElementArrayCollector toJsonPayloadAsArray(MessageContext mc) {
        return new JsonElementArrayCollector(mc);
    }

    public static JsonElementObjectCollector toJsonPayloadAsObject(MessageContext mc) {
        return new JsonElementObjectCollector(mc);
    }
}
