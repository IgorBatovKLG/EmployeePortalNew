package ru.batov.employeeportalnew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MilitaryController {

    @GetMapping("/military/index")
    public String MilitaryIndex(){
        return "military/index.html";
    }

    @GetMapping("/military/get_info")
    public String MilitaryGetInfo(){
        return "military/list_military.html";
    }

    @GetMapping("/military/edit")
    public String MilitaryEdit(){
        return "military/edit_object.html";
    }

    @GetMapping("/military/save")
    public String MilitarySave(){
        return "redirect:/military/index";
    }

    @GetMapping("/military/load")
    public String MilitaryLoad(){

        return "military/print.html";
    }

}
