package com.ApiRest.RestApi.service;


import com.ApiRest.RestApi.entities.HeroCollaborator;
import com.ApiRest.RestApi.entities.HeroInteractionWrapper;

public interface ComicServiceInterface {
    HeroInteractionWrapper getInteractionsByHero(int idHero,int pageN, int pageSize);
    String findByLastSync(int idHero);
    HeroCollaborator getHeroCollaboratorByHero(int idHero,int pageN, int pageSize);
}
