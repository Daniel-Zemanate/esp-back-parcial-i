package com.dh.catalogservice.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

	private Integer id;
	private String name;
	private String genre;
	private String urlStream;
}
