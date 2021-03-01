package com.ApiRest.RestApi.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase entidad que persite a base de datos, tabla: comic
 * @author BrahianVT
 * */
@Data
@Entity
@Table(name = "comic")
public class Comic {
    @Id
    @Column(name = "id_comic")
    private Integer idComic;

    @Column(name = "name_comic")
    private String nameComic;

    @Column(name = "name_writer")
    private String nameWriter;

    @Column(name = "name_editor")
    private String nameEditor;

    @Column(name = "name_colorist")
    private String nameColorist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="id_hero", referencedColumnName ="id_hero")
    private Hero hero;


    @ManyToMany(mappedBy = "heroInteractions", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Hero> comicHeros = new HashSet<>();

    @Column(name = "last_sync")
    private LocalDateTime lastSync;


}
