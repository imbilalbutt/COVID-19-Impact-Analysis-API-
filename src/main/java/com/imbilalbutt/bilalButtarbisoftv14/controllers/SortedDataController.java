package com.imbilalbutt.bilalButtarbisoftv14.controllers;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import com.imbilalbutt.bilalButtarbisoftv14.service.getSortedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SortedDataController {

    @Autowired
    private getSortedData sortedData;

    @GetMapping("/highest")
    public String highCases(Model model2){
        List<webData> sortedStats = sortedData.getAllSortedStats();
        int totalReportedCases = sortedStats.stream().mapToInt(stat-> stat.getLatestCases()).sum();
        model2.addAttribute("sortedStats", sortedStats);
        model2.addAttribute("totalReportedCases", totalReportedCases);
        return "highest";
    }
}
