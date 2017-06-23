package it.inail.estema.microservices.poc.digident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories( basePackages = "it.inail.estema.microservices.poc.digident.repositories")
@EnableScheduling
@ConfigurationProperties
public class HeaderFooterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeaderFooterApplication.class, args);
	}
}
