package learnjava.skypro.cookbookapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import learnjava.skypro.cookbookapp.services.IngredientFilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/ingredient/files")
@Tag(name = "Файлы для раздела с ингредиентами", description = "CRUD-операции с файлами, содержащими ингредиенты")

public class IngredientFilesController {
    private final IngredientFilesService ingredientFilesService;

    public IngredientFilesController(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }
    @PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка нового json-файла с ингредиентами с заменой существующего"
    )
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile ingredientFile) {
        ingredientFilesService.cleanDataFile();
        File ingredientFileToUpload = ingredientFilesService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(ingredientFileToUpload)) {
            IOUtils.copy(ingredientFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
