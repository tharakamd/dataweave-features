package com.dilant.mediator.example.xml;

import com.dilant.mediator.util.PayloadHelper;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class XmlArrayMapperMediator extends AbstractMediator {

    private final OMFactory factory;

    public XmlArrayMapperMediator() {

        factory = OMAbstractFactory.getOMFactory();
    }

    @Override
    public boolean mediate(MessageContext mc) {

        PayloadHelper.getXmlChildElementsStream(mc)
                .forEach(element -> {
                    OMElement firstNameElement = (OMElement) element.getChildrenWithLocalName("FirstName").next();
                    String firstName = firstNameElement.getText();
                    firstNameElement.detach();

                    OMElement lastNameElement = (OMElement) element.getChildrenWithLocalName("LastName").next();
                    String lastName = lastNameElement.getText();
                    lastNameElement.detach();

                    OMElement companyElement = (OMElement) element.getChildrenWithLocalName("Company").next();
                    companyElement.detach();

                    OMElement fullNameElement = factory.createOMElement("FullName", null);
                    fullNameElement.setText(firstName + " " + lastName);
                    element.addChild(fullNameElement);

                    element.addChild(companyElement);

                });

        return true;
    }
}
