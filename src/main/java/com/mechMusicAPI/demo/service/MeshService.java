package com.mechMusicAPI.demo.service;

import com.mechMusicAPI.demo.model.ImageURl;
import com.mechMusicAPI.demo.model.ImageUrlList;
import com.mechMusicAPI.demo.model.MusicObject;
import com.mechMusicAPI.demo.model.ReleaseGroup;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MeshService {
    String musicBrainzUrl = "http://musicbrainz.org/ws/2/artist/";
    String wikipediaUrl = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=";
    String coverArtUrl = "http://coverartarchive.org/release-group/";
    private final RestTemplate restTemplate;

    public MeshService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public MusicObject meshApi(String id){
        MusicObject mu = null;
        mu = getMusicBrainzArtistObject(id).join();
        mu.removeNoneAlbumTypesFromList();
        setReleaseImage(mu.getReleaseGroups());
        return mu;
    }

    private void setReleaseImage(List <ReleaseGroup> releaseGroups) {
        releaseGroups.forEach(releaseGroup -> {
            String url = coverArtUrl + releaseGroup.getId();
            ImageUrlList results = new ImageUrlList();
            try {
                results = this.restTemplate.getForObject(url, ImageUrlList.class);
            }catch(HttpClientErrorException e){
                System.out.print("Image not found for:" + releaseGroup.getName());
            }
            Optional<ImageURl> optionalImgUrl= results.getContent();
            if(optionalImgUrl.isPresent()){
                releaseGroup.setUrlImage(optionalImgUrl.get());
            }else{
                releaseGroup.setUrlImage(new ImageURl("no img"));
            }
        });
    }

    @Async
    public CompletableFuture<MusicObject> getMusicBrainzArtistObject(String id) {
        String url = musicBrainzUrl + id +"?&fmt=json&inc=url-rels+release-groups";
        MusicObject results = this.restTemplate.getForObject(url, MusicObject.class);
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

