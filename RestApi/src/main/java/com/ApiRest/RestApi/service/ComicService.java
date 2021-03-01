package com.ApiRest.RestApi.service;


import com.ApiRest.RestApi.entities.HeroCollaborator;
import com.ApiRest.RestApi.entities.HeroInteraction;
import com.ApiRest.RestApi.entities.HeroInteractionWrapper;
import com.ApiRest.RestApi.repository.ComicRepositoryInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ComicService implements ComicServiceInterface {
    private final ComicRepositoryInterface comicRepositoryInterface;

    @Autowired
    public ComicService(ComicRepositoryInterface comicRepositoryInterface){
        this.comicRepositoryInterface = comicRepositoryInterface;
    }
    @Override
    public HeroInteractionWrapper getInteractionsByHero(int idHero,int pageN, int pageSize) {
        Pageable paging = PageRequest.of(pageN, pageSize);
        Page<Object[]> aux = comicRepositoryInterface.getInteractionsByHero(idHero, paging);
        List<HeroInteraction> res = new ArrayList<>();
        for(Object[] a: aux.toList()){
            res.add(new HeroInteraction("" + a[0], "" + a[1]));
        }
        String lastDate = findByLastSync(idHero);
        HeroInteractionWrapper heroInteract = new HeroInteractionWrapper(lastDate, res);
        return heroInteract;
    }

    @Override
    public String findByLastSync(int idHero) {
        String lastDate = comicRepositoryInterface.findByLastSync(idHero);
        return lastDate;
    }

    @Override
    public HeroCollaborator getHeroCollaboratorByHero(int idHero, int pageN, int pageSize) {
        Pageable paging = PageRequest.of(pageN, pageSize);
        Page<String> pageWriter = comicRepositoryInterface.getWriterByHero(idHero, paging);
        List<String> writers = pageWriter.toList();

        paging = PageRequest.of(pageN, pageSize);
        Page<String> pageEditor = comicRepositoryInterface.getWriterByHero(idHero, paging);
        List<String> editors = pageWriter.toList();

        paging = PageRequest.of(pageN, pageSize);
        Page<String> pageColorist = comicRepositoryInterface.getColoristByHero(idHero, paging);
        List<String> colorist = pageColorist.toList();

        String lastDate = findByLastSync(idHero);
        HeroCollaborator heroCol = new HeroCollaborator(lastDate, writers, editors, colorist);
        return heroCol;
    }


}
