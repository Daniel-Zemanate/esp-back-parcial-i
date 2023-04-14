package com.dh.catalogservice.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Season {

    private Integer seasonNumber;
    private List<Chapter> chapters = new ArrayList<>();


}
