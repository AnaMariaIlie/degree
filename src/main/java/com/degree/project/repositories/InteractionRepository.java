package com.degree.project.repositories;

import com.degree.project.models.Ingredient;
import com.degree.project.models.Interaction;
import org.springframework.data.repository.CrudRepository;

public interface InteractionRepository extends CrudRepository<Interaction, String> {

    boolean existsByFirstIngredientNameAndSecondIngredientNameAndToxicityLevelAndDescription(String first, String second, String toxic, String descr);

}