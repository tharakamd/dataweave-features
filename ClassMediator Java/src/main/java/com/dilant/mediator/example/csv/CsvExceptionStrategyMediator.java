package com.dilant.mediator.example.csv;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class CsvExceptionStrategyMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        JsonArray jsonArray = new JsonArray();

        PayloadHelper.getCsvArrayStream(mc, 1)
                .forEach(row -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("orderId", new JsonPrimitive(row[0]));
                    jsonObject.add("name", new JsonPrimitive(row[1]));
                    jsonObject.add("pricePerUnit", new JsonPrimitive(row[2]));
                    jsonObject.add("units", new JsonPrimitive(row[3]));
                    jsonArray.add(jsonObject);
                });

        try {
            PayloadHelper.setJsonPayloadToXmlContext(mc, jsonArray);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        return true;
    }
}
