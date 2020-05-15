package com.dilant.mediator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.util.Iterator;

public class XmlArrayMapperMediator extends AbstractMediator {

    private final OMFactory factory;

    public XmlArrayMapperMediator() {
        factory = OMAbstractFactory.getOMFactory();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        OMElement rootElement = mc.getEnvelope().getBody().getFirstElement();
        Iterator<OMElement> iterator = rootElement.getChildElements();

        while (iterator.hasNext()) {
            OMElement element = iterator.next();

            OMElement firstNameElement = (OMElement) element.getChildrenWithLocalName("FirstName").next();
            String firstName = firstNameElement.getText();
            firstNameElement.detach();

            OMElement lastNameElement = (OMElement) element.getChildrenWithLocalName("LastName").next();
            String lastName = lastNameElement.getText();
            lastNameElement.detach();

            OMElement companyElement = (OMElement) element.getChildrenWithLocalName("Company").next();
            companyElement.detach();

            OMElement fullNameElement = factory.createOMElement("FullName", rootElement.getNamespace());
            fullNameElement.setText(firstName + " " + lastName);
            element.addChild(fullNameElement);

           element.addChild(companyElement);
        }
        return true;
    }
}
