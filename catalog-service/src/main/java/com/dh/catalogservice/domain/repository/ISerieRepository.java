package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerieRepository extends MongoRepository<Serie, String> {

    List<Serie> findAllByGenre(String genre);
}
