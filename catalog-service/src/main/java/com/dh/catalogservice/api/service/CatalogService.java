package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.model.dto.CatalogDTO;
import com.dh.catalogservice.domain.model.dto.MovieDTO;
import com.dh.catalogservice.domain.model.dto.SerieDTO;

import java.util.List;

public interface CatalogService {

    CatalogDTO findGenreInCatalog(String genre, Integer faultPercentage);

    CatalogDTO findOffline(String genre);


}
