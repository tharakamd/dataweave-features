package com.dilant.mediator.example.csv;

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

public class CsvExceptionStrategyMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        String text = PayloadHelper.getTextPayload(mc);
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(text)).withSkipLines(1).build();

        JsonArray jsonArray = new JsonArray();

        StreamSupport.stream(csvReader.spliterator(), false)
                .forEach(row -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("orderId", new JsonPrimitive(row[0]));
                    jsonObject.add("name", new JsonPrimitive(row[1]));
                    jsonObject.add("pricePerUnit", new JsonPrimitive(row[2]));
                    jsonObject.add("units", new JsonPrimitive(row[3]));
                    jsonArray.add(jsonObject);
                });

        try {
            com.dilant.mediator.util.PayloadHelper.setJsonPayloadToXmlContext(mc, jsonArray);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }


        return true;
    }
}
