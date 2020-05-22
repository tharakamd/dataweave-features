package com.dilant.mediator.example.xml;

import com.dilant.mediator.util.PayloadHelper;
import org.apache.axiom.om.OMElement;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

import java.util.stream.Stream;

public class XmlArrayMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        try {
            OMElement rootElement = mc.getEnvelope().getBody().getFirstElement();
            Stream<OMElement> nameStream = PayloadHelper.getXmlElementsStream(rootElement, "//FirstName");
            PayloadHelper.getXmlChildElementsStream(mc)
                    .forEach(OMElement::detach);
            nameStream.forEach(rootElement::addChild);
        } catch (JaxenException e) {
            getLog(mc).error(e);
        }

        return true;
    }
}
