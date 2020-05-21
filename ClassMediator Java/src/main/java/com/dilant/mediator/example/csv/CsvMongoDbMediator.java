package com.dilant.mediator.example.csv;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class CsvMongoDbMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        JsonArray customerDetails = new JsonArray();

        PayloadHelper.getCsvArrayStream(mc, 1)
                .forEach(row -> {

                    JsonObject customerObject = new JsonObject();
                    customerObject.add("firstname", new JsonPrimitive(row[0]));
                    customerObject.add("surname", new JsonPrimitive(row[1]));
                    customerObject.add("phone", new JsonPrimitive(row[2]));
                    customerObject.add("email", new JsonPrimitive(row[3]));

                    JsonObject customerWrapper = new JsonObject();
                    customerWrapper.add("Customer", customerObject);
                    customerDetails.add(customerWrapper);
                });

        JsonObject rootObject = new JsonObject();
        rootObject.add("customerdetails", customerDetails);

        try {
            PayloadHelper.setJsonPayloadToXmlContext(mc, rootObject);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }


        return true;
    }
}
