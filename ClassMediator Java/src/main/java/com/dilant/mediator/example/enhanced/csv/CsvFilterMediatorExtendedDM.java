package com.dilant.mediator.example.enhanced.csv;

import com.dilant.mediator.util.collector.PayloadCollectors;
import com.dilant.mediator.util.extender.AbstractExtendedMediator;
import com.dilant.mediator.util.extender.ExtendedMessageContext;

public class CsvFilterMediatorExtendedDM extends AbstractExtendedMediator {
    @Override
    public void mediate(ExtendedMessageContext mc) {

        mc.getCsvArrayStream(1)
                .filter(row -> Integer.parseInt(row[0]) > 5)
                .collect(PayloadCollectors.toCsv(mc));

    }
}
