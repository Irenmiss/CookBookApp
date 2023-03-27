package learnjava.skypro.cookbookapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import learnjava.skypro.cookbookapp.dto.RecipeDTO;
import learnjava.skypro.cookbookapp.model.Recipe;
import learnjava.skypro.cookbookapp.services.RecipeFilesService;
import learnjava.skypro.cookbookapp.services.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> recipes = new HashMap<>();
    private int idCounter = 1;
    final private RecipeFilesService recipeFilesService;

    public RecipeServiceImpl(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public RecipeDTO addRecipe(Recipe recipe) {
        int id = idCounter++;
        recipes.put(id, recipe);
        saveToFile();
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
            saveToFile();
            return RecipeDTO.from(id, recipe);
        }
        return null;
    }

    @Override
    public RecipeDTO removeRecipe(int id) {
        Recipe removedRecipe = recipes.remove(id);
        saveToFile();
        if (removedRecipe != null) {
            return RecipeDTO.from(id, removedRecipe);
        }
        return null;
    }

    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipes;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private void readFromFile() {
        try {
            String json = recipeFilesService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Path createTextRecipesFile() throws IOException {
        Path path = recipeFilesService.createTempFile("recipesTextFile");
        for (Recipe recipe : recipes.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getTitle()).append("\n \n").append("Время приготовления: ").append(String.valueOf(recipe.getCookingTime())).append(" минут.").append("\n");
                writer.append("\n");
                writer.append("Ингредиенты: \n \n");
                recipe.getIngredients().forEach(ingredient -> {
                    try {
                        writer.append(" - ").append(ingredient.getTitle()).append(" - ").append(String.valueOf(ingredient.getNumber())).append(" ").append(ingredient.getMeasure()).append("\n \n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.append("\n");
                writer.append("Инструкция приготовления: \n \n");
                recipe.getSteps().forEach(step -> {
                    try {
                        writer.append(" > ").append(step).append("\n \n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.append("\n \n");
            }
        }
        return path;
    }
}
