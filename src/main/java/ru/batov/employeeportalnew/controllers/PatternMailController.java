package ru.batov.employeeportalnew.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class PatternMailController {

    public String creatingMailForm(){
        //todo creatingMailForm
        return "mail/creating_form.html";
    }

    public String printingMail(){
        //todo printingMail
        return "mail/printingMail";
    }
}
