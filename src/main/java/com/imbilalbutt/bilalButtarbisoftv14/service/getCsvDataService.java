package com.imbilalbutt.bilalButtarbisoftv14.service;

import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//@Component
//@Qualifier ("csvData")
@Service
public class getCsvDataService{

    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//    private myUrl csv_file_url ;
    private List<webData> allStats = new ArrayList<>();


    ////
    ////** Constructors
    ////
//    public getCsvDataService() {
//        this.allStats = new ArrayList<>();
//        csv_file_url = "empty";
//    }

//    public getCsvDataService(String csv_file_url) {
//        this.csv_file_url = csv_file_url;
//    }

//@Qualifier("csvData")
//    public getCsvDataService( List<webData> allStats) {
//        this.allStats = allStats;
//    }

//    public getCsvDataService(String csv_file_url, List<webData> allStats) {
//        this.csv_file_url = csv_file_url;
//        this.allStats = allStats;
//    }

    ////
    ////** Class Methods
    ////

//    @Bean ("csvData")
    @PostConstruct
    //public List<webdata> fetchData() throws IOException, InterruptedException {
    public void fetchData() throws IOException, InterruptedException {
        List<webData> tempData = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest rqst = HttpRequest.newBuilder()
                .uri(URI.create(csv_file_url)).build();

        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(rspns.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            /*locationStats locationStat = new locationStats() */
            webData locationStat = new webData();
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setState(record.get("Province/State"));
            locationStat.setLatestCases(Integer.parseInt(record.get(record.size() -1)));

            tempData.add(locationStat);
//            String id = record.get("Province/State");
//            System.out.println(locationStat);
        }
        this.allStats = tempData;
        //System.out.println(rspns.body());
//        return this.allStats; // return type: List<webdata?
    }

//    @PostConstruct
//    public void fetchData() throws IOException, InterruptedException {
//        List<webData> tempData = new ArrayList<>();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest rqst = HttpRequest.newBuilder()
//                .uri(URI.create(csv_file_url)).build();
//
//        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
//
//        StringReader in = new StringReader(rspns.body());
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        for (CSVRecord record : records) {
//            /*locationStats locationStat = new locationStats() */
//            webData locationStat = new webData();
//            locationStat.setCountry(record.get("Country/Region"));
//            locationStat.setState(record.get("Province/State"));
//            locationStat.setLatestCases(Integer.parseInt(record.get(record.size() -1)));
//
//            tempData.add(locationStat);
////            String id = record.get("Province/State");
////            System.out.println(locationStat);
//        }
//        this.allStats = tempData;
//        //System.out.println(rspns.body());
//    }


    ////
    ////** Getters
    ////
//    @Bean ("csvData")
//    @Qualifier ("csvData")
//    @Bean ("getCsvDataService")
    public List<webData> getAllStats() {
        return allStats;
    }

//    @Bean(name = "csvData")
//    public getCsvDataService csvDataService(){
//        return new getCsvDataService();
//    }

}