package com.dh.catalogservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Catalog")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Catalog {

    @Id
    private String genre;
    private List<Movie> movies;
    private List<Serie> series;

}
