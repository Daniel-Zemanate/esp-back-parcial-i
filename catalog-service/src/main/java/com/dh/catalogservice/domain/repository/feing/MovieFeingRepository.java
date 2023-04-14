package com.dh.catalogservice.domain.repository.feing;

import com.dh.catalogservice.domain.model.Movie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = FeingConfig.class)
public interface MovieFeingRepository {

    @GetMapping("/movies/{genre}")
    List<Movie> getMoviesByGenre(@PathVariable String genre, @RequestParam(defaultValue = "0") Integer faultPercentage);
}
