package com.ApiRest.RestApi.entities;

import lombok.Value;

import java.util.List;

@Value
public class HeroInteractionWrapper {
    String lastSync;
    List<HeroInteraction>  characters;
}
