package com.ApiRest.RestApi.repository;

import com.ApiRest.RestApi.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * Repositorio donde se realizan queries para traer la informacion de la base de datos
 * @author BrahianVT
 * */
public interface ComicRepositoryInterface extends PagingAndSortingRepository<Comic, Integer> {

    @Query(value = "select h.name_hero, GROUP_CONCAT(c.name_comic SEPARATOR ', ') from comic c inner join interaction i on c.id_comic = i.id_comic inner join hero h on i.id_hero_interaction = h.id_hero where c.id_hero = ?1 group by h.name_hero"
            , nativeQuery = true, countQuery = "select count(h.name_hero) from comic c inner join interaction i on c.id_comic = i.id_comic inner join hero h on i.id_hero_interaction = h.id_hero where c.id_hero = ?1")
    Page<Object[]> getInteractionsByHero(int idHero, Pageable pageable);

    @Query(value = "select c.last_sync from comic c where c.id_hero = ?1 order by c.last_sync desc limit 1", nativeQuery = true)
    String findByLastSync(int idHero);

    @Query(value = "SELECT DISTINCT c.name_writer from comic c where c.id_hero = ?1 and c.name_writer is not null"
            , nativeQuery = true, countQuery = "SELECT COUNT(DISTINCT c.name_writer) from comic c where c.id_hero = ?1 and c.name_writer is not null")
    Page<String> getWriterByHero(int idHero, Pageable pageable);

    @Query(value = "SELECT DISTINCT c.name_editor from comic c where c.id_hero = ?1 and c.name_editor is not null"
            , nativeQuery = true, countQuery = "SELECT COUNT(DISTINCT c.name_editor) from comic c where c.id_hero = ?1 and c.name_editor is not null")
    Page<String> getEditorByHero(int idHero, Pageable pageable);

    @Query(value = "SELECT DISTINCT c.name_colorist from comic c where c.id_hero = ?1 and c.name_colorist is not null"
            , nativeQuery = true, countQuery = "SELECT COUNT(DISTINCT c.name_colorist) from comic c where c.id_hero = ?1 and c.name_colorist is not null")
    Page<String> getColoristByHero(int idHero, Pageable pageable);
}
