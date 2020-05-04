package com.example.demo.service;

import groovy.json.JsonSlurper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GroovyExecutorService {

    @Value("${groovy_file_path}")
    private String groovyFilePath;

    public Object evaluateWithGroovy(String json, String fileName) {

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

    private Object parseJson(String json) {
        JsonSlurper slurper = new JsonSlurper();
        return slurper.parseText(json);
    }

}