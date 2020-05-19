package com.dilant.mediator.example;

import com.dilant.mediator.util.JsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.util.xpath.SynapseJsonPath;
import org.jaxen.JaxenException;

import java.util.ArrayList;

public class MappingUsingSelectorsMediator extends AbstractMediator {
    private final JsonParser parser;

    public MappingUsingSelectorsMediator() {
        parser = new JsonParser();
    }

    @Override
    public boolean mediate(MessageContext mc) {
        try {
            SynapseJsonPath synapseJsonPath = new SynapseJsonPath("$.accountType[*].users");
            Object obj = synapseJsonPath.evaluate(mc);

            JsonArray newArray = new JsonArray();

            if (obj instanceof ArrayList) {
                for (Object listElement : (ArrayList) obj) {
                    if (listElement instanceof JsonArray) {
                        for (JsonElement jsonElement : (JsonArray) listElement) {
                            newArray.add(jsonElement);
                        }
                    }
                }
            }

            JsonObject obj1 = new JsonObject();
            obj1.add("accountInfo", newArray);

            JsonArray arr1 = new JsonArray();
            arr1.add(obj1)
            ;
            JsonHelper.setJsonPayload(mc, arr1);

        } catch (JaxenException e) {
            e.printStackTrace();
        }

        return true;
    }
}
