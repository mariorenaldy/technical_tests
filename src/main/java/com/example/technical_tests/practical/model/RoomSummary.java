package com.example.technical_tests.practical.model;

import lombok.Data;

@Data
public class RoomSummary {
    private String roomName;
    private int totalConsumptionAmount;
    private int snackSiangCount;
    private int makanSiangCount;
    private int snackSoreCount;
}
