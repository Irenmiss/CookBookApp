package learnjava.skypro.cookbookapp.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import learnjava.skypro.cookbookapp.model.Ingredient;
import learnjava.skypro.cookbookapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы с ингредиентами блюд")

public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найден ингредиент",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Ingredient getRecipe(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping("/create")
    @Operation(
            summary = "Добавление ингредиента в базу"
    )
    public String addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингредиента"
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.editIngredient(id, ingredient);
        if (editedIngredient != null) {
            return ResponseEntity.ok(ingredient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента"
    )
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        String deletedIngredient = ingredientService.removeIngredient(id);
        if (deletedIngredient != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(
            summary = "Вывод всех внесённых в базу ингредиентов"
    )
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredients() {
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients != null) {
            return ResponseEntity.ok(ingredients);
        }
        return ResponseEntity.notFound().build();
    }
}
