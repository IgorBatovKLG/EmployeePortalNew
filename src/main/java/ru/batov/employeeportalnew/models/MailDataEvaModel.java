package ru.batov.employeeportalnew.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDataEvaModel {
    private String Id;
    private String ExamBuroName;
    private String InvalidityGroupName;
    private String FirstName;
    private String SecondName;
    private String LastName;
    private String DecisionDate;
    private String BlankNumber;
    private String IpraChildNumber;
    private String IpraNumber;
    private String PersonGender;
    private String BuroManagerName;
    private String ExpSostavManagerName;
    private String Address;
    private String PatientRepPersonLastName;
    private String PatientPersonAge;
    private String PurposesXml;
    private String InvalidityPeriodId;
    private String PrpNumber;
    private String UptProfLossDegree;
    private String UptDysfunctionDegreeTermId;
}
