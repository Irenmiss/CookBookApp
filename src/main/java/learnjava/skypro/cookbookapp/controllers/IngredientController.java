package learnjava.skypro.cookbookapp.controllers;


import learnjava.skypro.cookbookapp.model.Ingredient;
import learnjava.skypro.cookbookapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")

public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getRecipe(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping("/create")
    public String addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.editIngredient(id, ingredient);
        if (editedIngredient != null) {
            return ResponseEntity.ok(ingredient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        String deletedIngredient = ingredientService.removeIngredient(id);
        if (deletedIngredient != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredients() {
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients != null) {
            return ResponseEntity.ok(ingredients);
        }
        return ResponseEntity.notFound().build();
    }
}
