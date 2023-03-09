package learnjava.skypro.cookbookapp.controllers;

import learnjava.skypro.cookbookapp.dto.RecipeDTO;
import learnjava.skypro.cookbookapp.model.Recipe;
import learnjava.skypro.cookbookapp.services.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable("id") int id) {

        return recipeService.getRecipe(id);
    }

    @PostMapping
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {

        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(id, recipe);
        if (editedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
        String deletedRecipe = recipeService.removeRecipe(id);
        if (deletedRecipe != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
