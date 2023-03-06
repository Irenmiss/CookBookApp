package learnjava.skypro.cookbookapp.services;

import learnjava.skypro.cookbookapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredients = new HashMap<>();
    private int idCounter = 1;

    @Override
    public String addIngredient(Ingredient ingredient) {
        int id = idCounter++;
        ingredients.put(id, ingredient);
        return id + ". " + ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return ingredients.get(id);
    }
}