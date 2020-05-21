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
import java.util.stream.Collectors;

public class RegroupFieldsXmlMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        OMElement teachersElement = mc.getEnvelope().getBody().getFirstElement().getFirstChildWithName(new QName("teachers"));

        JsonObject root = new JsonObject();
        JsonArray classrooms = new JsonArray();

        PayloadHelper.getXmlChildElementsStream(teachersElement)
                .collect(Collectors.groupingBy(teacherElement -> teacherElement.getFirstChildWithName(new QName("subject")).getText()))
                .forEach((subjectName, teachersElementList) -> {
                    JsonObject classObject = new JsonObject();
                    classObject.add("name", new JsonPrimitive(subjectName));

                    JsonArray teachersArray = new JsonArray();
                    teachersElementList.forEach(element -> {
                        JsonObject teacherObject = new JsonObject();
                        teacherObject.add("name", new JsonPrimitive(element.getFirstChildWithName(new QName("name")).getText()));
                        teacherObject.add("lastName", new JsonPrimitive(element.getFirstChildWithName(new QName("lastName")).getText()));
                        teachersArray.add(teacherObject);
                    });

                    classObject.add("teachers", teachersArray);

                    classrooms.add(classObject);

                });

        root.add("classrooms", classrooms);

        try {
            PayloadHelper.setJsonPayloadToXmlContext(mc, root);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        return true;
    }
}
