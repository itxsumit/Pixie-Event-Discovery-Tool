package com.pixie;

public class Event {

    private String name;
    private String city;
    private String url;
    private String status;

    public Event(String name, String city, String url, String status) {
        this.name = name;
        this.city = city;
        this.url = url;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }
}
