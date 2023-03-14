package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.dto.CatalogWS;

import java.util.List;

public interface CatalogService {

    CatalogWS findGenreInCatalog(String genre);
}
