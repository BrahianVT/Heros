package com.ApiRest.RestApi.restController;


import com.ApiRest.RestApi.entities.HeroCollaborator;
import com.ApiRest.RestApi.entities.HeroInteractionWrapper;
import com.ApiRest.RestApi.service.ComicRepositoryServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/comic")
@Slf4j
public class ComicController {

    Map<String, Integer> map = new HashMap<String, Integer>();

    private final ComicRepositoryServiceInterface comicService;
    @Autowired
    public ComicController(ComicRepositoryServiceInterface comicService){
        this.comicService = comicService;
        map.put("capamerica",1009220 ); map.put("ironman",1009368 );
    }

    @GetMapping("/characters/ironman")
    public ResponseEntity<HeroInteractionWrapper> allInteractionsIronMan(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        log.info("Finding information for all hero Interactions with IronMan");
        HeroInteractionWrapper findInteractions = comicService.getInteractionsByHero(1009368, pageNo, pageSize);
        if(findInteractions== null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<HeroInteractionWrapper>(findInteractions, HttpStatus.OK);
    }

    @GetMapping("/characters/capamerica")
    public ResponseEntity<HeroInteractionWrapper> allInteractionsCapAmerica(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        log.info("Finding information for all hero Interactions with CapAmerica");
        HeroInteractionWrapper findInteractions = comicService.getInteractionsByHero(1009220, pageNo, pageSize);
        if(findInteractions == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<HeroInteractionWrapper>(findInteractions, HttpStatus.OK);
    }

    @GetMapping("/colaborators/{nameHero}")
    public ResponseEntity<HeroCollaborator> allInteractionsCapAmerica(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @PathVariable String nameHero){
        log.info("Finding collaborators hero" + nameHero);
        HeroCollaborator findCollaborators = comicService.getHeroCollaboratorByHero(map.get(nameHero), pageNo, pageSize);

        if(findCollaborators == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<HeroCollaborator>(findCollaborators, HttpStatus.OK);
    }

}
