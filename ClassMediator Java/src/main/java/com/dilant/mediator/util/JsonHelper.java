package com.dilant.mediator.util;

import com.dilant.mediator.util.collector.JsonElementArrayCollector;
import com.dilant.mediator.util.collector.JsonElementObjectCollector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonHelper {

    private static final JsonParser parser = new JsonParser();


    public static Stream<JsonElement> getJsonArrayStream(MessageContext mc) {
        JsonArray jsonArray = getPayloadJsonArray(mc);
        return StreamSupport.stream(jsonArray.spliterator(), false);
    }

    public static Stream<IndexedJsonElement> getJsonArrayStreamWithIndex(MessageContext mc) {
        JsonArray jsonArray = getPayloadJsonArray(mc);
        return IntStream.range(0, jsonArray.size())
                .mapToObj(i -> new IndexedJsonElement(i, jsonArray.get(i)));
    }

    public static Stream<Map.Entry<String, JsonElement>> getJsonObjectStream(MessageContext mc) {
        JsonObject jsonObject = getPayloadJsonObject(mc);
        return jsonObject.entrySet().stream();
    }

    public static JsonArray getPayloadJsonArray(MessageContext mc) {
        return getPayloadJsonElement(mc).getAsJsonArray();
    }

    public static JsonObject getPayloadJsonObject(MessageContext mc) {
        return getPayloadJsonElement(mc).getAsJsonObject();
    }

    public static JsonElement getPayloadJsonElement(MessageContext mc) {
        String jsonPayloadString = getPayloadJsonString(mc);
        return parser.parse(jsonPayloadString);
    }

    public static String getPayloadJsonString(MessageContext mc) {
        return JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
    }

    public static void setJsonPayload(MessageContext mc, JsonElement jsonPayload) {
        String transformedJson = jsonPayload.toString();
        setJsonPayload(mc, transformedJson);
    }

    public static void setJsonPayload(MessageContext mc, String payload) {
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                payload, true, true);
    }

    public static JsonElementArrayCollector toJsonPayloadAsArray(MessageContext mc) {
        return new JsonElementArrayCollector(mc);
    }

    public static JsonElementObjectCollector toJsonPayloadAsObject(MessageContext mc) {
        return new JsonElementObjectCollector(mc);
    }

}
