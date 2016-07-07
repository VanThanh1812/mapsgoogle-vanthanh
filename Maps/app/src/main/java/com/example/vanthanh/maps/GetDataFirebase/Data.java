package com.example.vanthanh.maps.GetDataFirebase;

/**
 * Created by Van Thanh on 7/4/2016.
 */
public class Data {
    private String link;
    private String name;
    private String lat;
    private String log;
    private String arc;
    private String snippet;

    public Data() {
    }

    public Data(String name, String lat, String log, String arc, String snippet) {
        this.name = name;
        this.lat = lat;
        this.log = log;
        this.arc = arc;
        this.snippet = snippet;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getArc() {
        return arc;
    }

    public void setArc(String arc) {
        this.arc = arc;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Data(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
