package com.example.mtms_task;

public class SourceLocationModel {

    private String name;
    private double latitude;
    private double longitude;

    public SourceLocationModel() {
    }

    public SourceLocationModel(String name){
        this.name = name;
    }

    public SourceLocationModel(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
