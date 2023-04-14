package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Movie;

import java.util.List;

public interface IMovieService {

    Movie saveMovieInCatalog(Movie movie);

    List<Movie> findAllMoviesByGenre(String genre);
}
