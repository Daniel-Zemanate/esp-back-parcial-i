package com.dh.catalogservice;

import com.dh.catalogservice.domain.model.*;
import com.dh.catalogservice.domain.repository.ICatalogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(ICatalogRepository repository) {
		String baseUrl = "www.netflix.com/series";

		return (args) -> {
			if (!repository.findAll().isEmpty()) {
				return;
			}

			//Serie A terror
			List<Chapter> serieASeasonAChapters = List.of(
					new Chapter("Chapter A", 1, baseUrl + "/terror/1/season/1/chapter/1"),
					new Chapter("Chapter B", 2, baseUrl + "/terror/1/season/1/chapter/2")
			);

			List<Chapter> serieASeasonBChapters = List.of(
					new Chapter("Chapter A", 1, baseUrl + "/terror/1/season/2/chapter/1"),
					new Chapter("Chapter B", 2, baseUrl + "/terror/1/season/2/chapter/2")
			);

			List<Season> serieASeasons = List.of(
					new Season(1, serieASeasonAChapters),
					new Season(2, serieASeasonBChapters)
			);
			Serie serieA = new Serie(UUID.randomUUID().toString(),"Serie A", "terror", serieASeasons);

			repository.save(new Catalog("Terror"
					, List.of(Movie.builder().id(1).name("movie1").genre("Terror").build())
					,List.of(serieA)));

		};
	}
}
