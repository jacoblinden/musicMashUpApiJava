package com.mechMusicAPI.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ImageURl implements Serializable {
    @JsonProperty("image")
    private String url;

    public ImageURl(String url) {
        this.url = url;
    }

    public ImageURl() {
        this.url = "fail";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
