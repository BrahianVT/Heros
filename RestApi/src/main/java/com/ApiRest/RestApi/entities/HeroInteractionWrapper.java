package com.ApiRest.RestApi.entities;

import lombok.Value;

import java.util.List;
/**
 * Clase que representa   un hero y las interacciones con otros heros
 * esta clase se usa en la salida del servicio 2
 * @author BrahianVT
 * */
@Value
public class HeroInteractionWrapper {
    String lastSync;
    List<HeroInteraction>  characters;
}
