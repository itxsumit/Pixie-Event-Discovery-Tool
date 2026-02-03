package com.pixie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class EventScraper {
    public List<String[]> fetchEvents(String city) {
        List<String[]> eventList = new ArrayList<>();
        String url = "https://in.bookmyshow.com/explore/events-" + city;

        try {
            // 403 Forbidden fix: Adding User-Agent and Referrer
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .referrer("https://www.google.com/")
                    .timeout(10000)
                    .get();

            // BookMyShow ke event links extract karna
            Elements links = doc.select("a[href*='/events/']");

            for (Element link : links) {
                String title = link.text().trim();
                String eventUrl = link.attr("abs:href");

                if (!title.isEmpty() && eventUrl.contains("/events/")) {
                    // Format: Name, Status, City, URL
                    eventList.add(new String[]{title, "Upcoming", city, eventUrl});
                }
            }
        } catch (Exception e) {
            System.out.println("Error in scraping: " + e.getMessage());
        }
        
        return eventList;
    }
}