package com.dilant.mediator.util.collector;

import com.dilant.mediator.util.PayloadHelper;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.UNORDERED;

public class JsonObjectCollector implements Collector<Map.Entry<String, JsonElement>, JsonObject, Boolean> {

    private final MessageContext mc;

    JsonObjectCollector(MessageContext mc) {
        this.mc = mc;
    }

    @Override
    public Supplier<JsonObject> supplier() {
        return JsonObject::new;
    }

    @Override
    public BiConsumer<JsonObject, Map.Entry<String, JsonElement>> accumulator() {
        return (jsonObject, jsonElementEntry) -> jsonObject.add(jsonElementEntry.getKey(), jsonElementEntry.getValue());
    }

    @Override
    public BinaryOperator<JsonObject> combiner() {
        return (jsonObject, jsonObject2) -> {
            jsonObject2.entrySet().forEach(jsonElementEntry -> {
                jsonObject.add(jsonElementEntry.getKey(), jsonElementEntry.getValue());
            });

            return jsonObject;
        };
    }

    @Override
    public Function<JsonObject, Boolean> finisher() {
        return result -> {
            try {
                PayloadHelper.setJsonPayload(mc, result);
            } catch (AxisFault axisFault) {
                return false;
            }
            return true;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.immutableEnumSet(UNORDERED);
    }
}
