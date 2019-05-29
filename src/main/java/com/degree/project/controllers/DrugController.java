package com.degree.project.controllers;

import com.degree.project.models.*;
import com.degree.project.repositories.DrugRepository;
import com.degree.project.repositories.IngredientRepository;
import com.degree.project.repositories.InteractionRepository;
import com.degree.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ExecutorBeanDefinitionParser;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class DrugController {

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InteractionRepository interactionRepository;

    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(method=RequestMethod.GET, value="/drugs")
    public Iterable<Drug> drugs() {
        return drugRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/drug")
    public Drug save(@RequestBody Drug drug) {

        //drug.getIngredients().stream().filter(Objects::nonNull).forEach(this::saveIngredient);

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

    @RequestMapping(value="/drugsIds", method = RequestMethod.POST)
    public Iterable<String> getdrugs(@RequestParam("id1") String first, @RequestParam("id2") String second, @RequestParam("id3") String third){

        List<String> foundDrugs = new ArrayList<>();
        Optional<Drug> firstDrug = drugRepository.findById(first);
        Optional<Drug> secondDrug = drugRepository.findById(second);
        Optional<Drug> thirdDrug = drugRepository.findById(third);


        firstDrug.ifPresent(drug -> foundDrugs.add(drug.getName()));
        secondDrug.ifPresent(drug -> foundDrugs.add(drug.getName()));
        thirdDrug.ifPresent(drug -> foundDrugs.add(drug.getName()));

        return foundDrugs;
    }

    private void addInEntry(String string, List<String> entry) {
        if (!"".equals(string)) {
            entry.add(string);
        }
    }



    @RequestMapping(value="/interactionList", method = RequestMethod.POST)
    public Map< String, Interaction> getitem(@RequestParam("userId") String userId, @RequestParam("first") String first, @RequestParam("second") String second, @RequestParam("third") String third){


        Map< String, Interaction> foundInteractions = new LinkedHashMap<>();
        Optional<Drug> firstDrug = drugRepository.findById(first);
        Optional<Drug> secondDrug = drugRepository.findById(second);
        Optional<Drug> thirdDrug = drugRepository.findById(third);

        //pentru cazul in care sunt prezente toate
        storeAndCompare(foundInteractions, firstDrug, secondDrug);
        storeAndCompare(foundInteractions, firstDrug, thirdDrug);
        storeAndCompare(foundInteractions, secondDrug, thirdDrug);


        List<String> entry = new ArrayList<>();
        Optional<User> optUser = userRepository.findById(userId);

        addInEntry(first, entry);
        addInEntry(second, entry);
        addInEntry(third, entry);

        entry.add(LocalDateTime.now().toString());

        if (optUser.isPresent()) {
            User user = optUser.get();
            user.getHistory().add(entry);
            userRepository.save(user);
        }

        /*if (firstDrug.isPresent() && thirdDrug.isPresent()) {
            for (Ingredient ingredient : firstDrug.get().getIngredients()) {
                for (Interaction interaction : ingredient.getInteractions()) {
                    if (compare(interaction.getSecondIngredientName(), thirdDrug.get().getIngredients())) {
                        foundInteractions.add(interaction);
                    }
                }

            }

        }

        if (secondDrug.isPresent() && thirdDrug.isPresent()) {
            for (Ingredient ingredient : secondDrug.get().getIngredients()) {
                for (Interaction interaction : ingredient.getInteractions()) {
                    if (compare(interaction.getSecondIngredientName(), thirdDrug.get().getIngredients())) {
                        foundInteractions.add(interaction);
                    }
                }

            }

        }
      Interaction i1 = new Interaction();
       i1.setFirstIngredientName("Fluoxetine");
       i1.setSecondIngredientName("Phenelzine");
       i1.setDescription("The interaction can result in a central serotonin syndrome. This condition is characterized by mental status changes, agitation, diaphoresis, tachycardia, and death");
       i1.setToxicityLevel("high");
       i1.setId("123");
       Interaction i2 = new Interaction();
       i2.setFirstIngredientName("Digoxin");
       i2.setSecondIngredientName("Quinidine");
       i2.setDescription("The interaction can lead to a marked increase in plasma concentration levels of digoxin in more than 90% of patients.Significant changes in serum digoxin are noticed within 24 hours.");
       i2.setToxicityLevel("medium");
       i2.setId("12d3");
        firstDrug.ifPresent(drug -> foundInteractions.put(firstDrug.get().getName() + "###" + secondDrug.get().getName(), i1));
        foundInteractions.put("NN"  + "###" + "DJJDJD" , i2);
        Interaction i3 = new Interaction();
        i3.setFirstIngredientName("Clonidine");
        i3.setSecondIngredientName("Propranolol");
        i3.setDescription("The combination may produce a mysterious hypertension that is unrelated to the pharmacology of either agent when administered independently.");
        i3.setToxicityLevel("low");
        i3.setId("12xd3");
        foundInteractions.put("NccN"  + "###" + "DccJJDJD", i3);*/


        return foundInteractions;
    }

    private void storeAndCompare(Map<String, Interaction> foundInteractions, Optional<Drug> firstDrug, Optional<Drug> secondDrug) {
        if (firstDrug.isPresent() && secondDrug.isPresent()) {
             for (Ingredient ingredient : firstDrug.get().getIngredients()) {
                 for (Interaction interaction : ingredient.getInteractions()) {
                     if (compare(interaction.getSecondIngredientName(), secondDrug.get().getIngredients())) {
                         foundInteractions.put(firstDrug.get().getName() + "###" + secondDrug.get().getName(), interaction);
                     }
                 }

             }

         }
    }


    public boolean compare(String secondName, List<Ingredient> ingredients) {
        return ingredients.stream().anyMatch(o -> o.getName().equals(secondName));
    }

}
