package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);
    PokemonType getPokemonTypeByName(String name);
    public List<PokemonType> getPokemonTypeByType(String type);
    List<PokemonType> getAllPokemonTypes();
}