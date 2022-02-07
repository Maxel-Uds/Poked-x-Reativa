package com.pokedex.reactiveweb.controllers;

import com.pokedex.reactiveweb.models.Pokemon;
import com.pokedex.reactiveweb.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping
    public Flux<Pokemon> findAll() {
        return service.findAll();
    }
}
