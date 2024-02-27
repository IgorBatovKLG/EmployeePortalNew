package ru.batov.employeeportalnew.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.batov.employeeportalnew.models.MenuItem;
import ru.batov.employeeportalnew.services.MenuService;

import java.util.List;

@ControllerAdvice
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ModelAttribute("menuItems")
    public List<MenuItem> getMenuItems() {
        return menuService.getMenuItems();
    }
}
