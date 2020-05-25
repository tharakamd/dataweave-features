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

package com.dilant.mediator.example.enhanced.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class FilterObjectMediator extends AbstractMediator {

    private final JsonParser parser;

    public FilterObjectMediator() {

        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {

        String jsonPayload = JsonUtil.jsonPayloadToString(((Axis2MessageContext) mc).getAxis2MessageContext());
        JsonObject jsonObject = parser.parse(jsonPayload).getAsJsonObject();

        JsonObject result = new JsonObject();
        jsonObject.entrySet().stream()
                .filter(objectEntry -> objectEntry.getValue().getAsInt() >= 2)
                .forEach(entry -> result.add(entry.getKey(), entry.getValue()));

        String transformedJson = result.toString();
        JsonUtil.newJsonPayload(
                ((Axis2MessageContext) mc).getAxis2MessageContext(),
                transformedJson, true, true);

        return true;
    }

}
