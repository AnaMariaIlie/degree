package com.degree.project.repositories;

import com.degree.project.models.Drug;
import org.springframework.data.repository.CrudRepository;

public interface DrugRepository extends CrudRepository<Drug, String> {

    boolean existsByName(String name);

}