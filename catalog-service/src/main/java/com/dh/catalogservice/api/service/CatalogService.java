package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.dto.CatalogDTO;

public interface CatalogService {

    CatalogDTO findGenreInCatalog(String genre);

    Catalog findInSelfDatabase(String genre);
    CatalogDTO findInSelfDatabaseOff(String genre);

    CatalogDTO createCatalog(Catalog catalog);

}
