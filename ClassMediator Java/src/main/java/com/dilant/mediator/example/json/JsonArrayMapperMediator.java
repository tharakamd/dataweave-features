package com.dilant.mediator.example.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class JsonArrayMapperMediator extends AbstractMediator {

    private final JsonParser parser;

    public JsonArrayMapperMediator() {
        parser = new JsonParser();
    }

    public boolean mediate(MessageContext mc) {
        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonArray jsonArray = parser.parse(jsonPayload).getAsJsonArray();

        int arraySize = jsonArray.size();
        for(int i=0; i<arraySize; i++) {
            jsonArray.set(i, generateResultObject(jsonArray.get(i).getAsJsonObject(), i));
        }

        String transformedJson = jsonArray.toString();

        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                transformedJson, true, true);

        return true;
    }

    private JsonObject generateResultObject(JsonObject jsonObject, int index){
        JsonObject resultObject = new JsonObject();
        resultObject.add("index", new JsonPrimitive(index));
        resultObject.add("Full Name", new JsonPrimitive(jsonObject.get("FirstName") + " " + jsonObject.get("LastName")));
        resultObject.add("Company", new JsonPrimitive(jsonObject.get("Company").getAsString()));
        return resultObject;
    }

}