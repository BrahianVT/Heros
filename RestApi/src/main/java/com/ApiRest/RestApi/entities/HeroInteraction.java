package com.ApiRest.RestApi.entities;
import lombok.Value;

/**
 * Clase que representa   un hero y los comics en los que sale
 * esta clase se usa en la salida del servicio 1
 * @author BrahianVT
 * */
@Value
public class HeroInteraction {
    String nameHero;
    String listComics;
}
