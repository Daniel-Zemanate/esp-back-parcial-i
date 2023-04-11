package com.dh.catalogservice.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Movie {

	private Integer id;
	private String name;
	private String genre;
	private String urlStream;
}
