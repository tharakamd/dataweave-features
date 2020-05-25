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
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;

import java.io.StringReader;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ExtendedMessageContext extends Axis2MessageContext {

    public ExtendedMessageContext(MessageContext axisMsgCtx) {
        super(((Axis2MessageContext) axisMsgCtx).getAxis2MessageContext(), axisMsgCtx.getConfiguration(), axisMsgCtx.getEnvironment());
    }

    public Stream<JsonElement> getJsonArrayStream() {
        return PayloadHelper.getJsonArrayStream(this);
    }

    public Stream<Map.Entry<String, JsonElement>> getJsonObjectStream() {
        JsonObject jsonObject = PayloadHelper.getPayloadJsonObject(this);
        return PayloadHelper.getJsonObjectStream(jsonObject);
    }

    public Stream<String[]> getCsvArrayStream(int linesToSkip) {
        String csvText = org.apache.synapse.util.PayloadHelper.getTextPayload(this);
        CSVReader csvReader = new CSVReaderBuilder(new StringReader(csvText)).withSkipLines(linesToSkip).build();
        return StreamSupport.stream(csvReader.spliterator(), false);
    }

    public void setJsonPayload(JsonElement jsonPayload) {
        String transformedJson = jsonPayload.toString();
        setJsonPayload(transformedJson);
    }

    public void setJsonPayload(String payload) {

        org.apache.axis2.context.MessageContext axis2MessageContext = this.getAxis2MessageContext();
        axis2MessageContext.setProperty("messageType", "application/json");
        axis2MessageContext.setProperty("ContentType", "application/json");

        try {
            JsonUtil.getNewJsonPayload(this.getAxis2MessageContext(),
                    payload, true, true);
        } catch (AxisFault axisFault) {
            throw new MCException(axisFault);
        }
    }

    public Stream<OMElement> getXmlChildElementsStream(MessageContext mc) {
        OMElement rootElement = mc.getEnvelope().getBody().getFirstElement();
        return PayloadHelper.getXmlChildElementsStream(rootElement);
    }
}
