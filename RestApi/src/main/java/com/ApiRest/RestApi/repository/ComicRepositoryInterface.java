package com.ApiRest.RestApi.repository;

import com.ApiRest.RestApi.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface ComicRepositoryInterface extends PagingAndSortingRepository<Comic, Integer> {

    @Query(value = "select h.name_hero, GROUP_CONCAT(c.name_comic SEPARATOR ', ') from comic c inner join interaction i on c.id_comic = i.id_comic inner join hero h on i.id_hero_interaction = h.id_hero where c.id_hero = ?1 group by h.name_hero"
            , nativeQuery = true, countQuery = "select count(h.name_hero) from comic c inner join interaction i on c.id_comic = i.id_comic inner join hero h on i.id_hero_interaction = h.id_hero where c.id_hero = ?1")
    Page<Object[]> getInteractionsByHero(int idHero, Pageable pageable);

    @Query(value = "select c.last_sync from comic c where c.id_hero = ?1 order by c.last_sync desc limit 1", nativeQuery = true)
    String findByLastSync(int idHero);
}
