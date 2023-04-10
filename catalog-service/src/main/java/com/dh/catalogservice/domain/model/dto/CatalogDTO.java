package com.dh.catalogservice.domain.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Catalog")
@Data
@Builder
public class CatalogDTO {
	private String genre;
	private List<MovieDTO> movies;
	private List<SerieDTO> series;
}
