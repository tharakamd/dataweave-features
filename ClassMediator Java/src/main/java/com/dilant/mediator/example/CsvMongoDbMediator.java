package com.dilant.mediator.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.util.PayloadHelper;

import java.io.StringReader;
import java.util.stream.StreamSupport;

public class CsvMongoDbMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        String text = PayloadHelper.getTextPayload(mc);
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(text)).withSkipLines(1).build();

        JsonArray customerDetails = new JsonArray();

        StreamSupport.stream(csvReader.spliterator(), false)
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
            com.dilant.mediator.util.PayloadHelper.setJsonPayloadToXmlContext(mc, rootObject);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }


        return true;
    }
}
