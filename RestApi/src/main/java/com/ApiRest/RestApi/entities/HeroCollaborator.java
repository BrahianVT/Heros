package com.ApiRest.RestApi.entities;

import lombok.Value;

import java.util.List;

@Value
public class HeroCollaborator {
    String lastSync;
    List<String> editors;
    List<String> writes;
    List<String> colorists;
}
