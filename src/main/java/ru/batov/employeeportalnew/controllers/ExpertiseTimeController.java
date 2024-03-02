package ru.batov.employeeportalnew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpertiseTimeController {

    @GetMapping("/ExpertiseTime")
    public String viewTime(){
        return "time/ExpertiseTime.html";
    }
}
