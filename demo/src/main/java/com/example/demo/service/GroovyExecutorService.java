package com.example.demo.service;

import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Service
public class GroovyExecutorService {

    @Value("${groovy_file_path}")
    private String groovyFilePath;

    public Object evaluate(String payload, String scriptName, PayloadType payloadType) {
        if (payloadType == PayloadType.JSON) {
            return executeJsonPayload(payload, scriptName);
        } else {
            return executeXmlPayload(payload, scriptName);
        }
    }

    private Object executeJsonPayload(String json, String fileName) {

        Object parsedJson = parseJson(json);

        Binding binding = new Binding();
        binding.setProperty("payload", parsedJson);

        GroovyShell shell = new GroovyShell(binding);

        try {
            return shell.evaluate(new File(groovyFilePath + fileName + ".groovy"));
        } catch (IOException e) {
            throw new RuntimeException("Error loading groovy file");
        }
    }

    private Object executeXmlPayload(String xml, String fileName) {
        try {
            GPathResult gPathResult = new XmlSlurper().parseText(xml);

            Binding binding = new Binding();
            binding.setProperty("payload", gPathResult);

            GroovyShell shell = new GroovyShell(binding);

            try {
                return shell.evaluate(new File(groovyFilePath + fileName + ".groovy"));
            } catch (IOException e) {
                throw new RuntimeException("Error loading groovy file");
            }

        } catch (IOException e) {
            throw new RuntimeException("Error parsing xml", e);
        } catch (SAXException e) {
            throw new RuntimeException("Error parsing xml", e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error parsing xml", e);
        }
    }

    private Object parseJson(String json) {
        JsonSlurper slurper = new JsonSlurper();
        return slurper.parseText(json);
    }

}