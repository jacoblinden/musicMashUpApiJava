package com.mechMusicAPI.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageUrlList implements Serializable {
    @JsonProperty("images")
    private List <ImageURl> imageURls;

    public ImageUrlList() {
        this.imageURls = new ArrayList<>();
    }

    public ImageUrlList(List <ImageURl> imageURls) {
        this.imageURls = imageURls;
    }

    public Optional<ImageURl> getContent() {
        return imageURls.stream().findFirst();
    }

    public void setImageURls(List<ImageURl> imageURls) {
        this.imageURls = imageURls;
    }
}
