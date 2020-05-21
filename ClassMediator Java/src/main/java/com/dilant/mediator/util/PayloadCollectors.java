package com.dilant.mediator.util;

import com.dilant.mediator.util.collector.JsonElementArrayCollector;
import com.dilant.mediator.util.collector.JsonElementObjectCollector;
import org.apache.synapse.MessageContext;

public class PayloadCollectors {
    private PayloadCollectors() {
    }

    public static JsonElementArrayCollector toJsonArray(MessageContext mc) {
        return new JsonElementArrayCollector(mc);
    }

    public static JsonElementObjectCollector toJsonObject(MessageContext mc) {
        return new JsonElementObjectCollector(mc);
    }
}
