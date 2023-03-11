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
        Ingredient ingredient = ingredients.get(id);
        if (ingredient != null) {
            return ingredients.get(id);
        }
        return null;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public String removeIngredient(int id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return "Ингредиент под номером " + id + "удалён";
        }
        return "Указанный id не существует";
    }

    @Override
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }
}