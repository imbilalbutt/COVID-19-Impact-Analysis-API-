package com.imbilalbutt.bilalButtarbisoftv14.controllers;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import com.imbilalbutt.bilalButtarbisoftv14.service.getCsvDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class csvDataController {

    @Autowired
    private getCsvDataService csvData;

    @GetMapping("/csvData")
    public String home(Model model) {
        // returns "Name" : which points to template.
        // Neeche wali sab chezien "csvData.html" k ander istemal ho rahi hain
        // data display krne k lye.
//        getCsvDataService csvData = new getCsvDataService();
        List<webData> allStats = csvData.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat-> stat.getLatestCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        return "csvData";
    }
}
