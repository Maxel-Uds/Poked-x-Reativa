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

    public Mono<Pokemon> createPokemon(Pokemon request) {
        Pokemon pokemon = newPokemon(request);
        return repository.save(pokemon);
    }

    public Mono<ResponseEntity<Pokemon>> update(String id, Pokemon request) {
        return repository.findById(id)
                .flatMap(pokemon -> {
                    pokemon = newPokemon(request);
                    pokemon.setId(id);
                    return repository.save(pokemon);
                })
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<Void>> deleteById(String id) {
        return repository.findById(id)
                .flatMap(pokemon ->
                        repository.delete(pokemon)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    private Pokemon newPokemon(Pokemon request) {
        return new Pokemon(null, request.getNome(), request.getCategoria(), request.getHabilidades(), request.getPeso());
    }
}
