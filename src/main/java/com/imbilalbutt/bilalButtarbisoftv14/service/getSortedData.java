package com.imbilalbutt.bilalButtarbisoftv14.service;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class getSortedData {
    private List<webData> allStats2 = new ArrayList<>();

    @Autowired
    private getCsvDataService coronaVirusData2;
//    private getCsvDataService coronaVirusData2 = new getCsvDataService();

    ////
    ////** Constructors
    ////

//    public getSortedData() {
//        this.allStats2 = new ArrayList<>();
//        this.coronaVirusData2 = new getCsvDataService();
//    }

//    public getSortedData(List<webData> allStats2) {
//        this.allStats2 = allStats2;
//    }

//    @Qualifier("getSortedData")
//    public getSortedData(getCsvDataService coronaVirusData2) {
//        this.coronaVirusData2 = coronaVirusData2;
//    }

//    public getSortedData(List<webData> allStats2, getCsvDataService coronaVirusData2) {
//        this.allStats2 = allStats2;
//        this.coronaVirusData2 = coronaVirusData2;
//    }

    ////
    ////** Class Methods
    ////
//    @Resource
//    @Qualifier("sortedData")
//    @Qualifier("sortedData")
//    @Bean ("sortedData")
    @PostConstruct
    public void fetchDataAndSort() throws IOException, InterruptedException {
//        List<getCsvDataService> sorted =this.allStats;
//        this.coronaVirusData.getAllStats();

//        System.out.println(coronaVirusData2.getAllStats()); //Yahan tak data original form mein hai
//        allStats2 = coronaVirusData2.getAllStats();
//        List<webData> tempData = new ArrayList<>();
//        tempData = allStats2;


        List<webData> tempData = new ArrayList<>(this.coronaVirusData2.getAllStats());
//        tempData = this.coronaVirusData2.fetchData(); //undo 1
//        tempData = this.coronaVirusData2.getAllStats();

//        this.allStats = coronaVirusData.getAllStats(); // only uncomment it to revert

//        Collections.sort(allStats);
//        Collections.sort(allStats, Collections.reverseOrder());

//        Comparator.comparing(webData::getLatestCases).reversed();
//        System.out.println(this.allStats);

//        System.out.println(coronaVirusData2.getAllStats());  //Yahan tak data original form mein hai
        Collections.sort(tempData, new Comparator<webData>() {
            @Override
            public int compare(webData o1, webData o2) {
                return Integer.compare(o1.getLatestCases(), o2.getLatestCases());
//                return o1.getLatestCases().compareTo(o2.getLatestCases());
            }
        });

//        System.out.println(coronaVirusData2.getAllStats()); // piece of code @ 48-54-> idr se data change ho raha ha

        Collections.reverse(tempData);
//        Collections.sort(tempData, Collections.reverseOrder());
        this.allStats2 = tempData;

//        String country = "Australia";
//        List<webData> countryData = new ArrayList<>();
//        for (webData singleWebDataElement: this.allStats2) {
//            if(singleWebDataElement.getCountry().equals(country)) {
//                System.out.println(singleWebDataElement.getCountry() + " " + singleWebDataElement.getState() + " " + singleWebDataElement.getLatestCases());
//                countryData.add(singleWebDataElement);
//            }
//        }

//        return this.allStats2;
    }


    ////
    ////** Getters
    ////
//    @Bean ("sortedDataBean")
//    @Qualifier ("sortedData")
//    @Bean ("getSortedData")
    public List<webData> getAllSortedStats() {
        return this.allStats2;
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// version 1.0
//
// Below code works fine, the below code is redundant that's why commenting it and using above code.
// Sara "getCsvDataService.java" wala kam kerega.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
// This service is sorting the data in descending order
// w.r.t. latestCases reported today.
//
@Service
public class getSortedData {

    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<webData> allStats = new ArrayList<>();
    getCsvDataService cxv;

    @Autowired
    public getSortedData() {
        this.allStats = allStats;
    }

    public getSortedData(List<webData> allStats) {
        this.allStats = allStats;
    }

    @PostConstruct
    public List<webData> fetchData() throws IOException, InterruptedException {
//        List<webData> abc = cxv.fetchData();
        List<webData> tempData = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest rqst = HttpRequest.newBuilder()
                .uri(URI.create(csv_file_url)).build();

        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(rspns.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            //locationStats locationStat = new locationStats()
            webData locationStat = new webData();
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setState(record.get("Province/State"));
            locationStat.setLatestCases(Integer.parseInt(record.get(record.size() - 1)));

            tempData.add(locationStat);
//            String id = record.get("Province/State");
//            System.out.println(locationStat);
        }


        Collections.sort(tempData, new Comparator<webData>() {
            @Override
            public int compare(webData o1, webData o2) {
                return Integer.compare(o1.getLatestCases(), o2.getLatestCases());
//                return o1.getLatestCases().compareTo(o2.getLatestCases());
            }
        });

        Collections.reverse(tempData);
//        Collections.sort(tempData, Collections.reverseOrder());
        //System.out.println(rspns.body());
        this.allStats = tempData;
        return this.allStats;
    }

    public List<webData> getAllSortedStats() {
        //\\
        return allStats;
    }
}

 */
