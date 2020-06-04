/*
package com.dilant.mediator.example.enhanced.csv;

import com.dilant.mediator.util.PayloadHelper;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class CsvFilterMediatorPayloadHelper extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        return PayloadHelper.getCsvArrayStream(mc, 1)
                .filter(row -> Integer.parseInt(row[0]) > 5)
                .collect(mc.toCsv(mc));

    }
}
*/
