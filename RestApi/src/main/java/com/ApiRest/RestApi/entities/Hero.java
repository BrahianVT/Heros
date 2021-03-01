package com.ApiRest.RestApi.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Clase entidad que persite a base de datos, tabla: hero
 * @author BrahianVT
 * */
@Data
@Entity
@Table(name = "hero")
public class Hero {
    @Column(name = "id_hero")
    @Id
    private Integer idHero;

    @Column(name = "name_hero")
    private String nameHero;

    @OneToOne(mappedBy = "hero")
    private Comic comic;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "interaction",
            joinColumns = {@JoinColumn(name = "id_hero_interaction")},
            inverseJoinColumns = {@JoinColumn(name = "id_comic")}
    )
    private Set<Comic> heroInteractions = new HashSet<>();

}
