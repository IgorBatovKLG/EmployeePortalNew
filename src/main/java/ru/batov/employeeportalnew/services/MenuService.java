package ru.batov.employeeportalnew.services;

import org.springframework.stereotype.Service;
import ru.batov.employeeportalnew.models.MenuItem;

import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    public List<MenuItem> getMenuItems() {
        // Здесь может быть логика для загрузки данных из базы данных или другого источника
        // В этом примере просто возвращается фиктивный список пунктов меню
        List<MenuItem> menuItems1 = Arrays.asList(
                new MenuItem("Создание письма", "/2", null),
                new MenuItem("Трек номера", "/1", null),
                new MenuItem("Статус отправления", "/1", null));


        return Arrays.asList(
                new MenuItem("Главная", "/", null),
                new MenuItem("Время ожидания в очереди", "/menu2",null),
                new MenuItem("Письма", "/menu1",menuItems1),
                new MenuItem("Военослужащие", "/menu2",null)
        );
    }
}
