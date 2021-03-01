package com.ApiRest.RestApi.restController;


import com.ApiRest.RestApi.entities.HeroCollaborator;
import com.ApiRest.RestApi.entities.HeroInteractionWrapper;
import com.ApiRest.RestApi.service.ComicServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa los Servicios Rest para el servicio 1 y Servicio 2
 * @author BrahianVT
 * */
@RestController
@RequestMapping("api/comic")
@Slf4j
public class ComicController {

    Map<String, Integer> map = new HashMap<String, Integer>();

    private final ComicServiceInterface comicService;
    @Autowired
    public ComicController(ComicServiceInterface comicService){
        this.comicService = comicService;
        map.put("capamerica",1009220 ); map.put("ironman",1009368 );
    }

    @GetMapping("/characters/{nameHero}")
    public ResponseEntity<HeroInteractionWrapper> allInteractionsByHero(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @PathVariable String nameHero){
        log.info("Finding information for all hero Interactions with" + nameHero);
        HeroInteractionWrapper findInteractions = comicService.getInteractionsByHero(map.get(nameHero), pageNo, pageSize);
        if(findInteractions== null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<HeroInteractionWrapper>(findInteractions, HttpStatus.OK);
    }

    @GetMapping("/colaborators/{nameHero}")
    public ResponseEntity<HeroCollaborator> getHeroCollaboratorByHero(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @PathVariable String nameHero){
        log.info("Finding collaborators hero" + nameHero);
        HeroCollaborator findCollaborators = comicService.getHeroCollaboratorByHero(map.get(nameHero), pageNo, pageSize);

        if(findCollaborators == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<HeroCollaborator>(findCollaborators, HttpStatus.OK);
    }

}
