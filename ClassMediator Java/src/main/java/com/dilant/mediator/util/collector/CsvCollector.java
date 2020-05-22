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

import com.google.common.collect.Sets;
import com.opencsv.CSVWriter;
import org.apache.synapse.MessageContext;
import org.apache.synapse.util.PayloadHelper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.UNORDERED;

public class CsvCollector implements Collector<String[], List<String[]>, Boolean> {

    private final MessageContext mc;

    CsvCollector(MessageContext mc) {
        this.mc = mc;
    }

    @Override
    public Supplier<List<String[]>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<String[]>, String[]> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<String[]>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<String[]>, Boolean> finisher() {
        return rowList -> {
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            csvWriter.writeAll(rowList);
            try {
                csvWriter.close();
                stringWriter.flush();
                String resultPayload = stringWriter.toString();
                PayloadHelper.setTextPayload(mc, resultPayload);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;

        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Sets.immutableEnumSet(UNORDERED);
    }
}