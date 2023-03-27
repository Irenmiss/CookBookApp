package learnjava.skypro.cookbookapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Первый тестовый контроллер", description = "Описание проекта и проверка работоспособности приложения")
public class FirstController {

    @GetMapping("/")
    @Operation(
            summary = "Проверка работоспособности приложения"
    )
    public String appLaunch() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    @Operation(
            summary = "Описание проекта"
    )
    public String getProjectDescription() {
        return "Имя студента: Ирина Кустова; Название проекта: CookBook; Дата создания: 22.02.2023; Описание проекта: Приложение для сайта рецептов";
    }
}
