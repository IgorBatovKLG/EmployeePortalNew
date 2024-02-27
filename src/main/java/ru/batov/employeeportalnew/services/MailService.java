package ru.batov.employeeportalnew.services;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;
import ru.batov.employeeportalnew.models.MailDataEvaModel;
import ru.batov.employeeportalnew.models.PrintMailModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {


    private final Gson gson;
    private final EvaGetDataService evaGetDataService;

    public MailService(Gson gson, EvaGetDataService evaGetDataService) {
        this.gson = gson;
        this.evaGetDataService = evaGetDataService;
    }

    public List<PrintMailModel> getListPatternMail(String date, String buro){
        String stringJson = evaGetDataService.getStringJson("{\"Columns\":[\"ExamBuroName\",\"InvalidityGroupName\",\"FirstName\",\"SecondName\",\"LastName\",\"DecisionDate\",\"BlankNumber\",\"IpraChildNumber\",\"IpraNumber\",\"Address\",\"PersonGender\",\"BuroManagerName\",\"ExpSostavManagerName\",\"PatientRepPersonLastName\",\"PatientPersonAge\",\"PurposesXml\",\"InvalidityPeriodId\",\"PrpNumber\",\"UptProfLossDegree\",\"UptDysfunctionDegreeTermId\"]," +
                "\"Conditions\":[" +
                "{\"FieldName\":\"DecisionDate\",\"Value\":null,\"Values\":[\"" + date + "T00:00:00\",\"" + date + "T00:00:00\"],\"Type\":9,\"IsNegative\":false,\"Disabled\":false},{\"FieldName\":\"ExaminationPurposeID\",\"Type\":11,\"IsNegative\":false,\"Disabled\":false,\"Value\":null,\"Values\":[1,2,6,7,8]}," +
                "{\"FieldName\":\"ExamBuroId\",\"Type\":11,\"IsNegative\":false,\"Disabled\":false,\"Value\":null,\"Values\":[\"" + buro + "\"]}" +
                "],\"HidePeopleDoubles\":false,\"Page\":1,\"PageSize\":100,\"SortField\":null,\"IsSortDesc\":false}");

        String list = JsonPath.parse(stringJson).read("List").toString();
        MailDataEvaModel[] model = gson.fromJson(list, MailDataEvaModel[].class);
        List<PrintMailModel> printMailModels = new ArrayList<>();
        for (MailDataEvaModel mailDataEvaModel : model) {
            printMailModels.add(createPatternMail(mailDataEvaModel));
        }
        return printMailModels;
    }

    public PrintMailModel createPatternMail(MailDataEvaModel model){

        boolean child = false;
        boolean group = false;
        boolean upt = false;
        boolean ipra = false;
        boolean prp = false;

        if(18 > Integer.parseInt(model.getPatientPersonAge().split(" ")[0])){ //todo проверить
            child = true;
        }
        if (model.getPurposesXml().toLowerCase().contains("установления")){
            group = true;
        } else if (model.getPurposesXml().toLowerCase().contains("индивидуальной программы")){
            ipra = true;
        }
        if (model.getPurposesXml().toLowerCase().contains("определения степени утраты")){
            upt = true;
        } else if (model.getPurposesXml().toLowerCase().contains("разработки программы реабилитации лица")){
            prp = true;
        }
        List<String> strings = new ArrayList<>();
        if (child){
            if(group) {
                strings = setDisabilityGroupChildren(model);
            } else if (ipra) {
                strings = developIPRAChildren(model);
            } else if (upt) {
                strings = setUPT(model);
            } else if (prp){
                strings = developPRP(model);
            }

        } else {
            if(group) {
                strings = setDisabilityGroupAdults(model);
            } else if (ipra) {
                strings = developIPRAAdults(model);
            } else if (upt) {
                strings = setUPT(model);
            } else if (prp){
                strings = developPRP(model);
            }
        }
        String typeManager = "Руководитель бюро медико-социальной экспертизы";
        if (model.getExamBuroName().toLowerCase().contains("состав")){
            typeManager = "Руководитель экспертного состава медико-социальной экспертизы";
            model.setBuroManagerName(model.getExpSostavManagerName());
        }
        String sexAndName = "НЕ УКАЗАН ПОЛ!";
        if (model.getPersonGender().equals("ЖЕН")){
            sexAndName = "Уважаемая " + model.getFirstName() + " " + model.getSecondName() + "!";
        } else {
            sexAndName = "Уважаемый " + model.getFirstName() + " " + model.getSecondName() + "!";
        }
        if (model.getPatientRepPersonLastName().split(" ").length>2){
            sexAndName = "Уважаемый(ая) " + model.getPatientRepPersonLastName().split(" ")[1] + " " + model.getPatientRepPersonLastName().split(" ")[2];
        }
        PrintMailModel printMailModel = PrintMailModel.builder()
                .adres(model.getAddress())
                .fullName(model.getLastName() + " " + model.getFirstName() +  " " + model.getSecondName())
                .sexAndName(sexAndName)
                .paragraphs(strings)
                .managerType(typeManager)
                .managerName(model.getBuroManagerName())
                .build();

        return printMailModel;
    }
    public List<String> setDisabilityGroupAdults(MailDataEvaModel model){
        String paragraf1 = "";
        if (model.getInvalidityGroupName().equals("Инвалидность не установлена")) {
            paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Вы были освидетельствованы " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы принято решение: " + model.getInvalidityGroupName().toLowerCase() + invalidityPeriodId(model.getInvalidityPeriodId()) +  ".";
        } else {
            paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Вы были освидетельствованы " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы Вам установлена " + model.getInvalidityGroupName().toLowerCase() + invalidityPeriodId(model.getInvalidityPeriodId()) + " и разработана индивидуальная программа реабилитации или абилитации инвалида(ИПРА).";

            if (model.getPatientRepPersonLastName().split(" ").length > 1) {
                paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Гражданин " + model.getLastName() + " " + model.getFirstName() +  " " + model.getSecondName() + " опекуном которого вы являетесь был освидетельствован " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы принято решение: " + model.getInvalidityGroupName().toLowerCase() + invalidityPeriodId(model.getInvalidityPeriodId()) + " и разработана индивидуальная программа реабилитации или абилитации инвалида(ИПРА).";

            }
        }
        List<String> strings = additionalText(model);
        strings.add(0, paragraf1);
        return strings;
    }
    public List<String> developIPRAAdults(MailDataEvaModel model) {
        String paragraf1 = "";
        paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Вы были освидетельствованы " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы разработана индивидуальная программа реабилитации или абилитации инвалида (ИПРА).";

        if (model.getPatientRepPersonLastName().split(" ").length > 1) {
            paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Гражданин " + model.getLastName() + " " + model.getFirstName() +  " " + model.getSecondName() + " опекуном которого вы являетесь был освидетельствован " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы разработана индивидуальная программа реабилитации или абилитации инвалида (ИПРА).";

        }
        List<String> strings = additionalText(model);
        strings.add(0, paragraf1);
        return strings;
    }
    public List<String> setUPT(MailDataEvaModel model){
        String paragraf1 = "";
        if (model.getUptProfLossDegree().length()<2){
            paragraf1 = "В соответствии с Правилами установления степени утраты профессиональной трудоспособности в результате несчастных случаев на производстве и профессиональных заболеваний, утвержденными постановлением Правительства Российской Федерации от 16.10.2000 №789,  Критериями определения степени утраты профессиональной трудоспособности в результате несчастных случаев на производстве и профессиональных заболеваний, утвержденными приказом Минтруда России от 30.09.2020 № 687н, Вы были освидетельствованы " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы Вам не определена степень утраты профессиональной трудоспособности в результате несчастных случаев на производстве и профессиональных заболеваний.";
        }else {
            paragraf1 = "В соответствии с Правилами установления степени утраты профессиональной трудоспособности в результате несчастных случаев на производстве и профессиональных заболеваний, утвержденными постановлением Правительства Российской Федерации от 16.10.2000 №789,  Критериями определения степени утраты профессиональной трудоспособности в результате несчастных случаев на производстве и профессиональных заболеваний, утвержденными приказом Минтруда России от 30.09.2020 № 687н, Вы были освидетельствованы " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы Вам определено "+model.getUptProfLossDegree()+" утраты профессиональной трудоспособности в результате несчастных случаев на производстве и профессиональных заболеваний на срок "+model.getUptDysfunctionDegreeTermId()+" и разработана программа реабилитации пострадавшего в результате несчастного случая на производстве и профессионального заболевания (ПРП).";
        }
        List<String> strings = additionalText(model);
        strings.add(0, paragraf1);
        return strings;
    }
    public List<String> developPRP(MailDataEvaModel model){
        String paragraf1 = "";
        paragraf1 = "В соответствии с п. 22 (м) Правил признания лица инвалидом, утвержденных постановлением Правительства РФ от 05.04.2022 № 588 «О признании лица инвалидом», приказом Минтруда России от 30.12.2020 № 982н «Об утверждении формы программы реабилитации пострадавшего в результате несчастного случая на производстве и профессионального заболевания и порядка ее составления» Вы были освидетельствованы " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы Вам  разработана программа реабилитации пострадавшего в результате несчастного случая на производстве и профессионального заболевания (ПРП).";
        List<String> strings = additionalText(model);
        strings.add(0, paragraf1);
        return strings;
    }
    public List<String> setDisabilityGroupChildren(MailDataEvaModel model){
        String paragraf1 = "";
        if (model.getInvalidityGroupName().equals("Инвалидность не установлена")) {
            paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Ваш ребенок " + model.getLastName() + " " + model.getFirstName() +  " " + model.getSecondName() + " был освидетельствован " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы принято решение: " + model.getInvalidityGroupName().toLowerCase() + invalidityPeriodId(model.getInvalidityPeriodId()) + ".";
        } else {
            paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Ваш ребенок " + model.getLastName() + " " + model.getFirstName() +  " " + model.getSecondName() + " был освидетельствован " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы установлена " + model.getInvalidityGroupName().toLowerCase() + invalidityPeriodId(model.getInvalidityPeriodId()) + " и разработана индивидуальная программа реабилитации или абилитации (ИПРА).";
        }
        List<String> strings = additionalText(model);
        strings.add(0, paragraf1);
        return strings;
    }
    public List<String> developIPRAChildren(MailDataEvaModel model){
        String paragraf1 = "";
        paragraf1 = "Согласно Постановлению Правительства Российской Федерации от 05 апреля 2022г. № 588 «О Признании лица инвалидом» Ваш ребенок " + model.getLastName() + " " + model.getFirstName() +  " " + model.getSecondName() + " был освидетельствован " + model.getDecisionDate() + " в " + model.getExamBuroName().toLowerCase() + ". По результатам проведения медико-социальной экспертизы разработана индивидуальная программа реабилитации или абилитации (ИПРА).";
        List<String> strings = additionalText(model);
        strings.add(0, paragraf1);
        return strings;
    }
    private String invalidityPeriodId(String period){
        if (!(period ==null)){
            if (period.contains("на 1 год")) {
                return " сроком на один год ";
            }
            else if (period.contains("на 2 года")) {
                return " сроком на два года";
            }
            else if (period.contains("на 5 лет")){
                return " сроком на пять лет";
            }
            else if (period.contains("до 18 лет")) {
                return " до 18 лет ";
            }
            else if (period.contains("до 14 лет")) {
                return " до 14 лет ";
            }
            else if (period.contains("бессрочно")) {
                return " бессрочно ";
            }
        }
        return "";
    }

    public List<String> additionalText(MailDataEvaModel model){
        List<String> strings = new ArrayList<>();

        String paragraf2 = "В случае несогласия с решением бюро медико-социальной экспертизы Вы (Ваш законный или уполномоченный представитель) в месячный срок со дня получения решения можете обжаловать его в главное бюро, в случае несогласия с решением главного бюро — в месячный срок в Федеральное бюро МСЭ. Решения бюро, главного бюро, Федерального бюро могут быть обжалованы в суд в порядке, установленном законодательством Российской Федерации.";
        String paragraf3 = "Приложение:";
        strings.add(paragraf2);
        strings.add(paragraf3);
        try {
            if (model.getBlankNumber().isBlank()
                    & model.getIpraChildNumber().isBlank()
                    & model.getIpraNumber().isBlank()) {
                paragraf3 = "";
            }
        } catch (NullPointerException e){
            paragraf3 = "";
        }
        int id = 1;
        try {

            if (!model.getBlankNumber().isBlank()){
                String paragraf6 = id + ". Справка " + model.getBlankNumber();
                strings.add(paragraf6);
                id++;
            }
        } catch (NullPointerException e) {}
        if (!model.getIpraChildNumber().isBlank()){
            String paragraf6 = id + ". ИПРА " + model.getIpraChildNumber();
            strings.add(paragraf6);
            id++;
        }

        if (!model.getIpraNumber().isBlank()){
            String paragraf6 = id + ". ИПРА " + model.getIpraNumber();
            strings.add(paragraf6);
            id++;
        }
        if (!model.getPrpNumber().isBlank()){
            String paragraf6 = id + ". ПРП " + model.getPrpNumber();
            strings.add(paragraf6);
            id++;
        }
        return strings;
    }
}
