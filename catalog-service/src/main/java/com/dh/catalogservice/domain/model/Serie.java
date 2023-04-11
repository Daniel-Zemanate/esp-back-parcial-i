package com.dh.catalogservice.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vaninagodoy
 */

@Data
@Builder
@AllArgsConstructor
public class Serie {

    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

}
