package com.miage.altea.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            /*var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json");

            var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);*/

            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        for(PokemonType pokemonType : pokemons){
            if(pokemonType.getId() == id){
                return pokemonType;
            }
        }
        return new PokemonType();
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        for(PokemonType pokemonType : pokemons){
            if(pokemonType.getName().equals(name)){
                return pokemonType;
            }
        }
        return new PokemonType();
    }

    @Override
    public List<PokemonType> findPokemonTypeByTypes(List<String> types) {
        System.out.println("Loading Pokemons informations for Pokemon type " + types);
        List<PokemonType> pokemonTypes = new ArrayList<>();
        Boolean inTypes = true;
        for(PokemonType pokemonType : pokemons){
            inTypes = true;
            for(String type : types){
                if(!pokemonType.getTypes().contains(type)){
                    inTypes = false;
                }
            }
            if(inTypes){
                pokemonTypes.add(pokemonType);
            }
        }
        return pokemonTypes;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        pokemons.sort(Comparator.comparing(PokemonType::getId));
        return pokemons;
    }
}