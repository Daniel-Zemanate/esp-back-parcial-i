package com.dh.catalogservice;

import com.dh.catalogservice.domain.model.Chapter;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Season;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.repository.IMovieRepository;
import com.dh.catalogservice.domain.repository.ISerieRepository;
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

//	@Bean
	public CommandLineRunner loadData(ISerieRepository serieRepository, IMovieRepository movieRepository) {
		String baseUrl = "www.netflix.com/series";

		return (args) -> {
			if (!serieRepository.findAll().isEmpty() || !movieRepository.findAll().isEmpty()) {
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
					Season.builder().seasonNumber(1).chapters(serieASeasonAChapters).build(),
					Season.builder().seasonNumber(2).chapters(serieASeasonBChapters).build()
			);

			//Serie B comedia
			List<Chapter> serieBSeasonAChapters = List.of(
					new Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/1/chapter/1"),
					new Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/1/chapter/2")
			);

			List<Chapter> serieBSeasonBChapters = List.of(
					new Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/2/chapter/1"),
					new Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/2/chapter/2")
			);

			List<Season> serieBSeasons = List.of(
					Season.builder().seasonNumber(1).chapters(serieBSeasonAChapters).build(),
					Season.builder().seasonNumber(2).chapters(serieBSeasonBChapters).build()
			);

			Serie serieA = Serie.builder().id(UUID.randomUUID().toString()).name("Serie A").genre("terror").seasons(serieASeasons).build();
			Serie serieB = Serie.builder().id(UUID.randomUUID().toString()).name("Serie B").genre("comedia").seasons(serieBSeasons).build();
			serieRepository.save(serieA);
			serieRepository.save(serieB);


			movieRepository.save(Movie.builder().name("movie1").genre("Terror").urlStream(baseUrl).build());
			movieRepository.save(Movie.builder().name("movie2").genre("Terror").urlStream(baseUrl).build());
			movieRepository.save(Movie.builder().name("movie3").genre("Comedy").urlStream(baseUrl).build());
			movieRepository.save(Movie.builder().name("movie4").genre("Comedy").urlStream(baseUrl).build());

		};
	}


}
