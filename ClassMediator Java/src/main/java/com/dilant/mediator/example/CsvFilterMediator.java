package com.dilant.mediator.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.util.PayloadHelper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.stream.StreamSupport;

public class CsvFilterMediator extends AbstractMediator {
    @Override
    public boolean mediate(MessageContext mc) {

        Writer writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        String text = PayloadHelper.getTextPayload(mc);
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(text)).withSkipLines(1).build();
        StreamSupport.stream(csvReader.spliterator(), false)
                .filter(row -> Integer.parseInt(row[0]) > 990)
                .forEach(csvWriter::writeNext);
        try {
            csvWriter.close();
            PayloadHelper.setTextPayload(mc, writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
