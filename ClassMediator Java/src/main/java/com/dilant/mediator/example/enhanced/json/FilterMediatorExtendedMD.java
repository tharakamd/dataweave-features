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

import com.dilant.mediator.util.collector.PayloadCollectors;
import com.dilant.mediator.util.extender.AbstractExtendedMediator;
import com.dilant.mediator.util.extender.ExtendedMessageContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class FilterMediatorExtendedMD extends AbstractExtendedMediator {
    @Override
    public void mediate(ExtendedMessageContext mc) {

        mc.getJsonArrayStream()
                .map(JsonElement::getAsInt)
                .filter(item -> item >= 2)
                .map(JsonPrimitive::new)
                .collect(PayloadCollectors.toJsonArray(mc));
    }
}