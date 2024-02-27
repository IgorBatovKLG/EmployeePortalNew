package ru.batov.employeeportalnew.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrintMailModel {
    private String adres;
    private String fullName;
    private String sexAndName;
    private List<String> paragraphs;
    private String managerType;
    private String managerName;

}
