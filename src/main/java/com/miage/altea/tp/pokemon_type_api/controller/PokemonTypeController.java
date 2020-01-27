package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {

    public PokemonTypeService pokemonTypeService;

    @Autowired
    public PokemonTypeController(PokemonTypeService service) {
        this.pokemonTypeService = service;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return pokemonTypeService.getPokemonType(id);
    }

    @GetMapping(value = "/", params = "name")
    PokemonType getPokemonTypeFromName(@RequestParam(value = "name", required = false) String pokemonName){
        return pokemonTypeService.getPokemonTypeByName(pokemonName);
    }

    @GetMapping(value = "/", params = "types")
    List<PokemonType> getPokemonTypeFromType(@RequestParam(value = "types", required = false) String pokemonType){
        return pokemonTypeService.getPokemonTypeByType(pokemonType);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }
}