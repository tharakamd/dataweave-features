/*
package com.dilant.mediator.example.json;

import com.google.gson.JsonPrimitive;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.mediators.PayloadCollectors;

import java.util.stream.Stream;

public class SplitByMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        String input = "192.88.99.0";
        return Stream.of(input.split("\\."))
                .map(JsonPrimitive::new)
                .collect(PayloadCollectors.toJsonArray(mc));
    }
}*/
