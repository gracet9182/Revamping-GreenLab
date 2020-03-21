package com.example.green;

public class Event {
    private String date;
    private String time;
    private String name;
    private String location;

    public Event(String date, String time, String name, String location) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.location = location;
    }

    public Event(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
