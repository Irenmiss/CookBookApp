package learnjava.skypro.cookbookapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import learnjava.skypro.cookbookapp.services.RecipeFilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы для раздела с рецептами", description = "CRUD-операции с файлами, содержащими рецепты")
public class RecipeFilesController {
    private final RecipeFilesService recipeFilesService;

    public RecipeFilesController(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }
    @GetMapping(value = "/export")
    @Operation(
            summary = "Скачивание файла с рецептами в виде json-файла"
    )
    public ResponseEntity<InputStreamResource> downloadRecipesFile() throws FileNotFoundException {
        File recipeFileToDownload = recipeFilesService.getDataFile();
        if (recipeFileToDownload.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFileToDownload));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(recipeFileToDownload.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка нового json-файла с рецептами с заменой существующего"
    )
    public ResponseEntity<Void> uploadRecipesFile(@RequestParam MultipartFile recipeFile) {
        recipeFilesService.cleanDataFile();
        File recipeFileToUpload = recipeFilesService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(recipeFileToUpload)) {
            IOUtils.copy(recipeFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
