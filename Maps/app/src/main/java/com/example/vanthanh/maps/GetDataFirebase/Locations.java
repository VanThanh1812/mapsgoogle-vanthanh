package com.example.vanthanh.maps.GetDataFirebase;

/**
 * Created by Van Thanh on 7/1/2016.
 */
public class Locations {
    private String name;
    private double log;
    private double lat;
    private String snippet;
    private double arc;
    public Locations() {
    }

    public Locations(String name, double log, double lat, String snippet, double arc) {
        this.name = name;
        this.log = log;
        this.lat = lat;
        this.snippet = snippet;
        this.arc = arc;
    }

    public double getArc() {
        return arc;
    }

    public void setArc(double arc) {
        this.arc = arc;
    }

    public String getSnippet() {
        return snippet;
    }

    public Locations(double log, double lat) {
        this.log = log;
        this.lat = lat;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Locations(String name, double log, double lat, String snippet) {
        this.name = name;
        this.log = log;
        this.lat = lat;
        this.snippet = snippet;
    }

    public Locations(String name, double log, double lat) {
        this.name = name;
        this.log = log;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
