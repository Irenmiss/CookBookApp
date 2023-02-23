package learnjava.skypro.cookbookapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/")
    public String appLaunch() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String getProjectDescription() {
        return "Имя студента: Ирина Кустова; Название проекта: CookBook; Дата создания: 22.02.2023; Описание проекта: Приложение для сайта рецептов";
    }
}
