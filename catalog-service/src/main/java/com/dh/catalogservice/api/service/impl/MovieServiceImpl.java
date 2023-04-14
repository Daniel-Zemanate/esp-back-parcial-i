package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.IMovieService;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements IMovieService {

    private IMovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovieInCatalog(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAllMoviesByGenre(String genre) {
        return movieRepository.findAllByGenre(genre);
    }
}
