package com.mechMusicAPI.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicObject implements Serializable {
    private String name;
    private String id;
    @JsonProperty("release-groups")
    private ArrayList<ReleaseGroup> releaseGroups;
    private String desc;

    public MusicObject(String name, String id) {
        this.name = name;
        this.id = id;
        this.releaseGroups = new ArrayList<>();
    }

    public void setReleaseGroups(ArrayList<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    public void removeNoneAlbumTypesFromList(){
        releaseGroups.removeIf(releaseGroup -> releaseGroup.getType() == null || !releaseGroup.getType().equals("Album"));
    }

    public ArrayList<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MusicObject{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", releasegroups='" + releaseGroups + '\'' +
                '}';
    }
}
