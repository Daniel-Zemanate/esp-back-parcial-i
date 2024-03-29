package com.dh.serieservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
/**
 * @author vaninagodoy
 */

@Document(collection = "Series")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Serie {

    @Id
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

}
