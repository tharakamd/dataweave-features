package com.dilant.mediator;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.xpath.AXIOMXPath;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.jaxen.JaxenException;

import java.util.Iterator;
import java.util.List;

public class XmlArrayMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        OMElement rootElement = mc.getEnvelope().getBody().getFirstElement();

        try {
            AXIOMXPath xPath = new AXIOMXPath("//FirstName");
            List<OMElement> elementList = xPath.selectNodes(rootElement);

            Iterator<OMElement> iterator = rootElement.getChildElements();
            while (iterator.hasNext()) {
                OMElement element = iterator.next();
                element.detach();
            }

            elementList.forEach(rootElement::addChild);
        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
    }
}
