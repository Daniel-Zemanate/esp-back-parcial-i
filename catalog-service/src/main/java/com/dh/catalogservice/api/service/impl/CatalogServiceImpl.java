package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.model.dto.CatalogDTO;
import com.dh.catalogservice.domain.model.dto.MovieDTO;
import com.dh.catalogservice.domain.model.dto.SerieDTO;
import com.dh.catalogservice.domain.repository.ICatalogRepository;
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

	private ICatalogRepository catalogRepository;


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

	@Override
	public Catalog findInSelfDatabase(String genre) {
		return catalogRepository.findById(genre).orElse(new Catalog());
	}
	@Override
	public CatalogDTO findInSelfDatabaseOff(String genre) {
		Catalog catalog = catalogRepository.findById(genre).orElse(new Catalog());
		return CatalogDTO.builder()
				.genre(catalog.getGenre())
				.movies(catalog.getMovies().stream().map(movie -> {
					return MovieDTO.builder()
							.id(movie.getId())
							.name(movie.getName())
							.genre(movie.getGenre())
							.urlStream(movie.getUrlStream())
							.build();
				}).collect(Collectors.toList()))
				.series(catalog.getSeries().stream().map(serie -> {
					return SerieDTO.builder()
							.id(serie.getId())
							.name(serie.getName())
							.genre(serie.getGenre())
							.seasons(serie.getSeasons())
							.build();
				}).collect(Collectors.toList()))
				.build();
	}

	@Override
	public CatalogDTO createCatalog(Catalog catalog) {
		Catalog catalogCreated = catalogRepository.save(catalog);

		CatalogDTO catalogDTO = CatalogDTO.builder()
				.genre(catalogCreated.getGenre())
				.movies(catalogCreated.getMovies().stream().map(movie -> {
					return MovieDTO.builder()
							.id(movie.getId())
							.name(movie.getName())
							.genre(movie.getGenre())
							.urlStream(movie.getUrlStream())
							.build();
				}).collect(Collectors.toList()))
				.series(catalogCreated.getSeries().stream().map(serie -> {
					return SerieDTO.builder()
							.id(serie.getId())
							.name(serie.getName())
							.genre(serie.getGenre())
							.seasons(serie.getSeasons())
							.build();
				}).collect(Collectors.toList()))
				.build();


		return catalogDTO;
	}
}
