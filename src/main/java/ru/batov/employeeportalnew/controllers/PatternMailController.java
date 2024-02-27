package ru.batov.employeeportalnew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.batov.employeeportalnew.models.PrintMailModel;
import ru.batov.employeeportalnew.services.MailService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatternMailController {

    private final MailService mailService;

    public PatternMailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/creatingMailForm")
    public String creatingMailForm(){
        return "mail/creating_form.html";
    }
    @GetMapping("/printingMail")
    public String printingMail(Model model,
                               @RequestParam(name = "RegDateFrom", required = false, defaultValue = "") String date,
                               @RequestParam(name = "buro", required = false, defaultValue = "") String buro,
                               @RequestParam(name = "executor", required = false, defaultValue = "") String executor,
                               @RequestParam(name = "phone", required = false, defaultValue = "") String phone){



        List<PrintMailModel> listPatternMail = mailService.getListPatternMail(date, buro);

        model.addAttribute("listMail", listPatternMail);
        model.addAttribute("executor", executor);
        model.addAttribute("phone", phone);

        return "mail/printing.html";

    }
}
