package com.imbilalbutt.bilalButtarbisoftv14.models;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Comparator;

@Repository
public class webData  { //implements Comparable<webData>
    private String country;
    private String state;
    private int latestCases;

    public webData() {
        country = "Default";
        state = "State";
        latestCases = 0;
    }

    public webData(String country, String state, int latestCases) {
        this.country = country;
        this.state = state;
        this.latestCases = latestCases;
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

    public int getLatestCases() {
        return latestCases;
    }

    public void setLatestCases(int latestCases) {
        this.latestCases = latestCases;
    }
//
//    Comparator<webData> compareById = (webData o1, webData o2) ->
//            o1.getLatestCases().compareTo(o2.getLatestCases() );

//    @Override
//    public int compareTo(webData o) {
//        return this.getLatestCases()
//                .compareToIgnoreCase((o.getLatestCases()));
//    }


    @Override
    public String toString() {
        return "getCsvDataService{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", latestCases=" + latestCases +
                '}';
    }

//    @Override
//    public int compareTo(@NotNull webData o) {
//        return this.getLatestCases()
//                .compareTo((o.getLatestCases()));
//    }
}
