package learnjava.skypro.cookbookapp.services;

import learnjava.skypro.cookbookapp.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    String addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    String removeIngredient(int id);

    Map<Integer, Ingredient> getAllIngredients();
}
