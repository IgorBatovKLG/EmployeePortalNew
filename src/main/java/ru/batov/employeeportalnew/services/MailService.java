package ru.batov.employeeportalnew.services;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import ru.batov.employeeportalnew.models.MailDataEvaModel;

@Service
public class MailService {

    private final Gson gson;
    private final EvaGetDataService evaGetDataService;

    public MailService(Gson gson, EvaGetDataService evaGetDataService) {
        this.gson = gson;
        this.evaGetDataService = evaGetDataService;
    }

    public String createPatternMail(MailDataEvaModel model){
boolean child = false;
boolean group = false;
boolean upt = false;
boolean ipra = false;
boolean prp = false;
    }
}
