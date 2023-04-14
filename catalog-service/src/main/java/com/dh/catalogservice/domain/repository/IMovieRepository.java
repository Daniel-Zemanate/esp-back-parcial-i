package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends MongoRepository<Movie, Integer> {

    List<Movie> findAllByGenre(String genre);
}
