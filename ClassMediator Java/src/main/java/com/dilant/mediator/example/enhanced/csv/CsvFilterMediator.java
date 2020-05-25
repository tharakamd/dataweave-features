package com.dilant.mediator.example.enhanced.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.util.PayloadHelper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.stream.StreamSupport;

public class CsvFilterMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

        String text = PayloadHelper.getTextPayload(mc);
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(text)).withSkipLines(1).build();
        StreamSupport.stream(csvReader.spliterator(), false)
                .filter(row -> Integer.parseInt(row[0]) > 5)
                .forEach(csvWriter::writeNext);

        try {
            csvWriter.close();
            writer.flush();
            String content = writer.toString();
            PayloadHelper.setTextPayload(mc, content);
        } catch (IOException e) {
            getLog(mc).error(e);
        }

        return true;
    }
}
