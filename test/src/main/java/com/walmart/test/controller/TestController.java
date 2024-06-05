package com.walmart.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.test.service.TestService;
import com.walmart.test.vo.ArrayRequest;
import com.walmart.test.vo.ArrayResponse;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/api/searchArrayFull")
    public ResponseEntity<ArrayResponse> searchArray(@RequestBody ArrayRequest arrays) {
        if (arrays.getArrayA() == null || arrays.getArrayB() == null || arrays.getArrayA().isEmpty() || arrays.getArrayB().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<String> arrayC = testService.compareArrays(arrays.getArrayA(), arrays.getArrayB());
        ArrayResponse response = new ArrayResponse();
        response.setResults(arrayC);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/searchArray")
    public ResponseEntity<ArrayResponse> searchArray2(@RequestBody ArrayRequest arrays) {
        if (arrays.getArrayA() == null || arrays.getArrayB() == null || arrays.getArrayA().isEmpty() || arrays.getArrayB().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<String> arrayC = testService.compareArraysFilterByFrequency(arrays.getArrayA(), arrays.getArrayB(), 2);
        ArrayResponse response = new ArrayResponse();
        response.setResults(arrayC);

        return ResponseEntity.ok(response);
    }
}

