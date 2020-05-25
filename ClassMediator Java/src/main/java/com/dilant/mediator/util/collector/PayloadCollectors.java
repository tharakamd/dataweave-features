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

package com.dilant.mediator.util.collector;

import com.dilant.mediator.util.extender.SimpleMessageContext;
import org.apache.synapse.MessageContext;

/**
 * Generate instances of the Payload Collectors
 */
public class PayloadCollectors {

    private PayloadCollectors() {

    }

    public static JsonArrayCollector toJsonArray(MessageContext mc) {

        return new JsonArrayCollector(mc);
    }

    public static JsonArrayCollector toJsonArray(SimpleMessageContext simpleMessageContext) {

        return new JsonArrayCollector(simpleMessageContext);
    }

    public static JsonObjectCollector toJsonObject(MessageContext mc) {

        return new JsonObjectCollector(mc);
    }

    public static JsonObjectCollector toJsonObject(SimpleMessageContext simpleMessageContext) {

        return new JsonObjectCollector(simpleMessageContext);
    }

    public static CsvCollector toCsv(MessageContext mc) {

        return new CsvCollector(mc);
    }

    public static CsvCollector toCsv(SimpleMessageContext simpleMessageContext) {

        return new CsvCollector(simpleMessageContext);
    }
}
