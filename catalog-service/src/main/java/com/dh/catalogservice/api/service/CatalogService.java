package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.dto.CatalogDTO;

public interface CatalogService {

    CatalogDTO findGenreInCatalog(String genre);
}
