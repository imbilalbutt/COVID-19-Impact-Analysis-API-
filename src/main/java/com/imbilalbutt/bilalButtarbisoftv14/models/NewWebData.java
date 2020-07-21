package com.imbilalbutt.bilalButtarbisoftv14.models;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// version 2.0 of NewWebData.java created for java class name "readCsvDataWithRespectToJava v2.2"
//
// Below commented part works with readCsvDataWithRespectToDate v1, v2, v3, v2.1
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class NewWebData  {
    private String country;
    private String state;
    private List<String> CasesInASingleDay;

    public NewWebData() {
        country = "Default";
        state = "State";
        CasesInASingleDay = new ArrayList<>();
    }

    public NewWebData(String country, String state, List<String> casesInASingleDay) {
        this.country = country;
        this.state = state;
        this.CasesInASingleDay = casesInASingleDay;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public List<String> getCasesInASingleDay() {
        return CasesInASingleDay;
    }

    public void setCasesInASingleDay(List<String> casesInASingleDay) {
        this.CasesInASingleDay = casesInASingleDay;
    }

    public void setCasesInASingleDay(String country, String state, List<String> casesInASingleDay) {
        this.country = country;
        this.state = state;
        this.CasesInASingleDay = casesInASingleDay;
    }

    public NewWebData setItAll(NewWebData wbd){
        this.country = wbd.country;
        this.state = wbd.state;
        this.CasesInASingleDay = wbd.CasesInASingleDay;

        return this;
    }

    public void addCase(List<String> casesInASingleDay){
        this.CasesInASingleDay.addAll(casesInASingleDay);
    }

    // Adds in single case in a list
    // Meaning for a country & state, add a case.
    public void addCase(String aCase){
        this.CasesInASingleDay.add(aCase);
    }

    @Override
    public String toString() {
        return "NewWebData{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", CasesInASingleDay=" + CasesInASingleDay +
                '}';
    }

}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// version 1.0 of NewWebData.java
//
// Below commented part works with readCsvDataWithRespectToDate v1.0, v2.0, v3, v2.1
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////// did not made this repo
//@Component
//public class NewWebData  { //implements Comparable<NewWebData>
//    private String country;
//    private String state;
//    private List<Integer> CasesInASingleDay;
//
//    public NewWebData() {
//        country = "Default";
//        state = "State";
//        CasesInASingleDay = new ArrayList<>();
//    }
//
//    public NewWebData(String country, String state, List<Integer> casesInASingleDay) {
//        this.country = country;
//        this.state = state;
//        this.CasesInASingleDay = casesInASingleDay;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//
//    public List<Integer> getCasesInASingleDay() {
//        return CasesInASingleDay;
//    }
//
//    public void setCasesInASingleDay(List<Integer> casesInASingleDay) {
//        this.CasesInASingleDay = casesInASingleDay;
//    }
//
//    public void setCasesInASingleDay(String country, String state, List<Integer> casesInASingleDay) {
//        this.country = country;
//        this.state = state;
//        this.CasesInASingleDay = casesInASingleDay;
//    }
//
//    public NewWebData setItAll(NewWebData wbd){
//        this.country = wbd.country;
//        this.state = wbd.state;
//        this.CasesInASingleDay = wbd.CasesInASingleDay;
//
//        return this;
//    }
//
//        public void addCase(List<Integer> casesInASingleDay){
//            this.CasesInASingleDay.addAll(casesInASingleDay);
//    }
//
//    // Meaning for a country & state, add a case.
//    public void addCase(int aCase){
//        this.CasesInASingleDay.add(aCase);
//    }
//
//    @Override
//    public String toString() {
//        return "NewWebData{" +
//                "country='" + country + '\'' +
//                ", state='" + state + '\'' +
//                ", CasesInASingleDay=" + CasesInASingleDay +
//                '}';
//    }
//
//}


