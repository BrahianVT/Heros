package com.ApiRest.RestApi.service;


import com.ApiRest.RestApi.entities.HeroInteractionWrapper;

public interface ComicRepositoryServiceInterface {
    HeroInteractionWrapper getInteractionsByHero(int idHero,int pageN, int pageSize);
    String findByLastSync(int idHero);
}
