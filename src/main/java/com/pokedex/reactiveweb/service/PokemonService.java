package com.pokedex.reactiveweb.service;

import com.pokedex.reactiveweb.models.Pokemon;
import com.pokedex.reactiveweb.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public Flux<Pokemon> findAll() {
        return repository.findAll();
    }
}
