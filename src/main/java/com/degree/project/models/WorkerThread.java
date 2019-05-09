package com.degree.project.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class WorkerThread extends Parent implements Callable {
    List<Ingredient> chunk;

    public WorkerThread(List<Ingredient> chunky) {
        chunk = chunky;
    }

    public boolean compare(String secondName) {
        return drug2List.stream().anyMatch(o -> o.getName().equals(secondName));
    }

    @Override
    public List<Interaction> call() throws Exception {

        List<Interaction> foundInteractions = new ArrayList<>();
        for (Ingredient ingredient : chunk) {
            for (Interaction interaction : ingredient.getInteractions()) {
                if (compare(interaction.getSecondIngredientName())) {
                    foundInteractions.add(interaction);
                }
            }

        }

        return foundInteractions;
    }
}
