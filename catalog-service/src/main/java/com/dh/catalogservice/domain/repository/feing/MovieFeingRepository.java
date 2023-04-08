package com.dh.catalogservice.domain.repository.feing;

import com.dh.catalogservice.domain.model.dto.MovieWS;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = FeingConfig.class)
public interface MovieFeingRepository {

    @GetMapping("/movies/{genre}")
    List<MovieWS> getMoviesByGenre(@PathVariable String genre);
}
