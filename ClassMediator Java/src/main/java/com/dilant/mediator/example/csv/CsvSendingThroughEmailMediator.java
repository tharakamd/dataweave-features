package com.dilant.mediator.example.csv;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class CsvSendingThroughEmailMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        JsonArray jsonArray = new JsonArray();
        PayloadHelper.getCsvArrayStream(mc, 1)
                .forEach(row -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("name", new JsonPrimitive(row[1]));
                    jsonObject.add("orderId", new JsonPrimitive(row[0]));
                    jsonObject.add("pricePerUnit", new JsonPrimitive(row[3]));
                    jsonObject.add("units", new JsonPrimitive(row[2]));
                    jsonObject.add("totalPrice", new JsonPrimitive(Double.parseDouble(row[3]) * Double.parseDouble(row[2])));
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