package ru.batov.employeeportalnew.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    private String name;
    private String url;
    private List<MenuItem> subMenu;

}
