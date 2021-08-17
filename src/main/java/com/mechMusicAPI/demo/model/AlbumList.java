package com.mechMusicAPI.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumList implements Serializable {
    private List<Album> albums;

    public AlbumList(List<Album>albums) {
        this.albums = albums;
    }

    public AlbumList() {
        this.albums = new ArrayList<>();
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(albums.size());
        albums.forEach(album -> {
                output.append(albums);
        });
        return output.toString();
    }
}
