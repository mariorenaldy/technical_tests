package com.example.technical_tests.logical.controller;

import com.example.technical_tests.logical.service.LogicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogicalController {

    @Autowired
    LogicalService service;

    @GetMapping("/readable")
    public String convertToReadable(@RequestParam String s) {
        return service.convertToReadable(s);
    }

    @GetMapping("/fizzbuzz")
    public String fizzbuzz() {
        return service.fizzbuzz();
    }

    @GetMapping("/fibonacci")
    public List<Integer> fibonacci(@RequestParam int n) {
        return service.fibonacci(n);
    }

    @GetMapping("/maxProfit")
    public int maxProfit(@RequestParam int[] prices) {
        return service.maxProfit(prices);
    }

    @GetMapping("/countNumbers")
    public int countNumbers(@RequestParam List<String> str) {
        return service.countNumbers(str);
    }
}
