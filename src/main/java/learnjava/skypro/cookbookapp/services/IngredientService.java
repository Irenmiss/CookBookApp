package learnjava.skypro.cookbookapp.services;

import learnjava.skypro.cookbookapp.model.Ingredient;

public interface IngredientService {
    String addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);
}
