package com.degree.project.controllers;

import com.degree.project.models.Drug;
import com.degree.project.models.Ingredient;
import com.degree.project.models.Interaction;
import com.degree.project.repositories.DrugRepository;
import com.degree.project.repositories.IngredientRepository;
import com.degree.project.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
public class DrugController {

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InteractionRepository interactionRepository;
    
    @RequestMapping(method=RequestMethod.GET, value="/drugs")
    public Iterable<Drug> drugs() {
        return drugRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/drug")
    public Drug save(@RequestBody Drug drug) {

        drug.getIngredients().stream().filter(Objects::nonNull).forEach(this::saveIngredient);

        if (!drugRepository.existsByName(drug.getName()))
            drugRepository.save(drug);

        return drug;
    }

    private void saveIngredient(Ingredient ingredient) {

        ingredient.getInteractions().stream().filter(Objects::nonNull).forEach(this::saveInteraction);

        if(!ingredientRepository.existsByName(ingredient.getName()))
            ingredientRepository.save(ingredient);

    }

    private void saveInteraction(Interaction interaction) {

        String first = interaction.getFirstIngredientName();
        String second = interaction.getSecondIngredientName();

        boolean i = interactionRepository.existsByFirstIngredientNameAndSecondIngredientNameAndToxicityLevelAndDescription(first,
                second, interaction.getToxicityLevel(), interaction.getDescription());
        if (!i)
            interactionRepository.save(interaction);
    }

    @RequestMapping(method=RequestMethod.GET, value="/drug/{id}")
    public Optional<Drug> show(@PathVariable String id) {
        return drugRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/drug/{id}")
    public Drug update(@PathVariable String id, @RequestBody Drug drug) {
        Optional<Drug> drugOptional = drugRepository.findById(id);

        if (!drugOptional.isPresent())
            return null;

        Drug i = drugOptional.get();

        if(drug.getName() != null)
            i.setName(drug.getName());

        if(drug.getIngredients() != null)
            i.setIngredients(drug.getIngredients());

        drugRepository.save(i);
        return drug;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/drug/{id}")
    public void delete(@PathVariable String id) {
        Optional<Drug> drugOptional = drugRepository.findById(id);

        if (!drugOptional.isPresent())
            return;

        Drug drug = drugOptional.get();
        drugRepository.delete(drug);

    }
}
