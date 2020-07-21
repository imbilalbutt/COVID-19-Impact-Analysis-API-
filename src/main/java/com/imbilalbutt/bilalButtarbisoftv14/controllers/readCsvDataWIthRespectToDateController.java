package com.imbilalbutt.bilalButtarbisoftv14.controllers;


import com.imbilalbutt.bilalButtarbisoftv14.models.NewWebData;
import com.imbilalbutt.bilalButtarbisoftv14.service.readCsvDataWithRespectToDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class readCsvDataWIthRespectToDateController {

    @Autowired
    private readCsvDataWithRespectToDate dateWiseData;


    @GetMapping("/date")
    public String home(Model model) {
        List<NewWebData> allStats = this.dateWiseData.getAllStats();
        model.addAttribute("allStats", allStats);
        return "date";
    }
}

