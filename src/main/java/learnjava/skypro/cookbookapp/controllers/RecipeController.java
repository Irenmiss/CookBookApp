package learnjava.skypro.cookbookapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import learnjava.skypro.cookbookapp.dto.RecipeDTO;
import learnjava.skypro.cookbookapp.model.Recipe;
import learnjava.skypro.cookbookapp.services.RecipeService;
import learnjava.skypro.cookbookapp.services.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найден рецепт",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public RecipeDTO getRecipe(@PathVariable("id") int id) {

        return recipeService.getRecipe(id);
    }

    @PostMapping
    @Operation(
            summary = "Публикация (добавление) рецепта"
    )
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {

        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта"
    )
    public RecipeDTO editRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта"
    )
//    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
//        String deletedRecipe = recipeService.removeRecipe(id);
//        if (deletedRecipe != null) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
    public RecipeDTO deleteRecipe(@PathVariable("id") int id) {
        return recipeService.removeRecipe(id);
    }

    @GetMapping
    @Operation(
            summary = "Вывод всех ранее добавленных рецептов"
    )
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
