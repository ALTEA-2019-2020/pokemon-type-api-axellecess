package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    public PokemonTypeRepository pokemonTypeRepository;


    public TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository){
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType pokemonType = pokemonTypeRepository.findPokemonTypeById(id);

        // Name translation
        if(LocaleContextHolder.getLocale() != Locale.ENGLISH && LocaleContextHolder.getLocale() != Locale.FRENCH){
            pokemonType.setName(translationRepository.getPokemonName(id, Locale.ENGLISH));
        }
        else {
            pokemonType.setName(translationRepository.getPokemonName(id, LocaleContextHolder.getLocale()));
        }

        return pokemonType;
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypeByType(String type) {
        List<String> types = Arrays.asList(type.split(","));
        return pokemonTypeRepository.findPokemonTypeByTypes(types);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        List<PokemonType> pokemonTypeList = pokemonTypeRepository.findAllPokemonType();

        // Name translation
        for(PokemonType pokemonType : pokemonTypeList){
            if(LocaleContextHolder.getLocale() != Locale.ENGLISH && LocaleContextHolder.getLocale() != Locale.FRENCH){
                pokemonType.setName(translationRepository.getPokemonName(pokemonType.getId(), Locale.ENGLISH));
            }
            else {
                pokemonType.setName(translationRepository.getPokemonName(pokemonType.getId(), LocaleContextHolder.getLocale()));
            }
        }
        return pokemonTypeList;
    }

    @Autowired
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }
}