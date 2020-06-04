package com.dilant.mediator.example.enhanced.csv;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.synapse.mediators.AbstractExtendedMediator;
import org.apache.synapse.mediators.util.SimpleMessageContext;

public class CsvExceptionStrategyMediatorExtendedDM extends AbstractExtendedMediator {

    @Override
    public void mediate(SimpleMessageContext mc) {

        JsonArray jsonArray = new JsonArray();

        mc.getCsvArrayStream(1)
                .forEach(row -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("orderId", new JsonPrimitive(row[0]));
                    jsonObject.add("name", new JsonPrimitive(row[1]));
                    jsonObject.add("pricePerUnit", new JsonPrimitive(row[2]));
                    jsonObject.add("units", new JsonPrimitive(row[3]));
                    jsonArray.add(jsonObject);
                });

        mc.setJsonPayload(jsonArray);

    }
}
