package com.dilant.mediator.example.xml;

import com.dilant.mediator.util.PayloadHelper;
import org.apache.axiom.om.OMElement;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import javax.xml.namespace.QName;

public class InsertXmlTagMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        PayloadHelper.getXmlChildElementsStream(mc)
                .forEach(bookElement -> {
                    String year = bookElement.getFirstChildWithName(new QName("year")).getText();
                    OMElement titleElement = bookElement.getFirstChildWithName(new QName("title"));
                    titleElement.addAttribute("lang", "en", null);
                    titleElement.addAttribute("year", year, null);

                    bookElement.getChildrenWithLocalName("author").forEachRemaining(
                            authorElement -> ((OMElement) authorElement).addAttribute("loc", "US", null));

                });

        return true;
    }
}
