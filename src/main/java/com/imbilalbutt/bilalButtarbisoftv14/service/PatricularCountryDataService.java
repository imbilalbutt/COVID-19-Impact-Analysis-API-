package com.imbilalbutt.bilalButtarbisoftv14.service;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service //("abc")
public class PatricularCountryDataService {

    // Issue: The problem is it is okay to ExtarctCountryDetails() but with
    // ExtractCountryWithParameter(String name) is causing Bean issue.
    private String country;
    private List<webData> countryData = new ArrayList<>(); //allStats
    @Autowired
    private getSortedData gsd;

    public PatricularCountryDataService(){
        this.country = "Australia";
    }

    public PatricularCountryDataService(String country) {
        this.country = country;
    }


//    @PostConstruct
//    public void ExtractCountryDetails(String country) {
//        List<webData> allCountryList = gsd.getAllSortedStats();
//        List<webData> tempCountryData = new ArrayList<>();
//        for (webData singleWebDataElement : allCountryList) {
//            if (singleWebDataElement.getCountry().equals(country)) {
////                System.out.println(singleWebDataElement.getCountry() + " " + singleWebDataElement.getState() + " " + singleWebDataElement.getLatestCases());
//                tempCountryData.add(singleWebDataElement);
//            }
//        }
//        this.countryData = tempCountryData;
//    }

    @PostConstruct
    public void ExtractCountryDetails() {
        List<webData> allCountryList = gsd.getAllSortedStats();
        List<webData> tempCountryData = new ArrayList<>();
        for (webData singleWebDataElement : allCountryList) {
            if (singleWebDataElement.getCountry().equals(country)) {
//                System.out.println(singleWebDataElement.getCountry() + " " + singleWebDataElement.getState() + " " + singleWebDataElement.getLatestCases());
                tempCountryData.add(singleWebDataElement);
            }
        }
        this.countryData = tempCountryData;
    }

    public List<webData> getCountryDataWithParameter(String countryName){
//        ExtractCountryDetails(countryName);
        ExtractCountryDetails();
        return this.countryData;
    }

    public List<webData> getCountryData() {
        return this.countryData;
    }

//    @Bean ("particularCountryDataServiceBean")
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public PatricularCountryDataService patricularCountryDataService(){
//        return new PatricularCountryDataService();
//    }

}
