package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.ISerieService;
import com.dh.catalogservice.domain.model.Serie;
import com.dh.catalogservice.domain.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements ISerieService {

    private ISerieRepository serieRepository;

    @Autowired
    public SerieServiceImpl(ISerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public Serie saveSerieInCatalog(Serie serie) {
        return serieRepository.save(serie);
    }

    @Override
    public List<Serie> findAllSeriesByGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }
}
