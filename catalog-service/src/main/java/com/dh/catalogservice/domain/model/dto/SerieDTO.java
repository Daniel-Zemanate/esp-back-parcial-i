package com.dh.catalogservice.domain.model.dto;

import com.dh.catalogservice.domain.model.Season;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SerieDTO {


    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

}
