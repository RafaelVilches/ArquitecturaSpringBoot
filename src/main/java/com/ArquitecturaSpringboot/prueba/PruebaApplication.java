package com.ArquitecturaSpringboot.prueba;

import com.ArquitecturaSpringboot.prueba.Model.Entities.Libro;
import com.ArquitecturaSpringboot.prueba.Repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PruebaApplication {

	public static void main(String[] args) {

		SpringApplication.run(PruebaApplication.class, args);
	}

	@Bean
	public CommandLineRunner init (LibroRepository myRepo) {
		return args -> {
			myRepo.save(new Libro(null,"Quijote","Cervantes",345));
			myRepo.save(new Libro(null,"LibroTroll","Rubius",50));
			myRepo.save(new Libro(null,"VirtualHero","Rubius",35));
			myRepo.save(new Libro(null,"LosPilaresDeLaTierra","XD",2500));
			myRepo.save(new Libro(null,"Dracula","Stoker",563));
		};
	}

}


