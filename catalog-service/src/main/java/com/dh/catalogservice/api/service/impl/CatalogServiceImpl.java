package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.api.service.IMovieService;
import com.dh.catalogservice.api.service.ISerieService;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.model.dto.CatalogDTO;
import com.dh.catalogservice.domain.model.dto.MovieDTO;
import com.dh.catalogservice.domain.model.dto.SerieDTO;
import com.dh.catalogservice.domain.repository.feing.MovieFeingRepository;
import com.dh.catalogservice.domain.repository.feing.SeriesFeingRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {
    private MovieFeingRepository movieFeingRepository;
    private SeriesFeingRepository seriesFeingRepository;

	private IMovieService movieService;
	private ISerieService serieService;

	@Autowired
	public CatalogServiceImpl(MovieFeingRepository movieFeingRepository, SeriesFeingRepository seriesFeingRepository, IMovieService movieService, ISerieService serieService) {
		this.movieFeingRepository = movieFeingRepository;
		this.seriesFeingRepository = seriesFeingRepository;
		this.movieService = movieService;
		this.serieService = serieService;
	}


//	CIRCUIT BREAKER WAS IMPLEMENTED HERE BECAUSE MICROSERVICES ARE FETCHED IN THIS METHOD, THUS IS NECESSARY TO GUARANTEE RESPONSE AVAILABILITY
	@CircuitBreaker(name = "seriesmoviesfeing", fallbackMethod = "fallback")
	@Retry(name = "seriesmoviesfeing")
    @Override
    public CatalogDTO findGenreInCatalog(String genre, Integer faultPercentage) {

        List<Movie> moviesList = movieFeingRepository.getMoviesByGenre(genre, faultPercentage);
        List<Serie> serieList = seriesFeingRepository.getSeriesByGenre(genre.toLowerCase());

		List<MovieDTO> movieDTOList = moviesList.stream().map(movie -> {
			return MovieDTO.builder()
					.id(movie.getId())
					.name(movie.getName())
					.genre(movie.getGenre())
					.urlStream(movie.getUrlStream())
					.build();
		}).collect(Collectors.toList());

		List<SerieDTO> serieDTOList = serieList.stream().map(serie -> {
			return SerieDTO.builder()
					.id(serie.getId())
					.name(serie.getName())
					.genre(serie.getGenre())
					.seasons(serie.getSeasons())
					.build();
		}).collect(Collectors.toList());


		CatalogDTO catalogDTO = CatalogDTO.builder()
                .genre(genre)
                .movies(movieDTOList)
				.series(serieDTOList)
                .build();


        return catalogDTO;
    }

	public CatalogDTO fallback(String genre, Integer faultPercentage, Throwable t) {
		CatalogDTO responseFallback = findOffline(genre);
		return responseFallback;
	}

	@Override
	public CatalogDTO findOffline(String genre) {
		List<Movie> movieList = movieService.findAllMoviesByGenre(genre);
		List<Serie>	serieList = serieService.findAllSeriesByGenre(genre.toLowerCase());


		return CatalogDTO.builder()
				.genre(genre)
				.movies(movieList.stream().map(movie -> {
					return MovieDTO.builder().id(movie.getId()).name(movie.getName()).genre(movie.getGenre()).urlStream(movie.getUrlStream()).build();
				}).collect(Collectors.toList()))
				.series(serieList.stream().map(serie -> {
					return SerieDTO.builder().id(serie.getId()).name(serie.getName()).genre(serie.getGenre()).seasons(serie.getSeasons()).build();
				}).collect(Collectors.toList()))
				.build();
	}

}
