package ru.batov.employeeportalnew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndexPage() {
        // Здесь вы можете добавить данные, которые вы хотите передать в ваш шаблон Thymeleaf
        // Пример: model.addAttribute("newsList", yourNewsList);

        return "index.html";
    }
}
