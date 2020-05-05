package com.example.demo.controller;

import com.example.demo.service.GroovyExecutorService;
import com.example.demo.service.OutputFormatter;
import com.example.demo.service.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    private GroovyExecutorService groovyExecutorService;
    @Autowired
    private OutputFormatter outputFormatter;

    @PostMapping(path = "/{file_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity handleRequest(@RequestBody final String payload, @PathVariable(value = "file_name") String fileName, @RequestParam(value = "type", defaultValue = "JSON") PayloadType payloadType) {
        Object outputOjb = groovyExecutorService.evaluate(payload, fileName, payloadType);
        Object jsonString = outputFormatter.formatOutput(outputOjb);
        return new ResponseEntity(jsonString, HttpStatus.ACCEPTED);
    }
}
