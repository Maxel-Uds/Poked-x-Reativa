package com.pokedex.reactiveweb.config;

import com.pokedex.reactiveweb.service.PokemonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public PokemonService pokemonService() {
        return new PokemonService();
    }
}
