package com.dilant.mediator.example.enhanced.xml;

import com.dilant.mediator.util.extender.AbstractExtendedMediator;
import com.dilant.mediator.util.extender.SimpleMessageContext;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;

public class XmlArrayMapperMediatorExtendedMD extends AbstractExtendedMediator {

    private final OMFactory factory;

    public XmlArrayMapperMediatorExtendedMD() {
        factory = OMAbstractFactory.getOMFactory();
    }

    @Override
    public void mediate(SimpleMessageContext mc) {

        mc.getXmlChildElementsStream()
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
    }
}
