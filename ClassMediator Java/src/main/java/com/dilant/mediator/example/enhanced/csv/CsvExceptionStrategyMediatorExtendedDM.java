package com.dilant.mediator.example.enhanced.csv;

import com.dilant.mediator.util.extender.AbstractExtendedMediator;
import com.dilant.mediator.util.extender.SimpleMessageContext;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
