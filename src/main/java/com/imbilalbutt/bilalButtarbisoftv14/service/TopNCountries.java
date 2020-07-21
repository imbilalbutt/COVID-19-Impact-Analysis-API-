package com.imbilalbutt.bilalButtarbisoftv14.service;


import com.imbilalbutt.bilalButtarbisoftv14.models.webData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service//("cde")
public class TopNCountries {
    private int n;

    @Autowired
    getSortedData sortedData;

    private List<webData> topNConsList;
    private List<webData> singleCountryData;

   // @Qualifier(//   // //"abc")
//    @Autowired
//    PatricularCountryDataService countryDataService;

    public TopNCountries(){
        this.n = 10;
    }

    public TopNCountries(int n) {
        this.n = n;
    }

    @PostConstruct
    public void topNcountries(){
        List<webData> tempSortedListHolder = sortedData.getAllSortedStats(); // have all data
        List<webData> singleCountryAndStateHolder = new ArrayList<>();
        for (int i = 0; i < n; i++){
            webData wbd = tempSortedListHolder.get(i); // it contains top most country
//            System.out.println(wbd.getCountry()+ " " + wbd.getState() + " " + wbd.getLatestCases());
//            tempCList.addAll(countryDataService.getCountryDataWithParameter(wbd.getCountry()));
            singleCountryAndStateHolder.addAll(this.ExtractSingleCountryDetails(wbd.getCountry()));
            // 10 dafa paticularCountry call krna hai
        }
        this.topNConsList = singleCountryAndStateHolder;
    }

        public List<webData> ExtractSingleCountryDetails(String country) {
        List<webData> tempCountryData = new ArrayList<>(); //Holding all the states against single country
        List<webData> allCountryList = sortedData.getAllSortedStats();

        for (webData singleWebDataElement : allCountryList) {
            if (singleWebDataElement.getCountry().equals(country)) {
                //System.out.println(singleWebDataElement.getCountry() + " " + singleWebDataElement.getState() + " " + singleWebDataElement.getLatestCases());
                tempCountryData.add(singleWebDataElement);
            }
        }
        this.singleCountryData = tempCountryData;
        return this.singleCountryData;
    }

////    @Bean("akas")
//    @PostConstruct
//    public List<webData> topNcountries(int n){
//        List<webData> tempList = sortedData.getAllSortedStats(); // have all data
//        List<webData> tempCList = new ArrayList<>();
//        for (int i = 0; i < n; i++){
//            webData wbd = tempList.get(i); // it contains top most country
//            tempCList.addAll(countryDataService.getCountryDataWithParameter(wbd.getCountry()));
//            // 10 dafa paticularCountry call krna hai
//        }
//        this.topNConsList = tempCList;
//        return this.topNConsList;
//    }

    public List<webData> getTopNConsListWithParameter(int n) {
//        topNcountries(n);
        topNcountries();
        return this.topNConsList;
    }

//    @Bean ("topNCountriesBean")
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public TopNCountries topNCountries(){
//        return new TopNCountries();
//    }
}
