package com.ApiRest.RestApi.restController;


import com.ApiRest.RestApi.entities.HeroInteraction;
import com.ApiRest.RestApi.entities.HeroInteractionWrapper;
import com.ApiRest.RestApi.service.ComicRepositoryServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comic")
@Slf4j
public class ComicController {

    private final ComicRepositoryServiceInterface comicService;
    @Autowired
    public ComicController(ComicRepositoryServiceInterface comicService){
        this.comicService = comicService;
    }

    @GetMapping("/characters/ironman")
    public ResponseEntity<HeroInteractionWrapper> allInteractions(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){

        log.info("Finding information for all hero Interactions");
        HeroInteractionWrapper findInteractions = comicService.getInteractionsByHero(1009368, pageNo, pageSize);
        if(findInteractions== null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<HeroInteractionWrapper>(findInteractions, HttpStatus.OK);
    }
}
