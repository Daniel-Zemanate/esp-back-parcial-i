package com.dh.catalogservice.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vaninagodoy
 */

@Data
@Builder
public class Serie {

    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

}
