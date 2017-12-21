package com.luxoft.samurai;

import com.luxoft.SamuraiGenerator;
import com.luxoft.samurai.handlers.SamuraiHandler;
import com.luxoft.samurai.storage.SamuraiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

@Configuration
@Import(RouterConfig.class)

@ComponentScan
@SpringBootApplication
@EnableWebFlux
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	SamuraiHandler getSamuraiHandler()
	{
		return new SamuraiHandler();
	}

	@Bean
	CommandLineRunner init(SamuraiRepository repository)
	{
		return (env) ->
		{
//			System.out.println("REPO --> " + repository);

			repository.save(Mono.fromSupplier(SamuraiGenerator::generateSamurai)).subscribe();
			repository.save(Mono.fromSupplier(SamuraiGenerator::generateSamurai)).subscribe();
//			repository.save(Mono.fromSupplier(SamuraiGenerator::generateSamurai)).subscribe();
		};
	}



}
