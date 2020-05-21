package com.dilant.mediator.util;

import com.google.gson.*;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.util.xpath.SynapseJsonPath;
import org.jaxen.JaxenException;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PayloadHelper {

    private static final JsonParser parser = new JsonParser();
    private static final Gson gson = new Gson();

    private PayloadHelper() {
    }

    public static Stream<OMElement> getXmlChildElementsStream(OMElement xmlElement) {
        Iterable<OMElement> iterable = xmlElement::getChildElements;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static Stream<IndexedJsonElement> getJsonArrayStreamWithIndex(MessageContext mc) {
        JsonArray jsonArray = getPayloadJsonArray(mc);
        return getJsonArrayStreamWithIndex(jsonArray);
    }

    private static Stream<IndexedJsonElement> getJsonArrayStreamWithIndex(JsonArray jsonArray) {
        return IntStream.range(0, jsonArray.size())
                .mapToObj(i -> new IndexedJsonElement(i, jsonArray.get(i)));
    }

    public static Stream<JsonElement> getJsonArrayStream(MessageContext mc, String jsonPath) throws JaxenException {
        JsonElement evaluationResultJson = getPayloadJsonElement(mc, jsonPath);
        return getJsonArrayStream(evaluationResultJson.getAsJsonArray());
    }

    public static Stream<JsonElement> getJsonArrayStream(MessageContext mc) {
        JsonArray jsonArray = getPayloadJsonArray(mc);
        return getJsonArrayStream(jsonArray);
    }

    public static Stream<JsonElement> getJsonArrayStream(JsonArray jsonArray) {
        return StreamSupport.stream(jsonArray.spliterator(), false);
    }

    public static Stream<IndexedEntry> getIndexedJsonObjectStream(MessageContext mc, String jsonPath) throws JaxenException {
        JsonElement evaluationResultJson = getPayloadJsonElement(mc, jsonPath);
        return getJsonObjectStreamWithIndex(parser.parse(evaluationResultJson.getAsJsonArray().get(0).getAsString()).getAsJsonObject());
    }

    public static Stream<Map.Entry<String, JsonElement>> getJsonObjectStream(MessageContext mc, String jsonPath) throws JaxenException {
        JsonElement evaluationResultJson = getPayloadJsonElement(mc, jsonPath);
        return getJsonObjectStream(parser.parse(evaluationResultJson.getAsJsonArray().get(0).getAsString()).getAsJsonObject());
    }

    public static Stream<Map.Entry<String, JsonElement>> getJsonObjectStream(MessageContext mc) {
        JsonObject jsonObject = getPayloadJsonObject(mc);
        return getJsonObjectStream(jsonObject);
    }

    public static Stream<IndexedEntry> getJsonObjectStreamWithIndex(JsonObject jsonObject) {
        return IntStream.range(0, jsonObject.entrySet().size())
                .mapToObj(i -> new IndexedEntry(i, jsonObject.entrySet().iterator().next()));
    }

    public static Stream<Map.Entry<String, JsonElement>> getJsonObjectStream(JsonObject jsonObject) {
        return jsonObject.entrySet().stream();
    }

    public static JsonArray getPayloadJsonArray(MessageContext mc) {
        return getPayloadJsonElement(mc).getAsJsonArray();
    }

    public static JsonObject getPayloadJsonObject(MessageContext mc) {
        return getPayloadJsonElement(mc).getAsJsonObject();
    }

    public static JsonElement getPayloadJsonElement(MessageContext mc, String jsonPath) throws JaxenException {
        SynapseJsonPath synapseJsonPath = new SynapseJsonPath(jsonPath);
        Object evaluationResult = synapseJsonPath.evaluate(mc);
        return gson.toJsonTree(evaluationResult);
    }

    public static JsonElement getPayloadJsonElement(MessageContext mc) {
        String jsonPayloadString = getPayloadJsonString(mc);
        return parser.parse(jsonPayloadString);
    }

    public static void setJsonPayloadToXmlContext(MessageContext mc, JsonElement jsonElement) throws AxisFault {
        org.apache.axis2.context.MessageContext axis2MessageContext = ((Axis2MessageContext) mc).getAxis2MessageContext();
        axis2MessageContext.setProperty("messageType", "application/json");
        axis2MessageContext.setProperty("ContentType", "application/json");

        setJsonPayload2(mc, jsonElement.toString());
    }

    public static String getPayloadJsonString(MessageContext mc) {
        return JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
    }

    public static void setJsonPayload(MessageContext mc, JsonElement jsonPayload) {
        String transformedJson = jsonPayload.toString();
        setJsonPayload(mc, transformedJson);
    }

    // todo :: change this with getNewJsonPayload
    public static void setJsonPayload(MessageContext mc, String payload) {
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                payload, true, true);
    }

    public static void setJsonPayload2(MessageContext mc, String payload) throws AxisFault {
        JsonUtil.getNewJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                payload, true, true);
    }

}