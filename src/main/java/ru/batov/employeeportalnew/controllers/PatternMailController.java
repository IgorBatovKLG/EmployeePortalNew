package ru.batov.employeeportalnew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatternMailController {

    @GetMapping("/creatingMailForm")
    public String creatingMailForm(){
        //todo creatingMailForm
        return "mail/creating_form.html";
    }

    public String printingMail(){
        //todo printingMail
        return "mail/printingMail";
    }
}
