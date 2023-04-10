package com.dh.catalogservice.domain.repository.feing;

import com.dh.catalogservice.domain.model.Serie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = FeingConfig.class)
public interface SeriesFeingRepository {

    @GetMapping("/api/v1/series/{genre}")
    List<Serie> getSeriesByGenre(@PathVariable String genre);
}
