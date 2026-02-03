package com.pixie;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Pixie Event Discovery Tool ===");
        System.out.print("Enter City (e.g., jaipur, mumbai): ");
        String city = sc.nextLine().trim().toLowerCase();

        EventScraper scraper = new EventScraper();
        System.out.println("Fetching events for " + city + "... Please wait.");
        
        List<String[]> events = scraper.fetchEvents(city);

        if (events != null && !events.isEmpty()) {
            ExcelManager.saveToExcel(events, city);
            System.out.println("\nSUCCESS: " + events.size() + " events found and saved!");
        } else {
            System.out.println("\nFAILED: No events found. Check your internet or if the city name is correct.");
        }
        sc.close();
    }
}