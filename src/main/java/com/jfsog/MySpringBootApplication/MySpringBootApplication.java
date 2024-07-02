package com.jfsog.MySpringBootApplication;

import com.github.javafaker.Faker;
import com.jfsog.MySpringBootApplication.Controllers.PropriedadeController;
import com.jfsog.MySpringBootApplication.Entidades.Propriedade;
import com.jfsog.MySpringBootApplication.Repositorios.PropriedadeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "Hello %s!".formatted(name);
    }
    @Bean
    public CommandLineRunner commandLineRunner(PropriedadeRepository propriedadeRepository) {
        return args -> {
            var fak = new Faker();
            Stream.iterate(0, i -> i + 1).limit(50).forEach(i -> {
                var prop = Propriedade.builder()
                                      .categoria(fak.name().title())
                                      .subcategoria(fak.name().title())
                                      .descricao(fak.starTrek().location())
                                      .nome(fak.name().name())
                                      .valor("" + i)
                                      .build();
                propriedadeRepository.save(prop);
            });
            var propriedades = propriedadeRepository.findByNomeContainingIgnoreCaseOrderByCategoriaAscSubcategoriaAscNomeAsc("a");
            propriedades.forEach(System.out::println);
        };
    }
}
