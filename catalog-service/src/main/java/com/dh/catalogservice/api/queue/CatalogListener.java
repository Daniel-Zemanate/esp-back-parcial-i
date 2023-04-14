package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.api.service.IMovieService;
import com.dh.catalogservice.api.service.ISerieService;
import com.dh.catalogservice.domain.model.Movie;
import com.dh.catalogservice.domain.model.Serie;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CatalogListener {

    private IMovieService movieService;
    private ISerieService serieService;

    @Autowired
    public CatalogListener(IMovieService movieService, ISerieService serieService) {
        this.movieService = movieService;
        this.serieService = serieService;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie) {
        try {
            Thread.sleep(1000);
            System.out.println("CREANDO UNA SERIE DESDE CATALOG PARA USO OFFLINE");
            serieService.saveSerieInCatalog(serie);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie movie) {
        try {
            Thread.sleep(1000);
            System.out.println("CREANDO UNA MOVIE DESDE CATALOG PARA USO OFFLINE");
            movieService.saveMovieInCatalog(movie);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
