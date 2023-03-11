package learnjava.skypro.cookbookapp.services;

import learnjava.skypro.cookbookapp.dto.RecipeDTO;
import learnjava.skypro.cookbookapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipes = new HashMap<>();
    private int idCounter = 1;

    @Override
    public RecipeDTO addRecipe(Recipe recipe) {
        int id = idCounter++;
        recipes.put(id, recipe);
        return RecipeDTO.from(id, recipe);
    }

    @Override
    public RecipeDTO getRecipe(int id) {
        Recipe recipe = recipes.get(id);
        if (recipe != null) {
            return RecipeDTO.from(id, recipe);
        }
        return null;
    }

    @Override
    public RecipeDTO editRecipe(int id, Recipe recipe) {
        Recipe editedRecipe = recipes.get(id);
        if (editedRecipe != null) {
            recipes.put(id, recipe);
            return RecipeDTO.from(id, recipe);
        }
        return null;
    }

    @Override
    public RecipeDTO removeRecipe(int id) {
        Recipe removedRecipe = recipes.remove(id);
        if (removedRecipe != null) {
            return RecipeDTO.from(id, removedRecipe);
        }
        return null;
    }

    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipes;
    }
}
