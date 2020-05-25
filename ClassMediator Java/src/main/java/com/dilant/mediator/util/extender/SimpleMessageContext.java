/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package com.dilant.mediator.util.extender;

import com.dilant.mediator.util.PayloadHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;

import java.io.StringReader;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SimpleMessageContext {

    private final MessageContext messageContext;

    protected SimpleMessageContext(MessageContext messageContext) {

        this.messageContext = messageContext;
    }

    /**
     * Get the payload as a Stream of JsonElement if payload is a Json Array
     *
     * @return Stream of JsonElement
     */
    public Stream<JsonElement> getJsonArrayStream() {

        return PayloadHelper.getJsonArrayStream(messageContext);
    }

    /**
     * If the payload is a Json Object, get the payload as a Map.Entry,key being the attribute key and value being
     * the attribute value
     *
     * @return Stream of Map.Entry with object attribute name as the key
     * and attribute value as value
     */
    public Stream<Map.Entry<String, JsonElement>> getJsonObjectStream() {

        JsonObject jsonObject = PayloadHelper.getPayloadJsonObject(messageContext);
        return PayloadHelper.getJsonObjectStream(jsonObject);
    }

    /**
     * If the payload is a CSV, get the payload as a Stream of Array of String for each line in CSV file
     *
     * @param linesToSkip Number of lines to skip from header of CSV
     * @return Stream of Array of String representing each line of the CSV payload
     */
    public Stream<String[]> getCsvArrayStream(int linesToSkip) {

        String csvText = org.apache.synapse.util.PayloadHelper.getTextPayload(messageContext);
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(csvText)).withSkipLines(linesToSkip).build();
        return StreamSupport.stream(csvReader.spliterator(), false);
    }

    /**
     * Set given JsonElement as the Json payload of. If the current payload type is not Json, the payload type would
     * be set as Json
     *
     * @param jsonPayload JsonElement to set as the current payload
     */
    public void setJsonPayload(JsonElement jsonPayload) {

        String transformedJson = jsonPayload.toString();
        setJsonPayload(transformedJson);
    }

    /**
     * Set given String as the Json payload of. If the current payload type is not Json, the payload type would be
     * set to Json
     *
     * @param payload String to set as the current payload
     */
    public void setJsonPayload(String payload) {

        org.apache.axis2.context.MessageContext axis2MessageContext =
                ((Axis2MessageContext) messageContext).getAxis2MessageContext();
        axis2MessageContext.setProperty("messageType", "application/json");
        axis2MessageContext.setProperty("ContentType", "application/json");

        try {
            JsonUtil.getNewJsonPayload(((Axis2MessageContext) messageContext).getAxis2MessageContext(),
                    payload, true, true);
        } catch (AxisFault axisFault) {
            throw new MCException(axisFault);
        }
    }

    /**
     * If the payload is type XML, then get the child elements of the root XML element as a Stream of OMElement
     *
     * @return Stream of OMElement of the child elements of the root xml element
     */
    public Stream<OMElement> getXmlChildElementsStream() {

        OMElement rootElement = messageContext.getEnvelope().getBody().getFirstElement();
        return PayloadHelper.getXmlChildElementsStream(rootElement);
    }

    /**
     * Set the current payload to the given text
     *
     * @param text String to set as the current payload
     */
    public void setTextPayload(String text) {

        if (messageContext.getEnvelope() == null) {
            try {
                messageContext.setEnvelope(OMAbstractFactory.getSOAP12Factory()
                        .createSOAPEnvelope());
            } catch (Exception e) {
                throw new SynapseException(e);
            }
        }
        org.apache.synapse.util.PayloadHelper.setTextPayload(messageContext.getEnvelope(), text);
    }
}
