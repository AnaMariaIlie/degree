package com.degree.project.controllers;

import com.degree.project.models.Ingredient;
import com.degree.project.models.Interaction;
import com.degree.project.repositories.IngredientRepository;
import com.degree.project.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InteractionRepository interactionRepository;

    @RequestMapping(method=RequestMethod.GET, value="/ingredients")
    public Iterable<Ingredient> ingredients() {
        return ingredientRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/ingredient")
    public Ingredient save(@RequestBody Ingredient ingredient) {


        for (Interaction i : ingredient.getInteractions()) {
            if (i != null)
                interactionRepository.save(i);
        }
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    @RequestMapping(method=RequestMethod.GET, value="/ingredient/{id}")
    public Optional<Ingredient> show(@PathVariable String id) {
        return ingredientRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/ingredient/{id}")
    public Ingredient update(@PathVariable String id, @RequestBody Ingredient ingredient) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);

        if (!ingredientOptional.isPresent())
            return null;

        Ingredient i = ingredientOptional.get();

        if(ingredient.getName() != null)
            i.setName(ingredient.getName());

        if(ingredient.getInteractions() != null)
            i.setInteractions(ingredient.getInteractions());

        ingredientRepository.save(i);
        return ingredient;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/ingredient/{id}")
    public void delete(@PathVariable String id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);

        if (!ingredientOptional.isPresent())
            return;


        Ingredient ingredient = ingredientOptional.get();
        for (Interaction interaction: ingredient.getInteractions()) {
            interactionRepository.delete(interaction);
        }
        ingredientRepository.delete(ingredient);

    }
}
