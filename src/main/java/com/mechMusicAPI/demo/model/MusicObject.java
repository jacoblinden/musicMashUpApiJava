package com.mechMusicAPI.demo.model;

import java.io.Serializable;
import java.util.List;

public class MusicObject implements Serializable {
    private String name;
    private String id;
    private AlbumList albums;
    private String desc;

    public void setAlbums(AlbumList albums) {
        this.albums = albums;
    }

    public MusicObject(String name, String id, AlbumList albums) {
        this.name = name;
        this.id = id;
        this.albums= albums;
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
                ", album='" + albums + '\'' +
                '}';
    }
}
