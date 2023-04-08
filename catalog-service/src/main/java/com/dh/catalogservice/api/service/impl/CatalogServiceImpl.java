package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.repository.feing.MovieFeingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
	private MovieFeingRepository movieRepository;


	public CatalogServiceImpl(MovieFeingRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public CatalogWS findGenreInCatalog(String genre) {

		List<MovieWS> movies =  movieRepository.getMoviesByGenre(genre);
		CatalogWS catalogWS = CatalogWS.builder()
				.genre(genre)
				.movies(movies)
				.build();


		return catalogWS;
	}
}
