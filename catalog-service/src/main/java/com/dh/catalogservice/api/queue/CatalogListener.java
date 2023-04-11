package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogListener {

    private final CatalogService catalogService;

    public CatalogListener(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie) {
        try {
            Thread.sleep(1000);
            Catalog catalog = catalogService.findInSelfDatabase(serie.getGenre());
            catalog.getSeries().add(serie);
            catalogService.createCatalog(catalog);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie movie) {
        try {
            Thread.sleep(1000);
            Catalog catalog = catalogService.findInSelfDatabase(movie.getGenre());
            catalog.getMovies().add(movie);
            catalogService.createCatalog(catalog);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
