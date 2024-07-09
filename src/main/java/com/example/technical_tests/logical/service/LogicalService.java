package com.example.technical_tests.logical.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogicalService {

    public String convertToReadable(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            String reversedWord = new StringBuilder(word).reverse().toString();
            result.append(reversedWord).append(" ");
        }
        return result.toString().trim();
    }

    public String fizzbuzz() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.append("FizzBuzz<br>");
            } else if (i % 3 == 0) {
                result.append("Fizz<br>");
            } else if (i % 5 == 0) {
                result.append("Buzz<br>");
            } else {
                result.append(i).append("<br>");
            }
        }
        return result.toString();
    }

    public List<Integer> fibonacci(int n) {
        List<Integer> fibSeries = new ArrayList<>();
        if (n > 0) fibSeries.add(0);
        if (n > 1) fibSeries.add(1);
        while (fibSeries.size() < n) {
            int size = fibSeries.size();
            fibSeries.add(fibSeries.get(size - 1) + fibSeries.get(size - 2));
        }
        return fibSeries;
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return minPrice;
    }

    public int countNumbers(List<String> str) {
        int counts = 0;
        for (String s : str) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    counts++;
                }
            }
        }
        return counts;
    }
}
