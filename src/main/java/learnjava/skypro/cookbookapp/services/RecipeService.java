package learnjava.skypro.cookbookapp.services;

import learnjava.skypro.cookbookapp.dto.RecipeDTO;
import learnjava.skypro.cookbookapp.model.Recipe;

import java.util.Map;

public interface RecipeService {

    RecipeDTO addRecipe(Recipe recipe);

    RecipeDTO getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    String removeRecipe(int id);

    Map<Integer, Recipe> getAllRecipes();
}
