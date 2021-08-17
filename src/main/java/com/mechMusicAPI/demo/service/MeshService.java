package com.mechMusicAPI.demo.service;

import com.mechMusicAPI.demo.model.AlbumList;
import com.mechMusicAPI.demo.model.MusicObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class MeshService {
    String musicBrainzUrl = "http://musicbrainz.org/ws/2/artist/";
    String wikipediaUrl = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";
    private final RestTemplate restTemplate;

    public MeshService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public MusicObject meshApi(String id){
        MusicObject mu = null;
        try{
            mu = getMusicBrainzArtistObject(id).get();
            mu = getWikiDesc(id, mu).get();
        }catch(InterruptedException | ExecutionException e){
            System.out.println("Error musicBrainz");
        }

        return mu;
    }

    @Async
    public CompletableFuture<MusicObject> getMusicBrainzArtistObject(String id) {
        String url = musicBrainzUrl + id +"?&fmt=json&inc=url-rels+release-groups";
        MusicObject results = this.restTemplate.getForObject(url, MusicObject.class);
        AlbumList albumList = this.restTemplate.getForObject(url,AlbumList.class);
        results.setAlbums(CompletableFuture.completedFuture(albumList).join());
        return CompletableFuture.completedFuture(results);
    }

    @Async
    public CompletableFuture<MusicObject> getWikiDesc(String id,MusicObject m){
        String url = wikipediaUrl + m.getName();
        String results = this.restTemplate.getForObject(url, String.class);
        m.setDesc(results.split("\"extract\"")[1]);
        return CompletableFuture.completedFuture(m);
    }
}

