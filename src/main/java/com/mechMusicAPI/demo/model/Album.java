package com.mechMusicAPI.demo.model;

import java.io.Serializable;

public class Album implements Serializable {
    private String title;
    private String date;

    public Album(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getName() {
        return title;
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
        return "Album{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
