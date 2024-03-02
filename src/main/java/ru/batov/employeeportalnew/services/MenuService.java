package ru.batov.employeeportalnew.services;

import org.springframework.stereotype.Service;
import ru.batov.employeeportalnew.models.MenuItem;

import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems1 = Arrays.asList(
                new MenuItem("Добавление трек номеров", "/treck_add", null),
                new MenuItem("Проверить трек номер", "/mail_treck", null),
                new MenuItem("Бюро МСЭ №1", "/buro_mails?buro=201", null),
                new MenuItem("Бюро МСЭ №2", "/buro_mails?buro=202", null),
                new MenuItem("Бюро МСЭ №3", "/buro_mails?buro=203", null),
                new MenuItem("Бюро МСЭ №4", "/buro_mails?buro=204", null),
                new MenuItem("Бюро МСЭ №5", "/buro_mails?buro=205", null),
                new MenuItem("Бюро МСЭ №6", "/buro_mails?buro=206", null),
                new MenuItem("Бюро МСЭ №7", "/buro_mails?buro=207", null),
                new MenuItem("Бюро МСЭ №8", "/buro_mails?buro=208", null),
                new MenuItem("Бюро МСЭ №9", "/buro_mails?buro=209", null),
                new MenuItem("Бюро МСЭ №10", "/buro_mails?buro=210", null),
                new MenuItem("Бюро МСЭ №11", "/buro_mails?buro=211", null),
                new MenuItem("Бюро МСЭ №12", "/buro_mails?buro=212", null),
                new MenuItem("Бюро МСЭ №13", "/buro_mails?buro=213", null),
                new MenuItem("Бюро МСЭ №15", "/buro_mails?buro=215", null),
                new MenuItem("Бюро МСЭ №17", "/buro_mails?buro=217", null),
                new MenuItem("Бюро МСЭ №18", "/buro_mails?buro=218", null),
                new MenuItem("Бюро МСЭ №19", "/buro_mails?buro=219", null),
                new MenuItem("ЭС №1", "/buro_mails?buro=301", null),
                new MenuItem("ЭС №2", "/buro_mails?buro=302", null),
                new MenuItem("ЭС №3", "/buro_mails?buro=303", null));
        List<MenuItem> menuItems2 = Arrays.asList(
                new MenuItem("Бюро", "/mail_treck", null),
                new MenuItem("Экспертный состав", "/mail_treck", null)
        );

        return Arrays.asList(
                //new MenuItem("Главная", "/", null),
                new MenuItem("Время ожидания в очереди", "/ExpertiseTime",null),
                new MenuItem("Создание письма", "/creatingMailForm",null),
                new MenuItem("Отслеживание отправлений", "#",menuItems1),
                new MenuItem("Военослужащие", "/military/index",null),
                new MenuItem("Критерии", "#",menuItems2)
        );
    }
}
