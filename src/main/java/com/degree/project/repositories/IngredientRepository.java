package com.degree.project.repositories;

import com.degree.project.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    boolean existsByName(String name);
}
