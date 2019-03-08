package com.degree.project.controllers;

import com.degree.project.models.Ingredient;
import com.degree.project.models.Interaction;
import com.degree.project.repositories.IngredientRepository;
import com.degree.project.repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class InteractionController {

    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @RequestMapping(method=RequestMethod.GET, value="/interactions")
    public Iterable<Interaction> interactions() {
        return interactionRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/interaction")
    public Interaction save(@RequestBody Interaction interaction) {

        String first = interaction.getFirstIngredientName();
        String second = interaction.getSecondIngredientName();
        boolean i = interactionRepository.existsByFirstIngredientNameAndSecondIngredientNameAndToxicityLevelAndDescription(first,
                second, interaction.getToxicityLevel(), interaction.getDescription());
        if (!i)
            interactionRepository.save(interaction);

        return interaction;
    }

    @RequestMapping(method=RequestMethod.GET, value="/interaction/{id}")
    public Optional<Interaction> show(@PathVariable String id) {
        return interactionRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/interaction/{id}")
    public Interaction update(@PathVariable String id, @RequestBody Interaction interaction) {
        Optional<Interaction> interactionOptional = interactionRepository.findById(id);

        if (!interactionOptional.isPresent())
            return null;

        Interaction i = interactionOptional.get();

        if(interaction.getFirstIngredientName() != null)
            i.setFirstIngredientName(interaction.getFirstIngredientName());

        if(interaction.getSecondIngredientName() != null)
            i.setSecondIngredientName(interaction.getSecondIngredientName());

        if(interaction.getDescription() != null)
            i.setDescription(interaction.getDescription());

        if(interaction.getToxicityLevel() != null)
            i.setToxicityLevel(interaction.getToxicityLevel());

        interactionRepository.save(i);
        return interaction;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/interaction/{id}")
    public void delete(@PathVariable String id) {
        Optional<Interaction> interactionOptional = interactionRepository.findById(id);

        if (!interactionOptional.isPresent())
            return;

        Interaction interaction = interactionOptional.get();
        interactionRepository.delete(interaction);

    }
}
