package com.imbilalbutt.bilalButtarbisoftv14.controllers;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import com.imbilalbutt.bilalButtarbisoftv14.service.TopNCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class topNCountriesController {

    private int n = 10;

//    @Qualifier ("cde")
    @Autowired
    TopNCountries topNCountries;


    @GetMapping("/topNCountries")
    public String highCases(Model model4){
        List<webData> countryData = topNCountries.getTopNConsListWithParameter(n);
        model4.addAttribute("countryData", countryData);
//        model4.addAttribute("n", n);
        return "topNCountries";
    }

}
