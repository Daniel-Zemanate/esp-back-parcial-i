package com.dh.catalogservice.domain.repository;


import com.dh.catalogservice.domain.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatalogRepository extends MongoRepository<Catalog, String> {

    Optional<Catalog> findById(String genre);

}
