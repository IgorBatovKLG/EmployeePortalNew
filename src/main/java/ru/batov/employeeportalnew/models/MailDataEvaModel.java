package ru.batov.employeeportalnew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDataEvaModel {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ExamBuroName")
    @Expose
    private String examBuroName;
    @SerializedName("InvalidityGroupName")
    @Expose
    private String invalidityGroupName;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("SecondName")
    @Expose
    private String secondName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("DecisionDate")
    @Expose
    private String decisionDate;
    @SerializedName("BlankNumber")
    @Expose
    private String blankNumber;
    @SerializedName("IpraChildNumber")
    @Expose
    private String ipraChildNumber;
    @SerializedName("IpraNumber")
    @Expose
    private String ipraNumber;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PersonGender")
    @Expose
    private String personGender;
    @SerializedName("BuroManagerName")
    @Expose
    private String buroManagerName;
    @SerializedName("ExpSostavManagerName")
    @Expose
    private String expSostavManagerName;
    @SerializedName("PatientRepPersonLastName")
    @Expose
    private String patientRepPersonLastName;
    @SerializedName("PatientPersonAge")
    @Expose
    private String patientPersonAge;
    @SerializedName("PurposesXml")
    @Expose
    private String purposesXml;
    @SerializedName("InvalidityPeriodId")
    @Expose
    private String invalidityPeriodId;
    @SerializedName("PrpNumber")
    @Expose
    private String prpNumber;
    @SerializedName("UptProfLossDegree")
    @Expose
    private String uptProfLossDegree;
    @SerializedName("UptDysfunctionDegreeTermId")
    @Expose
    private String uptDysfunctionDegreeTermId;
}
