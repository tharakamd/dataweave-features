package com.dilant.mediator.example.enhanced.csv;

import org.apache.synapse.mediators.AbstractExtendedMediator;
import org.apache.synapse.mediators.util.SimpleMessageContext;

public class CsvFilterMediatorExtendedDM extends AbstractExtendedMediator {

    @Override
    public void mediate(SimpleMessageContext mc) {

        mc.getCsvArrayStream(1)
                .filter(row -> Integer.parseInt(row[0]) > 5)
                .collect(mc.toCsv());

    }
}
