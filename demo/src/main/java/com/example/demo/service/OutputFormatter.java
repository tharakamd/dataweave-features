package com.example.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class OutputFormatter {

    ObjectMapper objectMapper = new ObjectMapper();

    public String formatOutput(Object outputObj) {
        try {
            return objectMapper.writeValueAsString(outputObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting to json", e);
        }
    }
}
