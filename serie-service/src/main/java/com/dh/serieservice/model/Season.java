package com.dh.serieservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Season {

    private Integer seasonNumber;
    private List<Chapter> chapters = new ArrayList<>();


}
