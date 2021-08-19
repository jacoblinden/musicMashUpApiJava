package com.mechMusicAPI.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ReleaseGroup implements Serializable {
    private String title;
    private String date;
    private String id;
    @JsonProperty("primary-type")
    private String type;
    private ImageURl imageUrl;

    public ReleaseGroup(String title, String date, String id,String type) {
            this.title = title;
            this.date = date;
            this.id =  id;
            this.type = type != null ? type : "unknown";
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setUrlImage(ImageURl imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.title = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReleaseGroup{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl=" + imageUrl+
                '}';
    }
}
