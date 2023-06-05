package com.dissertation.requests.controller;

import com.dissertation.requests.service.requestListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class test {
    private requestListService requestListService;

    @Autowired
    public void setRequestListService(requestListService requestListService){
        this.requestListService = requestListService;
    }

    @GetMapping("/api/test")
    public void test1() throws JsonProcessingException {
        requestListService.json();
    }
}
