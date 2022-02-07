package com.pokedex.reactiveweb;

import com.pokedex.reactiveweb.models.Pokemon;
import com.pokedex.reactiveweb.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactivewebApplication implements CommandLineRunner {

	@Autowired
	private PokemonRepository repository;
	private Flux<Pokemon> pokemonFlux;

	public static void main(String[] args) {
		SpringApplication.run(ReactivewebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			 pokemonFlux = Flux.just(
							new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
							new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
							new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
							new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09))
					.flatMap(repository::save);

			pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
	}
}
