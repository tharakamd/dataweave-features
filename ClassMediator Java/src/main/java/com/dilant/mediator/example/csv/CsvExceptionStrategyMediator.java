package com.dilant.mediator.example.csv;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseLog;
import org.apache.synapse.mediators.AbstractMediator;

public class CsvExceptionStrategyMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        SynapseLog logger = getLog(mc);

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
            PayloadHelper.setJsonPayload(mc, jsonArray);
        } catch (AxisFault axisFault) {
            logger.error(axisFault);
            return false;
        }

        return true;
    }
}
