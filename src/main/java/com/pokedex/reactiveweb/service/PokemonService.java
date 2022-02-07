package com.pokedex.reactiveweb.service;

import com.pokedex.reactiveweb.models.Pokemon;
import com.pokedex.reactiveweb.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public Flux<Pokemon> findAll() {
        return repository.findAll();
    }

    public Mono<ResponseEntity<Pokemon>> findById(String id) {
        return  repository.findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
