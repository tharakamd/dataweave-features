package com.dilant.mediator.example.csv;

import com.dilant.mediator.util.PayloadHelper;
import com.dilant.mediator.util.collector.PayloadCollectors;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class CsvFilterMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        return PayloadHelper.getCsvArrayStream(mc, 1)
                .filter(row -> Integer.parseInt(row[0]) > 5)
                .collect(PayloadCollectors.toCsv(mc));

    }
}
