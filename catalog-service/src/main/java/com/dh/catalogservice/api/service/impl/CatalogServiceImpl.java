package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.model.dto.CatalogDTO;
import com.dh.catalogservice.domain.model.dto.MovieDTO;
import com.dh.catalogservice.domain.model.dto.SerieDTO;
import com.dh.catalogservice.domain.repository.feing.MovieFeingRepository;
import com.dh.catalogservice.domain.repository.feing.SeriesFeingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {
    private MovieFeingRepository movieRepository;

    private SeriesFeingRepository serieRepository;


    public CatalogServiceImpl(MovieFeingRepository movieRepository, SeriesFeingRepository serieRepository) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }

    @Override
    public CatalogDTO findGenreInCatalog(String genre) {

        List<Movie> moviesList = movieRepository.getMoviesByGenre(genre);
        List<Serie> serieList = serieRepository.getSeriesByGenre(genre.toLowerCase());

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
}
