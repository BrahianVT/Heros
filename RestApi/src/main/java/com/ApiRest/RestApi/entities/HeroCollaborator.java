package com.ApiRest.RestApi.entities;

import lombok.Value;

import java.util.List;

/**
 * Clase que representa las colaboraciones de un hero
 * esta es la salida del servicio 1
 * @author BrahianVT
 * */
@Value
public class HeroCollaborator {
    String lastSync;
    List<String> editors;
    List<String> writes;
    List<String> colorists;
}
