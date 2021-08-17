package com.mechMusicAPI.demo.api;

import com.mechMusicAPI.demo.service.MeshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "music")
public class controller {
    @Autowired
    MeshService meshService;

    @GetMapping(path = "{id}")
    public String test(@PathVariable String id) {
        return meshService.meshApi(id).toString();
    }


}
