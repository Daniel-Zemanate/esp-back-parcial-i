package com.dh.catalogservice.api.controller;

import com.dh.catalogservice.api.queue.CatalogListener;
import com.dh.catalogservice.api.service.CatalogService;
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
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/online/{genre}")
    ResponseEntity<CatalogDTO> getCatalogByGenre(@PathVariable String genre) {

        return ResponseEntity.ok(catalogService.findGenreInCatalog(genre));
    }

    @GetMapping("/offline/{genre}")
    ResponseEntity<CatalogDTO> getCatalogByGenreOff(@PathVariable String genre) {

        return ResponseEntity.ok(catalogService.findInSelfDatabaseOff(genre));
    }

    @PostMapping("/save/serie")
    public ResponseEntity<?> saveSerie(@RequestBody Serie serie) {
        catalogListener.receive(serie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save/movie")
    public ResponseEntity<?> saveSerie(@RequestBody Movie movie) {
        catalogListener.receive(movie);
        return ResponseEntity.ok().build();
    }
}
