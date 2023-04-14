package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;

import java.util.List;

public interface ISerieService {

    Serie saveSerieInCatalog(Serie serie);

    List<Serie> findAllSeriesByGenre(String genre);
}
