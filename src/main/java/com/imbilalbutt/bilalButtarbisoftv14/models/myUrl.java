package com.imbilalbutt.bilalButtarbisoftv14.models;

import org.springframework.stereotype.Service;

@Service ("my_url_bean")
public class myUrl {

    private String csv_file_url;

    public myUrl() {
        this.csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    }

    public myUrl(String url) {
        this.csv_file_url = url;
    }

    public String getCsv_file_url() {
        return this.csv_file_url;
    }

    public String setCsv_file_url(String url) {
        return this.csv_file_url = url;
    }

    @Override
    public String toString() {
        return "myUrl{" +
                "csv_file_url='" + csv_file_url + '\'' +
                '}';
    }

//    @Bean("my_url_bean")
//    @Bean
//    public myUrl myUrlBean() {
//        return new myUrl("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv");
//    }
}
