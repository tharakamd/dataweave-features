package com.dilant.mediator.util.collector;

import com.dilant.mediator.util.PayloadHelper;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.UNORDERED;

public class JsonArrayCollector implements Collector<JsonElement, JsonArray, Boolean> {

    private final MessageContext mc;

    JsonArrayCollector(MessageContext mc) {
        this.mc = mc;
    }

    @Override
    public Supplier<JsonArray> supplier() {
        return JsonArray::new;
    }

    @Override
    public BiConsumer<JsonArray, JsonElement> accumulator() {
        return JsonArray::add;
    }

    @Override
    public BinaryOperator<JsonArray> combiner() {
        return (array1, array2) -> {
            JsonArray newArray = new JsonArray();
            while (array1.iterator().hasNext()) {
                newArray.add(array1.iterator().next());
            }
            while (array2.iterator().hasNext()) {
                newArray.add(array2.iterator().next());
            }
            return newArray;

        };
    }

    @Override
    public Function<JsonArray, Boolean> finisher() {
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
