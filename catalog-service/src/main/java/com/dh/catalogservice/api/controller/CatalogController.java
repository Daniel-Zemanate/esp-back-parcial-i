package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.queue.CatalogListener;
import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.api.service.IMovieService;
import com.dh.catalogservice.api.service.ISerieService;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.model.dto.CatalogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private CatalogService catalogService;

    private CatalogListener catalogListener;


    @Autowired
    public CatalogController(CatalogService catalogService, CatalogListener catalogListener) {
        this.catalogService = catalogService;
        this.catalogListener = catalogListener;
    }


    @GetMapping("/online/{genre}")
    ResponseEntity<CatalogDTO> getCatalogByGenre(@PathVariable String genre, @RequestParam(defaultValue = "0") Integer faultPercentage) {

        return ResponseEntity.ok(catalogService.findGenreInCatalog(genre, faultPercentage));
    }

    @GetMapping("/offline/{genre}")
    ResponseEntity<CatalogDTO> getCatalogByGenreOff(@PathVariable String genre) {

        return ResponseEntity.ok(catalogService.findOffline(genre));
    }

}
