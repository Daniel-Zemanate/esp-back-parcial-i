package com.dh.catalogservice.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Movies")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Movie {

	@Id
	private Integer id;
	private String name;
	private String genre;
	private String urlStream;
}
