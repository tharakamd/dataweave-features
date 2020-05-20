package com.dilant.mediator.example;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RegroupFieldsXmlMediator extends AbstractMediator {

    @Override
    public boolean mediate(MessageContext mc) {
        OMElement teachersElement = mc.getEnvelope().getBody().getFirstElement().getFirstChildWithName(new QName("teachers"));

        Iterable<OMElement> iterable = teachersElement::getChildElements;
        Map<String, List<OMElement>> subjectMap = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.groupingBy(teacherElement -> teacherElement.getFirstChildWithName(new QName("subject")).getText()));

        JsonObject root = new JsonObject();

        JsonArray classrooms = new JsonArray();

        subjectMap.forEach((subjectName, teachersElementList) -> {
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


        org.apache.axis2.context.MessageContext axis2MessageContext = ((Axis2MessageContext) mc).getAxis2MessageContext();
        axis2MessageContext.setProperty("messageType", "application/json");
        axis2MessageContext.setProperty("ContentType", "application/json");

        try {
            JsonHelper.setJsonPayload2(mc, root.toString());
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }

        return true;
    }
}
