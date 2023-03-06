package learnjava.skypro.cookbookapp.controllers;


import learnjava.skypro.cookbookapp.model.Ingredient;
import learnjava.skypro.cookbookapp.services.IngredientService;
import org.springframework.web.bind.annotation.*;

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
}
