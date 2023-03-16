package learnjava.skypro.cookbookapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import learnjava.skypro.cookbookapp.model.Ingredient;
import learnjava.skypro.cookbookapp.services.IngredientFilesService;
import learnjava.skypro.cookbookapp.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredients = new HashMap<>();
    private int idCounter = 1;
    private final IngredientFilesService ingredientFilesService;

    public IngredientServiceImpl(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public String addIngredient(Ingredient ingredient) {
        int id = idCounter++;
        ingredients.put(id, ingredient);
        saveToFile();
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
            saveToFile();
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

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void readFromFile() {
        try {
            String json = ingredientFilesService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}