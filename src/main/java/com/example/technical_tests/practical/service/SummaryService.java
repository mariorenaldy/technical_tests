package com.example.technical_tests.practical.service;

import com.example.technical_tests.practical.model.Booking;
import com.example.technical_tests.practical.model.ConsumptionType;
import com.example.technical_tests.practical.model.RoomSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SummaryService {

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Map<String, List<RoomSummary>>> getBookingSummary() {
        String bookingListUrl = "https://66876cc30bc7155dc017a662.mockapi.io/api/dummy-data/bookingList";
        String masterJenisKonsumsiUrl = "https://6686cb5583c983911b03a7f3.mockapi.io/api/dummy-data/masterJenisKonsumsi";

        List<Booking> bookings = Collections.emptyList();
        List<ConsumptionType> consumptionTypes = Collections.emptyList();

        try {
            bookings = List.of(restTemplate.getForObject(bookingListUrl, Booking[].class));
            System.out.println("Bookings fetched");
        } catch (ResourceAccessException e) {
            System.out.println("Failed to access bookingList API: " + e.getMessage());
        }

        try {
            consumptionTypes = List.of(restTemplate.getForObject(masterJenisKonsumsiUrl, ConsumptionType[].class));
            System.out.println("Consumption types fetched");
        } catch (ResourceAccessException e) {
            System.out.println("Failed to access masterJenisKonsumsi API: " + e.getMessage());
        }

        Map<String, Integer> consumptionPriceMap = new HashMap<>();
        for (ConsumptionType type : consumptionTypes) {
            consumptionPriceMap.put(type.getName(), type.getMaxPrice());
        }

        Map<String, Map<String, Map<String, RoomSummary>>> roomSummaryMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        for (Booking booking : bookings) {
            LocalDate bookingDate = LocalDate.parse(booking.getBookingDate(), formatter);
            String monthYear = bookingDate.getMonthValue() + "-" + bookingDate.getYear();
            String officeName = booking.getOfficeName();
            String roomName = booking.getRoomName();

            roomSummaryMap.putIfAbsent(monthYear, new HashMap<>());
            roomSummaryMap.get(monthYear).putIfAbsent(officeName, new HashMap<>());
            RoomSummary summary = roomSummaryMap.get(monthYear).get(officeName).getOrDefault(roomName, new RoomSummary());
            summary.setRoomName(roomName);

            int totalConsumption = 0;
            int snackSiangCount = 0;
            int makanSiangCount = 0;
            int snackSoreCount = 0;

            for (Booking.Consumption consumption : booking.getListConsumption()) {
                int price = consumptionPriceMap.getOrDefault(consumption.getName(), 0);
                totalConsumption += price * booking.getParticipants();
                switch (consumption.getName()) {
                    case "Snack Siang":
                        snackSiangCount += booking.getParticipants();
                        break;
                    case "Makan Siang":
                        makanSiangCount += booking.getParticipants();
                        break;
                    case "Snack Sore":
                        snackSoreCount += booking.getParticipants();
                        break;
                }
            }

            summary.setTotalConsumptionAmount(summary.getTotalConsumptionAmount() + totalConsumption);
            summary.setSnackSiangCount(summary.getSnackSiangCount() + snackSiangCount);
            summary.setMakanSiangCount(summary.getMakanSiangCount() + makanSiangCount);
            summary.setSnackSoreCount(summary.getSnackSoreCount() + snackSoreCount);

            roomSummaryMap.get(monthYear).get(officeName).put(roomName, summary);
        }

        Map<String, Map<String, List<RoomSummary>>> groupedSummary = new HashMap<>();
        for (Map.Entry<String, Map<String, Map<String, RoomSummary>>> entry : roomSummaryMap.entrySet()) {
            String monthYear = entry.getKey();
            Map<String, Map<String, RoomSummary>> officeGroupedSummaries = entry.getValue();

            Map<String, List<RoomSummary>> officeSummaries = new HashMap<>();
            for (Map.Entry<String, Map<String, RoomSummary>> officeEntry : officeGroupedSummaries.entrySet()) {
                String officeName = officeEntry.getKey();
                Map<String, RoomSummary> summariesByRoom = officeEntry.getValue();

                officeSummaries.put(officeName, new ArrayList<>(summariesByRoom.values()));
            }

            groupedSummary.put(monthYear, officeSummaries);
        }

        System.out.println("Grouped summary returned");

        return groupedSummary;
    }
}
