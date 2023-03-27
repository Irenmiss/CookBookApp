package learnjava.skypro.cookbookapp.services;

import learnjava.skypro.cookbookapp.dto.RecipeDTO;
import learnjava.skypro.cookbookapp.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface RecipeService {

    RecipeDTO addRecipe(Recipe recipe);

    RecipeDTO getRecipe(int id);

    RecipeDTO editRecipe(int id, Recipe recipe);

    RecipeDTO removeRecipe(int id);

    Map<Integer, Recipe> getAllRecipes();

    Path createTextRecipesFile() throws IOException;
}
