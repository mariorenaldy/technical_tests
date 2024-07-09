package com.example.technical_tests.practical.controller;

import com.example.technical_tests.practical.model.RoomSummary;
import com.example.technical_tests.practical.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary")
    public Map<String, Map<String, List<RoomSummary>>> getBookingSummary() {
        return summaryService.getBookingSummary();
    }
}
