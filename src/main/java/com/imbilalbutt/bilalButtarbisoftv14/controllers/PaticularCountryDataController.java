package com.imbilalbutt.bilalButtarbisoftv14.controllers;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import com.imbilalbutt.bilalButtarbisoftv14.service.PatricularCountryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

// Controller for ParticularCountryDataService
@Controller
public class PaticularCountryDataController {

   private String countryName = "Australia";


    //@Qualifier("particularCountryDataServiceBean")
    @Autowired
    PatricularCountryDataService singleCountry;

//    public PatricularCountryDataService singleCountry2("Pakistan");

//    public countryController() {
//        this.countryName = "Australia";
//    }
//
//    public countryController(String countryName){
//        this.countryName = countryName;
//    }

    @GetMapping("/country")
    public String highCases(Model model3){
        List<webData> countryData = singleCountry.getCountryDataWithParameter(countryName);
//        List<webData> countryData = singleCountry.getCountryData();
        model3.addAttribute("countryData", countryData);
        model3.addAttribute("countryName", countryName);
        return "country";
    }

    public String getCountryName() {
        return countryName;
    }

    public PatricularCountryDataService getSingleCountry() {
        return singleCountry;
    }

//    private List<webData> sortedStats_property= new ArrayList<>();
//    @Autowired private getSortedData sortedData;
//
//    @GetMapping("/country")
//    public String caseInACountry(Model model3){
//        List<webData> allTempData = sortedData.getAllSortedStats();
//        List<webData> tempData = new ArrayList<>();
//
//        for (webData singleWebDataElement: allTempData) {
//            if(singleWebDataElement.getCountry().equals(countryName)) {
////                System.out.println(singleWebDataElement.getCountry() + " " + singleWebDataElement.getState() + " " + singleWebDataElement.getLatestCases());
//                tempData.add(singleWebDataElement);
//            }
//        }
//
//        this.sortedStats_property = tempData;
//        model3.addAttribute("sortedStats_property", this.sortedStats_property);
//        model3.addAttribute("countryName", countryName);
//        return "country";
//        }
}