package com.dilant.mediator.example.xml;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import javax.xml.namespace.QName;

public class SelectingXmlMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {

        OMElement rootElement = mc.getEnvelope().getBody().getFirstElement();

        String id = rootElement.getAttribute(new QName("id")).getAttributeValue();
        String type = rootElement.getAttribute(new QName("type")).getAttributeValue();
        String brand = rootElement.getFirstElement().getText();

        JsonObject attributes = new JsonObject();
        attributes.add("id", new JsonPrimitive(id));
        attributes.add("type", new JsonPrimitive(type));

        JsonObject item = new JsonObject();
        item.add("type", new JsonPrimitive(type));
        item.add("name", new JsonPrimitive(brand));
        item.add("attributes", attributes);

        JsonArray items = new JsonArray();
        items.add(item);

        JsonObject root = new JsonObject();
        root.add("item", items);

        try {
            PayloadHelper.setJsonPayload(mc, root.toString());
        } catch (AxisFault axisFault) {
            getLog(mc).error(axisFault);
            return false;
        }

        return true;
    }
}
