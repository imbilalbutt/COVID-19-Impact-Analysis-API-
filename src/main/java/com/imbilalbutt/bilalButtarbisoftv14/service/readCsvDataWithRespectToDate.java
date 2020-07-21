package com.imbilalbutt.bilalButtarbisoftv14.service;


import com.imbilalbutt.bilalButtarbisoftv14.models.NewWebData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.URI;
import java.util.*;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//              Version 2.3
// It is exact same v2.3, but only removed comments. Made it pretty.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Service
public class readCsvDataWithRespectToDate {

    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();

    private String dateSince= "3/5/20";

    public void setDate(String dateSince) {
//        this.allStats = new ArrayList<>();
        this.dateSince = dateSince;
    }

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest rqst = HttpRequest.newBuilder()
                .uri(URI.create(csv_file_url)).build();

        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());

        StringReader in = new StringReader(rspns.body());

        //CSVParser csvParser = new CSVParser((CSVRecord) rspns.body() , CSVFormat.DEFAULT);
        //////////////////////////////////////////////////////////////////////
        // Usage here: It will take in the records and will add to list.
        // I am using it to get the headers of csv.
        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
        CSVRecord header = parser.getRecords().get(0);

        ArrayList<Object> list = new ArrayList<>(header.size());
        for (int i = 0; i < header.size(); i++) {
            list.add(header.get(i));
        }
        //System.out.println("header = " + list);
        //System.out.println("Index of header[dataSince] = " + list.indexOf(this.dateSince));
        //// Idher tak mje mil jaienge saray header, parser.getRecords().get(any_index)
        //// Is se mje us puri row ki cheezien mil jaiengi.
        //// Asan ilfaz mein, ye mje rows return ker k deta hai
        //////////////////////////////////////////////////////////////////////

        int beginDateIndexNumber = (int) (list.indexOf(this.dateSince) - 1);
        //System.out.println("beginDate = " + beginDateIndexNumber);
        String endDate = header.get(header.size() - 1);
        int endDateIndexNumber = list.indexOf(endDate);

        int countryNumber = 1;
        while (countryNumber < (endDateIndexNumber-1))
        {
        ////////////////////////////////////////////////////////////////////////
        ///***/ What Doing? It is showing each entry of CSVRecord" ////
        ////Record =  CSVRecord [comment=null, mapping={Province/State=0, Country/Region=1, Lat=2, Long=3, 1/22/20=4, 1/23/20=5, 1/24/20=6, 1/25/20=7, 1/26/20=8, 1/27/20=9, 1/28/20=10, 1/29/20=11, 1/30/20=12, 1/31/20=13, 2/1/20=14, 2/2/20=15, 2/3/20=16, 2/4/20=17, 2/5/20=18, 2/6/20=19, 2/7/20=20, 2/8/20=21, 2/9/20=22, 2/10/20=23, 2/11/20=24, 2/12/20=25, 2/13/20=26, 2/14/20=27, 2/15/20=28, 2/16/20=29, 2/17/20=30, 2/18/20=31, 2/19/20=32, 2/20/20=33, 2/21/20=34, 2/22/20=35, 2/23/20=36, 2/24/20=37, 2/25/20=38, 2/26/20=39, 2/27/20=40, 2/28/20=41, 2/29/20=42, 3/1/20=43, 3/2/20=44, 3/3/20=45, 3/4/20=46, 3/5/20=47, 3/6/20=48, 3/7/20=49, 3/8/20=50, 3/9/20=51, 3/10/20=52, 3/11/20=53, 3/12/20=54, 3/13/20=55, 3/14/20=56, 3/15/20=57, 3/16/20=58, 3/17/20=59, 3/18/20=60, 3/19/20=61, 3/20/20=62, 3/21/20=63, 3/22/20=64, 3/23/20=65, 3/24/20=66, 3/25/20=67, 3/26/20=68, 3/27/20=69, 3/28/20=70, 3/29/20=71, 3/30/20=72, 3/31/20=73, 4/1/20=74, 4/2/20=75, 4/3/20=76, 4/4/20=77, 4/5/20=78, 4/6/20=79, 4/7/20=80, 4/8/20=81, 4/9/20=82, 4/10/20=83, 4/11/20=84, 4/12/20=85, 4/13/20=86, 4/14/20=87, 4/15/20=88, 4/16/20=89, 4/17/20=90, 4/18/20=91, 4/19/20=92, 4/20/20=93, 4/21/20=94, 4/22/20=95, 4/23/20=96, 4/24/20=97, 4/25/20=98, 4/26/20=99, 4/27/20=100, 4/28/20=101, 4/29/20=102, 4/30/20=103, 5/1/20=104, 5/2/20=105, 5/3/20=106, 5/4/20=107, 5/5/20=108, 5/6/20=109, 5/7/20=110, 5/8/20=111, 5/9/20=112, 5/10/20=113, 5/11/20=114, 5/12/20=115, 5/13/20=116, 5/14/20=117, 5/15/20=118, 5/16/20=119, 5/17/20=120, 5/18/20=121, 5/19/20=122, 5/20/20=123, 5/21/20=124, 5/22/20=125, 5/23/20=126, 5/24/20=127, 5/25/20=128, 5/26/20=129, 5/27/20=130, 5/28/20=131, 5/29/20=132, 5/30/20=133, 5/31/20=134, 6/1/20=135, 6/2/20=136, 6/3/20=137, 6/4/20=138, 6/5/20=139, 6/6/20=140}, recordNumber=1, values=[, Afghanistan, 33.0, 65.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 5, 7, 7, 7, 11, 16, 21, 22, 22, 22, 24, 24, 40, 40, 74, 84, 94, 110, 110, 120, 170, 174, 237, 273, 281, 299, 349, 367, 423, 444, 484, 521, 555, 607, 665, 714, 784, 840, 906, 933, 996, 1026, 1092, 1176, 1279, 1351, 1463, 1531, 1703, 1828, 1939, 2171, 2335, 2469, 2704, 2894, 3224, 3392, 3563, 3778, 4033, 4402, 4687, 4963, 5226, 5639, 6053, 6402, 6664, 7072, 7653, 8145, 8676, 9216, 9998, 10582, 11173, 11831, 12456, 13036, 13659, 14525, 15205, 15750, 16509, 17267, 18054, 18969, 19551]]
        //System.out.println("Record = " + " " + (record)); // print addresses of each record
        //System.out.println("Record Iter = " + " " + (record.iterator())); // print addresses of each record

        ///***/ What Doing? It is showing all cases on just "tempDate" ////
        //           String svc = record.get(tempDate);//Integer.parseInt(record.get(tempDate));
        //            List<Integer> dvc = new ArrayList<>();
        //            dvc.add(svc);
        //System.out.println("svc = " + " " + svc);//Integer.parseInt(record.get(tempDate)));
        ////////////////////////////////////////////////////////////////////////


            CSVParser parser2 = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
            CSVRecord rcrd = parser2.getRecords().get(countryNumber);

            List<String> oneRowOfCsv = new ArrayList<>(rcrd.size());

            for (int i = 0; i < (rcrd.size()-1); i++) {
                oneRowOfCsv.add(rcrd.get(i));
            }
            // Us Specified date se le ker aj tak ka data lelo.

            List<String> lst = oneRowOfCsv.subList(beginDateIndexNumber, (oneRowOfCsv.size()));

            this.allStats.add(new NewWebData(oneRowOfCsv.get(1), oneRowOfCsv.get(0), lst));
            countryNumber++;
        }
    }

    @Bean
    public List<NewWebData> getAllStats() {
        return allStats;
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
////              Version 2.3
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
//@Service
//public class readCsvDataWithRespectToDate {
//
//    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//
//    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();
//
//    private String dateSince = "3/5/20";
//
////    private  Dictionary<String , List<NewWebData> > dicti = new Hashtable<>();
//
//
////    private Map<String , List<NewWebData> > dicti = new HashMap<String, List<NewWebData>>();
//
////        public readCsvDataWithRespectToDate(){
////        this.allStats = new ArrayList<>();
////        dateSince = " ";
////    }
//
////    public readCsvDataWithRespectToDate(String dateSince){
////        this.allStats = new ArrayList<>();
////        this.dateSince = dateSince;
////    }
//
////    public readCsvDataWithRespectToDate(List<NewWebData> allStats, String dateSince){
////        this.allStats = allStats;
////        this.dateSince = dateSince;
////    }
//
//    public void setDate(String dateSince) {
//        this.allStats = new ArrayList<>();
//        this.dateSince = dateSince;
//    }
//
//
//
//    @PostConstruct
//    public void fetchData() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest rqst = HttpRequest.newBuilder()
//                .uri(URI.create(csv_file_url)).build();
//
//        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
//
//        StringReader in = new StringReader(rspns.body());
//
////
//
////        //CSVParser csvParser = new CSVParser((CSVRecord) rspns.body() , CSVFormat.DEFAULT);
//        //////////////////////////////////////////////////////////////////////
//        // Usage here: It will take in the records and will add to list.
//        // I am using it to get the headers of csv.
//        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
//        CSVRecord header = parser.getRecords().get(0);
//
//        ArrayList<Object> list = new ArrayList<>(header.size());
////for (int i = 0; i < recording.size(); i++) { // formally this line
//        for (int i = 0; i < header.size(); i++) {
//            list.add(header.get(i));
//        }
////        System.out.println("header = " + list);
////        System.out.println("Index of header[dataSince] = " + list.indexOf(this.dateSince));
//        //// Idher tak mje mil jaienge saray header, parser.getRecords().get(any_index)
//        //// Is se mje us puri row ki cheezien mil jaiengi.
//        //// Asan ilfaz mein, ye mje rows return ker k deta hai
//        //////////////////////////////////////////////////////////////////////
//
//        String beginFate = this.dateSince;
//        int beginDateIndexNumber = (int) (list.indexOf(this.dateSince) - 1);
////        System.out.println("beginDate = " + beginDateIndexNumber);
//        String endDate = header.get(header.size() - 1);
//        int endDateIndexNumber = list.indexOf(endDate);
////        System.out.println("");
////        System.out.println("endDate = " + endDate);
////        System.out.println("endDateIndexNumber = " + endDateIndexNumber);
////        int columnNumber = beginDateIndexNumber;
//
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        //while (date < (endDateIndexNumber-1)) {
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//
//        int countryNumber = 1;
//        while (countryNumber < (endDateIndexNumber-1))
//        //for (CSVRecord record : records)
//        {
//
//            ////////////////////////////////////////////////////////////////////////
/////***/ What Doing? It is showing each entry of CSVRecord" ////
//////Record =  CSVRecord [comment=null, mapping={Province/State=0, Country/Region=1, Lat=2, Long=3, 1/22/20=4, 1/23/20=5, 1/24/20=6, 1/25/20=7, 1/26/20=8, 1/27/20=9, 1/28/20=10, 1/29/20=11, 1/30/20=12, 1/31/20=13, 2/1/20=14, 2/2/20=15, 2/3/20=16, 2/4/20=17, 2/5/20=18, 2/6/20=19, 2/7/20=20, 2/8/20=21, 2/9/20=22, 2/10/20=23, 2/11/20=24, 2/12/20=25, 2/13/20=26, 2/14/20=27, 2/15/20=28, 2/16/20=29, 2/17/20=30, 2/18/20=31, 2/19/20=32, 2/20/20=33, 2/21/20=34, 2/22/20=35, 2/23/20=36, 2/24/20=37, 2/25/20=38, 2/26/20=39, 2/27/20=40, 2/28/20=41, 2/29/20=42, 3/1/20=43, 3/2/20=44, 3/3/20=45, 3/4/20=46, 3/5/20=47, 3/6/20=48, 3/7/20=49, 3/8/20=50, 3/9/20=51, 3/10/20=52, 3/11/20=53, 3/12/20=54, 3/13/20=55, 3/14/20=56, 3/15/20=57, 3/16/20=58, 3/17/20=59, 3/18/20=60, 3/19/20=61, 3/20/20=62, 3/21/20=63, 3/22/20=64, 3/23/20=65, 3/24/20=66, 3/25/20=67, 3/26/20=68, 3/27/20=69, 3/28/20=70, 3/29/20=71, 3/30/20=72, 3/31/20=73, 4/1/20=74, 4/2/20=75, 4/3/20=76, 4/4/20=77, 4/5/20=78, 4/6/20=79, 4/7/20=80, 4/8/20=81, 4/9/20=82, 4/10/20=83, 4/11/20=84, 4/12/20=85, 4/13/20=86, 4/14/20=87, 4/15/20=88, 4/16/20=89, 4/17/20=90, 4/18/20=91, 4/19/20=92, 4/20/20=93, 4/21/20=94, 4/22/20=95, 4/23/20=96, 4/24/20=97, 4/25/20=98, 4/26/20=99, 4/27/20=100, 4/28/20=101, 4/29/20=102, 4/30/20=103, 5/1/20=104, 5/2/20=105, 5/3/20=106, 5/4/20=107, 5/5/20=108, 5/6/20=109, 5/7/20=110, 5/8/20=111, 5/9/20=112, 5/10/20=113, 5/11/20=114, 5/12/20=115, 5/13/20=116, 5/14/20=117, 5/15/20=118, 5/16/20=119, 5/17/20=120, 5/18/20=121, 5/19/20=122, 5/20/20=123, 5/21/20=124, 5/22/20=125, 5/23/20=126, 5/24/20=127, 5/25/20=128, 5/26/20=129, 5/27/20=130, 5/28/20=131, 5/29/20=132, 5/30/20=133, 5/31/20=134, 6/1/20=135, 6/2/20=136, 6/3/20=137, 6/4/20=138, 6/5/20=139, 6/6/20=140}, recordNumber=1, values=[, Afghanistan, 33.0, 65.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 5, 7, 7, 7, 11, 16, 21, 22, 22, 22, 24, 24, 40, 40, 74, 84, 94, 110, 110, 120, 170, 174, 237, 273, 281, 299, 349, 367, 423, 444, 484, 521, 555, 607, 665, 714, 784, 840, 906, 933, 996, 1026, 1092, 1176, 1279, 1351, 1463, 1531, 1703, 1828, 1939, 2171, 2335, 2469, 2704, 2894, 3224, 3392, 3563, 3778, 4033, 4402, 4687, 4963, 5226, 5639, 6053, 6402, 6664, 7072, 7653, 8145, 8676, 9216, 9998, 10582, 11173, 11831, 12456, 13036, 13659, 14525, 15205, 15750, 16509, 17267, 18054, 18969, 19551]]
////System.out.println("Record = " + " " + (record)); // print addresses of each record
////System.out.println("Record Iter = " + " " + (record.iterator())); // print addresses of each record
//
/////***/ What Doing? It is showing all cases on just "tempDate" ////
////           String svc = record.get(tempDate);//Integer.parseInt(record.get(tempDate));
////            List<Integer> dvc = new ArrayList<>();
////            dvc.add(svc);
////System.out.println("svc = " + " " + svc);//Integer.parseInt(record.get(tempDate)));
//
//
////            for(Iterator<String> it = record.iterator(); !it.hasNext();record.iterator().next()) {
////                System.out.println("it = " + it);
////                it.hasNext();
////            }
//
//            //////////////////////////////////////////////////////////////////////
//            // this is printing address
//
////            int date = beginDateIndexNumber;
//            /*locationStats locationStat = new locationStats() */
////                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//
////                NewWebData locationStat = new NewWebData();
////                locationStat.setCountry(record.get("Country/Region"));
////                locationStat.setState(record.get("Province/State"));
//
////            int x = Integer.parseInt(record.get(tempDate)) ;///-- This line is the problem
//            // Solution might be to change int x to Integer x
////            System.out.println("x = " + " " + x );
////            int maximu = Integer.parseInt(record.get(record.size() -1));
////            System.out.println("max " + " " + maximu);
//
////            Integer x = Integer.parseInt(record.get(date)); // ise b comment out kia hai
//
//            //////////////////////////////////////////////////////////////////////
//            // This inner once again revisit records each time.
//            // So to add all cases in locationStat (a temp list)
////                Iterable<CSVRecord> recordsCopy = new ArrayList<>();
////                NewWebData locationStat = new NewWebData();
////                ArrayList<String> CaseOfAllCountries = new ArrayList<>();
////
////                Iterable<CSVRecord> recordx = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
////
////                for (CSVRecord record : recordx) {
//////                    System.out.println("jkjkjkjkjkjkjkjkjkjkjkjkjkjkjk" + u);
//////                    u++;
////
////                    System.out.println("parser.getRecords().get(date) " + parser.getRecords().get(beginDateIndexNumber));
////                    locationStat.setCountry(record.get("Country/Region"));
////                    locationStat.setState(record.get("Province/State"));
////                }
//
//
//
//
////                CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
////                CSVRecord header = parser.getRecords().get(0);
//
////                System.out.println("1");
//
//
//
//            CSVParser parser2 = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
//            CSVRecord rcrd = parser2.getRecords().get(countryNumber);
////                System.out.println("rcrd  = " + rcrd.size());
//
////                System.out.println("2");
//            List<String> oneRowOfCsv = new ArrayList<>(rcrd.size());
////                System.out.println("3");
////for (int i = 0; i < recording.size(); i++) { // formally this line
//            for (int i = 0; i < (rcrd.size()-1); i++) {
//                oneRowOfCsv.add(rcrd.get(i));
//            }
//
//
//
//
//            // Us Specified date se le ker aj tak ka data lelo.
//            List<String> lst = oneRowOfCsv.subList(beginDateIndexNumber, (oneRowOfCsv.size()));
////                System.out.println("lst  = " + lst);
//
////                List<String> parsedCountryDataFromADate = new ArrayList<>(lst);
////                System.out.println("tab  = " + parsedCountryDataFromADate);
////                System.out.println("tab.size  = " + parsedCountryDataFromADate.size());
//
//            //entry.addAll(new ArrayList(oneRowOfCsv.subList(beginDateIndexNumber, (rcrd.size()-1))));
////                entry.addAll(oneRowOfCsv);
////                System.out.println("4");
////                System.out.println("OneRowOfCsv  = " + oneRowOfCsv);
////                System.out.println("entry  = " + entry.size());
////                System.out.println("5");
//
//
////                NewWebData tempWBD = new NewWebData();
////                tempWBD.setCountry(oneRowOfCsv.get(1));
////                tempWBD.setState(oneRowOfCsv.get(0));
////                tempWBD.setCasesInASingleDay(lst);
//
//
//
//            this.allStats.add(new NewWebData(oneRowOfCsv.get(1), oneRowOfCsv.get(0), lst));
//
//
////                System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
//
//            //locationStat.setCasesInASingleDay(CaseOfAllCountries);
//            //this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
////                System.out.println("locationStat = " + locationStat);
////                this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
//
//            //System.out.println("locationStat = " + " " + locationStat);
//            // Bhai ye tou return ker raha ha k sab countries k jitne cases the,
//            // dateSince wali date ko, yani dataSince ki date ko sab counties k
//            // cases bta raha hai.
//            // Asan lafzon mein yeh mje column de deta us puri date ka.
//            //////////////////////////////////////////////////////////////////////
//
//            //CaseOfAllCountries = locationStat.getCasesInASingleDay();
//            //System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
////            String id = record.get("Province/State");
////            System.out.println(locationStat);
//
////                System.out.println("this.addStats = " + this.allStats);
//
//
////                beginDateIndexNumber++;
////                System.out.println("beginFate = " + beginFate);
////                System.out.println("beginDateIndexNumber = "+ beginDateIndexNumber);
////                beginFate = header.get(beginDateIndexNumber);
////                System.out.println("header.get(beginDateIndexNumber = " + beginFate);
////                columnNumber++;
////                this.allStats.add(tempWBD);
////                System.out.println("this.allStat = " + this.allStats);
//            countryNumber++;
//        }
//        //}
//
////        System.out.println("this.allStat = " + this.allStats.get(0));
////        System.out.println("this.allStat = " + this.allStats.get(1));
////        System.out.println("this.allStat = " + this.allStats.get(2));
//
////        this.allStats = tempData;
//    }
//
//    @Bean
//    public List<NewWebData> getAllStats() {
//        return allStats;
//    }
//}




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
////              Version 2.2
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
////@Service
////public class readCsvDataWithRespectToDate {
////
////    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
////
////    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();
////
////    private String dateSince = "3/5/20";
////
//////        public readCsvDataWithRespectToDate(){
//////        this.allStats = new ArrayList<>();
//////        dateSince = " ";
//////    }
////
//////    public readCsvDataWithRespectToDate(String dateSince){
//////        this.allStats = new ArrayList<>();
//////        this.dateSince = dateSince;
//////    }
////
//////    public readCsvDataWithRespectToDate(List<NewWebData> allStats, String dateSince){
//////        this.allStats = allStats;
//////        this.dateSince = dateSince;
//////    }
////
////    public void setDate(String dateSince) {
////        this.allStats = new ArrayList<>();
////        this.dateSince = dateSince;
////    }
////
//////    private List<String[]> getRecords(String content, CSVFormat format) throws IOException {
//////        List<String[]> records = new ArrayList<>();
//////        CSVParser parser = CSVParser.parse(content, format);
//////        for (CSVRecord record : parser.getRecords()) {
//////            String[] line = new String[record.size()];
//////            for (int i = 0; i < line.length; i++) {
//////                line[i] = record.get(i);
//////            }
//////            records.add(line);
//////        }
//////        return records;
//////    }
////
////
////    @PostConstruct
////    public void fetchData() throws IOException, InterruptedException {
//////        String tempDateHolder = dateSince;
//////        List<NewWebData> tempData = new ArrayList<>();
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest rqst = HttpRequest.newBuilder()
//                .uri(URI.create(csv_file_url)).build();
//
//        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
//
//        StringReader in = new StringReader(rspns.body());
//
////
//
////        //CSVParser csvParser = new CSVParser((CSVRecord) rspns.body() , CSVFormat.DEFAULT);
//        //////////////////////////////////////////////////////////////////////
//        // Usage here: It will take in the records and will add to list.
//        // I am using it to get the headers of csv.
//        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
//        CSVRecord header = parser.getRecords().get(0);
//
////        ArrayList<Object> list = new ArrayList<>(recording.size());
//        ArrayList<Object> list = new ArrayList<>(header.size());
////for (int i = 0; i < recording.size(); i++) { // formally this line
//        for (int i = 0; i < header.size(); i++) {
//            list.add(header.get(i));
//        }
//        System.out.println("header = " + list);
//        System.out.println("Index of header[dataSince] = " + list.indexOf(this.dateSince));
//        //// Idher tak mje mil jaienge saray header, parser.getRecords().get(any_index)
//        //// Is se mje us puri row ki cheezien mil jaiengi.
//        //// Asan ilfaz mein, ye mje rows return ker k deta hai
//        //////////////////////////////////////////////////////////////////////
//
//        String beginFate = this.dateSince;
//        int beginDateIndexNumber = list.indexOf(this.dateSince);
//        System.out.println("beginDate = " + beginDateIndexNumber);
//        String endDate = header.get(header.size() - 1);
//        int endDateIndexNumber = list.indexOf(endDate);
////        System.out.println("");
//        System.out.println("endDate = " + endDate);
//        System.out.println("endDateIndexNumber = " + endDateIndexNumber);
////        int date = beginDateIndexNumber;
//
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        //while (date < (endDateIndexNumber-1)) {
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        while (beginDateIndexNumber < (endDateIndexNumber-1))
//            //for (CSVRecord record : records)
//            {
//
//                ////////////////////////////////////////////////////////////////////////
/////***/ What Doing? It is showing each entry of CSVRecord" ////
//////Record =  CSVRecord [comment=null, mapping={Province/State=0, Country/Region=1, Lat=2, Long=3, 1/22/20=4, 1/23/20=5, 1/24/20=6, 1/25/20=7, 1/26/20=8, 1/27/20=9, 1/28/20=10, 1/29/20=11, 1/30/20=12, 1/31/20=13, 2/1/20=14, 2/2/20=15, 2/3/20=16, 2/4/20=17, 2/5/20=18, 2/6/20=19, 2/7/20=20, 2/8/20=21, 2/9/20=22, 2/10/20=23, 2/11/20=24, 2/12/20=25, 2/13/20=26, 2/14/20=27, 2/15/20=28, 2/16/20=29, 2/17/20=30, 2/18/20=31, 2/19/20=32, 2/20/20=33, 2/21/20=34, 2/22/20=35, 2/23/20=36, 2/24/20=37, 2/25/20=38, 2/26/20=39, 2/27/20=40, 2/28/20=41, 2/29/20=42, 3/1/20=43, 3/2/20=44, 3/3/20=45, 3/4/20=46, 3/5/20=47, 3/6/20=48, 3/7/20=49, 3/8/20=50, 3/9/20=51, 3/10/20=52, 3/11/20=53, 3/12/20=54, 3/13/20=55, 3/14/20=56, 3/15/20=57, 3/16/20=58, 3/17/20=59, 3/18/20=60, 3/19/20=61, 3/20/20=62, 3/21/20=63, 3/22/20=64, 3/23/20=65, 3/24/20=66, 3/25/20=67, 3/26/20=68, 3/27/20=69, 3/28/20=70, 3/29/20=71, 3/30/20=72, 3/31/20=73, 4/1/20=74, 4/2/20=75, 4/3/20=76, 4/4/20=77, 4/5/20=78, 4/6/20=79, 4/7/20=80, 4/8/20=81, 4/9/20=82, 4/10/20=83, 4/11/20=84, 4/12/20=85, 4/13/20=86, 4/14/20=87, 4/15/20=88, 4/16/20=89, 4/17/20=90, 4/18/20=91, 4/19/20=92, 4/20/20=93, 4/21/20=94, 4/22/20=95, 4/23/20=96, 4/24/20=97, 4/25/20=98, 4/26/20=99, 4/27/20=100, 4/28/20=101, 4/29/20=102, 4/30/20=103, 5/1/20=104, 5/2/20=105, 5/3/20=106, 5/4/20=107, 5/5/20=108, 5/6/20=109, 5/7/20=110, 5/8/20=111, 5/9/20=112, 5/10/20=113, 5/11/20=114, 5/12/20=115, 5/13/20=116, 5/14/20=117, 5/15/20=118, 5/16/20=119, 5/17/20=120, 5/18/20=121, 5/19/20=122, 5/20/20=123, 5/21/20=124, 5/22/20=125, 5/23/20=126, 5/24/20=127, 5/25/20=128, 5/26/20=129, 5/27/20=130, 5/28/20=131, 5/29/20=132, 5/30/20=133, 5/31/20=134, 6/1/20=135, 6/2/20=136, 6/3/20=137, 6/4/20=138, 6/5/20=139, 6/6/20=140}, recordNumber=1, values=[, Afghanistan, 33.0, 65.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 5, 7, 7, 7, 11, 16, 21, 22, 22, 22, 24, 24, 40, 40, 74, 84, 94, 110, 110, 120, 170, 174, 237, 273, 281, 299, 349, 367, 423, 444, 484, 521, 555, 607, 665, 714, 784, 840, 906, 933, 996, 1026, 1092, 1176, 1279, 1351, 1463, 1531, 1703, 1828, 1939, 2171, 2335, 2469, 2704, 2894, 3224, 3392, 3563, 3778, 4033, 4402, 4687, 4963, 5226, 5639, 6053, 6402, 6664, 7072, 7653, 8145, 8676, 9216, 9998, 10582, 11173, 11831, 12456, 13036, 13659, 14525, 15205, 15750, 16509, 17267, 18054, 18969, 19551]]
////System.out.println("Record = " + " " + (record)); // print addresses of each record
////System.out.println("Record Iter = " + " " + (record.iterator())); // print addresses of each record
//
/////***/ What Doing? It is showing all cases on just "tempDate" ////
////           String svc = record.get(tempDate);//Integer.parseInt(record.get(tempDate));
////            List<Integer> dvc = new ArrayList<>();
////            dvc.add(svc);
////System.out.println("svc = " + " " + svc);//Integer.parseInt(record.get(tempDate)));
//
//
////            for(Iterator<String> it = record.iterator(); !it.hasNext();record.iterator().next()) {
////                System.out.println("it = " + it);
////                it.hasNext();
////            }
//
//                //////////////////////////////////////////////////////////////////////
//                // this is printing address
//
////            int date = beginDateIndexNumber;
//                /*locationStats locationStat = new locationStats() */
////                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//
////                NewWebData locationStat = new NewWebData();
////                locationStat.setCountry(record.get("Country/Region"));
////                locationStat.setState(record.get("Province/State"));
//
////            int x = Integer.parseInt(record.get(tempDate)) ;///-- This line is the problem
//                // Solution might be to change int x to Integer x
////            System.out.println("x = " + " " + x );
////            int maximu = Integer.parseInt(record.get(record.size() -1));
////            System.out.println("max " + " " + maximu);
//
////            Integer x = Integer.parseInt(record.get(date)); // ise b comment out kia hai
//
//                //////////////////////////////////////////////////////////////////////
//                // This inner once again revisit records each time.
//                // So to add all cases in locationStat (a temp list)
////                Iterable<CSVRecord> recordsCopy = new ArrayList<>();
//                NewWebData locationStat = new NewWebData();
//                ArrayList<String> CaseOfAllCountries = new ArrayList<>();
//
//                Iterable<CSVRecord> recordx = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//
//
//                int forLoopKitniBarChalaHai = 1;
//                NewWebData tempWBD = new NewWebData();
//                for (CSVRecord record : recordx) {
////                    System.out.println("jkjkjkjkjkjkjkjkjkjkjkjkjkjkjk" + u);
////                    u++;
//
//                    System.out.println("parser.getRecords().get(date) " + parser.getRecords().get(beginDateIndexNumber));
//                    locationStat.setCountry(record.get("Country/Region"));
//                    locationStat.setState(record.get("Province/State"));
//
////                    CaseOfAllCountries.add((record.get(date)));
//                    // I am commenting it out, because in this v2.1, CaseInASingleDay is of
////                    locationStat.addCase(Integer.parseInt(record.get(beginDateIndexNumber)));
//                    // And in new version 2.2, I will change it to List<String>
////
////                    locationStat.setCasesInASingleDay(record.get(beginFate));
////                    locationStat.addCase(Integer.parseInt(record.get(beginDateIndexNumber))); //tempDateHolder
//                }
//
////                forLoopKitniBarChalaHai++;
////                System.out.println("forLoopKitniBarChalaHai = " + forLoopKitniBarChalaHai);
//
//
////                System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
//
//                beginDateIndexNumber++;
//                locationStat.setCasesInASingleDay(CaseOfAllCountries);
//                this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
////                System.out.println("locationStat = " + locationStat);
////                this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
//
//                //System.out.println("locationStat = " + " " + locationStat);
//                // Bhai ye tou return ker raha ha k sab countries k jitne cases the,
//                // dateSince wali date ko, yani dataSince ki date ko sab counties k
//                // cases bta raha hai.
//                // Asan lafzon mein yeh mje column de deta us puri date ka.
//                //////////////////////////////////////////////////////////////////////
//
//                //CaseOfAllCountries = locationStat.getCasesInASingleDay();
//                //System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
////            String id = record.get("Province/State");
////            System.out.println(locationStat);
//
////                System.out.println("this.addStats = " + this.allStats);
//
//
////                beginDateIndexNumber++;
////                System.out.println("beginFate = " + beginFate);
////                System.out.println("beginDateIndexNumber = "+ beginDateIndexNumber);
////                beginFate = header.get(beginDateIndexNumber);
////                System.out.println("header.get(beginDateIndexNumber = " + beginFate);
//                beginDateIndexNumber++;
////                date++;
////            break;
//            }
////        System.out.println("this.allStat = " + this.allStats);
//            //date++;
//        //}
//
////        this.allStats = tempData;
//    }
//
////    public List<NewWebData> setWetAllStatsLise(String country, String state, List<Integer> caseOfAllCountries)//, List<NewWebData> wbd){
////    {
////        // allStats k ander Integer<list> ko update kerna hai
////        // yani
////        // allStats.CasesInASingleDay = caseOfAllCountries ho jaye ksi tarah se
////        this.allStats.add(new NewWebData(country, state, caseOfAllCountries));
////
////        return this.allStats;
////    }
//
//    @Bean
//    public List<NewWebData> getAllStats() {
//        return allStats;
//    }
//}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//              Version 2.1
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//@Service
//public class readCsvDataWithRespectToDate {
//
//    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//
//    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();
//
//    private String dateSince = "3/5/20";
//
////        public readCsvDataWithRespectToDate(){
////        this.allStats = new ArrayList<>();
////        dateSince = " ";
////    }
//
////    public readCsvDataWithRespectToDate(String dateSince){
////        this.allStats = new ArrayList<>();
////        this.dateSince = dateSince;
////    }
//
////    public readCsvDataWithRespectToDate(List<NewWebData> allStats, String dateSince){
////        this.allStats = allStats;
////        this.dateSince = dateSince;
////    }
//
//    public void setDate(String dateSince) {
//        this.allStats = new ArrayList<>();
//        this.dateSince = dateSince;
//    }
//
////    private List<String[]> getRecords(String content, CSVFormat format) throws IOException {
////        List<String[]> records = new ArrayList<>();
////        CSVParser parser = CSVParser.parse(content, format);
////        for (CSVRecord record : parser.getRecords()) {
////            String[] line = new String[record.size()];
////            for (int i = 0; i < line.length; i++) {
////                line[i] = record.get(i);
////            }
////            records.add(line);
////        }
////        return records;
////    }
//
//
//    @PostConstruct
//    public void fetchData() throws IOException, InterruptedException {
////        String tempDateHolder = dateSince;
////        List<NewWebData> tempData = new ArrayList<>();
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest rqst = HttpRequest.newBuilder()
//                .uri(URI.create(csv_file_url)).build();
//
//        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
//
//        StringReader in = new StringReader(rspns.body());
//
////
//
////        //CSVParser csvParser = new CSVParser((CSVRecord) rspns.body() , CSVFormat.DEFAULT);
//        //////////////////////////////////////////////////////////////////////
//        // Usage here: It will take in the records and will add to list.
//        // I am using it to get the headers of csv.
//        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
//        CSVRecord header = parser.getRecords().get(0);
//
////        ArrayList<Object> list = new ArrayList<>(recording.size());
//        ArrayList<Object> list = new ArrayList<>(header.size());
////for (int i = 0; i < recording.size(); i++) { // formally this line
//        for (int i = 0; i < header.size(); i++) {
//            list.add(header.get(i));
//        }
//        System.out.println("header = " + list);
//        System.out.println("Index of header[dataSince] = " + list.indexOf(this.dateSince));
//        //// Idher tak mje mil jaienge saray header, parser.getRecords().get(any_index)
//        //// Is se mje us puri row ki cheezien mil jaiengi.
//        //// Asan ilfaz mein, ye mje puri aik row return ker k deta hai
//        //////////////////////////////////////////////////////////////////////
//
//        String beginFate = this.dateSince;
//        int beginDateIndexNumber = list.indexOf(this.dateSince);
//        System.out.println("beginDate = " + beginDateIndexNumber);
//        String endDate = header.get(header.size() - 4);
//        int endDateIndexNumber = list.indexOf(endDate);
////        System.out.println("");
//        System.out.println("endDate = " + endDate);
//        System.out.println("endDateIndexNumber = " + endDateIndexNumber);
//        int date = beginDateIndexNumber;
//
////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        //while (date < (endDateIndexNumber-1)) {
//
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        while (beginDateIndexNumber < (endDateIndexNumber-1))
//            //for (CSVRecord record : records)
//            {
//
//                ////////////////////////////////////////////////////////////////////////
/////***/ What Doing? It is showing each entry of CSVRecord" ////
//////Record =  CSVRecord [comment=null, mapping={Province/State=0, Country/Region=1, Lat=2, Long=3, 1/22/20=4, 1/23/20=5, 1/24/20=6, 1/25/20=7, 1/26/20=8, 1/27/20=9, 1/28/20=10, 1/29/20=11, 1/30/20=12, 1/31/20=13, 2/1/20=14, 2/2/20=15, 2/3/20=16, 2/4/20=17, 2/5/20=18, 2/6/20=19, 2/7/20=20, 2/8/20=21, 2/9/20=22, 2/10/20=23, 2/11/20=24, 2/12/20=25, 2/13/20=26, 2/14/20=27, 2/15/20=28, 2/16/20=29, 2/17/20=30, 2/18/20=31, 2/19/20=32, 2/20/20=33, 2/21/20=34, 2/22/20=35, 2/23/20=36, 2/24/20=37, 2/25/20=38, 2/26/20=39, 2/27/20=40, 2/28/20=41, 2/29/20=42, 3/1/20=43, 3/2/20=44, 3/3/20=45, 3/4/20=46, 3/5/20=47, 3/6/20=48, 3/7/20=49, 3/8/20=50, 3/9/20=51, 3/10/20=52, 3/11/20=53, 3/12/20=54, 3/13/20=55, 3/14/20=56, 3/15/20=57, 3/16/20=58, 3/17/20=59, 3/18/20=60, 3/19/20=61, 3/20/20=62, 3/21/20=63, 3/22/20=64, 3/23/20=65, 3/24/20=66, 3/25/20=67, 3/26/20=68, 3/27/20=69, 3/28/20=70, 3/29/20=71, 3/30/20=72, 3/31/20=73, 4/1/20=74, 4/2/20=75, 4/3/20=76, 4/4/20=77, 4/5/20=78, 4/6/20=79, 4/7/20=80, 4/8/20=81, 4/9/20=82, 4/10/20=83, 4/11/20=84, 4/12/20=85, 4/13/20=86, 4/14/20=87, 4/15/20=88, 4/16/20=89, 4/17/20=90, 4/18/20=91, 4/19/20=92, 4/20/20=93, 4/21/20=94, 4/22/20=95, 4/23/20=96, 4/24/20=97, 4/25/20=98, 4/26/20=99, 4/27/20=100, 4/28/20=101, 4/29/20=102, 4/30/20=103, 5/1/20=104, 5/2/20=105, 5/3/20=106, 5/4/20=107, 5/5/20=108, 5/6/20=109, 5/7/20=110, 5/8/20=111, 5/9/20=112, 5/10/20=113, 5/11/20=114, 5/12/20=115, 5/13/20=116, 5/14/20=117, 5/15/20=118, 5/16/20=119, 5/17/20=120, 5/18/20=121, 5/19/20=122, 5/20/20=123, 5/21/20=124, 5/22/20=125, 5/23/20=126, 5/24/20=127, 5/25/20=128, 5/26/20=129, 5/27/20=130, 5/28/20=131, 5/29/20=132, 5/30/20=133, 5/31/20=134, 6/1/20=135, 6/2/20=136, 6/3/20=137, 6/4/20=138, 6/5/20=139, 6/6/20=140}, recordNumber=1, values=[, Afghanistan, 33.0, 65.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 5, 7, 7, 7, 11, 16, 21, 22, 22, 22, 24, 24, 40, 40, 74, 84, 94, 110, 110, 120, 170, 174, 237, 273, 281, 299, 349, 367, 423, 444, 484, 521, 555, 607, 665, 714, 784, 840, 906, 933, 996, 1026, 1092, 1176, 1279, 1351, 1463, 1531, 1703, 1828, 1939, 2171, 2335, 2469, 2704, 2894, 3224, 3392, 3563, 3778, 4033, 4402, 4687, 4963, 5226, 5639, 6053, 6402, 6664, 7072, 7653, 8145, 8676, 9216, 9998, 10582, 11173, 11831, 12456, 13036, 13659, 14525, 15205, 15750, 16509, 17267, 18054, 18969, 19551]]
////System.out.println("Record = " + " " + (record)); // print addresses of each record
////System.out.println("Record Iter = " + " " + (record.iterator())); // print addresses of each record
//
/////***/ What Doing? It is showing all cases on just "tempDate" ////
////           String svc = record.get(tempDate);//Integer.parseInt(record.get(tempDate));
////            List<Integer> dvc = new ArrayList<>();
////            dvc.add(svc);
////System.out.println("svc = " + " " + svc);//Integer.parseInt(record.get(tempDate)));
//
//
////            for(Iterator<String> it = record.iterator(); !it.hasNext();record.iterator().next()) {
////                System.out.println("it = " + it);
////                it.hasNext();
////            }
//
//                //////////////////////////////////////////////////////////////////////
//                // this is printing address
//
////            int date = beginDateIndexNumber;
//                /*locationStats locationStat = new locationStats() */
////                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//
////                NewWebData locationStat = new NewWebData();
////                locationStat.setCountry(record.get("Country/Region"));
////                locationStat.setState(record.get("Province/State"));
//
////            int x = Integer.parseInt(record.get(tempDate)) ;///-- This line is the problem
//                // Solution might be to change int x to Integer x
////            System.out.println("x = " + " " + x );
////            int maximu = Integer.parseInt(record.get(record.size() -1));
////            System.out.println("max " + " " + maximu);
//
////            Integer x = Integer.parseInt(record.get(date)); // ise b comment out kia hai
//
//                //////////////////////////////////////////////////////////////////////
//                // This inner once again revisit records each time.
//                // So to add all cases in locationStat (a temp list)
////                Iterable<CSVRecord> recordsCopy = new ArrayList<>();
//                NewWebData locationStat = new NewWebData();
//                List<Integer> CaseOfAllCountries = new ArrayList<>();
//
//                int u = 1;
//                NewWebData tempWBD = new NewWebData();
//                for (CSVRecord record : records) {
//                    System.out.println("jkjkjkjkjkjkjkjkjkjkjkjkjkjkjk" + u);
//                    u++;
//                    locationStat.setCountry(record.get("Country/Region"));
//                    locationStat.setState(record.get("Province/State"));
//                    CaseOfAllCountries.add(Integer.parseInt(record.get(beginFate)));
//                    // I am commenting it out, because in this v2.1, CaseInASingleDay is of
////                    locationStat.addCase(Integer.parseInt(record.get(beginDateIndexNumber)));
//                    // And in new version 2.2, I will change it to List<String>
////
////                    locationStat.setCasesInASingleDay(record.get(beginFate));
////                    locationStat.addCase(Integer.parseInt(record.get(beginDateIndexNumber))); //tempDateHolder
//                }
//                locationStat.setCasesInASingleDay(CaseOfAllCountries);
//                this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
//                System.out.println("locationStat = " + locationStat);
////                this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
//
//                //System.out.println("locationStat = " + " " + locationStat);
//                // Bhai ye tou return ker raha ha k sab countries k jitne cases the,
//                // dateSince wali date ko, yani dataSince ki date ko sab counties k
//                // cases bta raha hai.
//                // Asan lafzon mein yeh mje column de deta us puri date ka.
//                //////////////////////////////////////////////////////////////////////
//
//                //CaseOfAllCountries = locationStat.getCasesInASingleDay();
//                //System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
////            String id = record.get("Province/State");
////            System.out.println(locationStat);
//
////                System.out.println("this.addStats = " + this.allStats);
//
//
//                beginDateIndexNumber++;
//                System.out.println("beginFate = " + beginFate);
//                beginFate = header.get(beginDateIndexNumber);
////            break;
//            }
//        System.out.println("this.allStat = " + this.allStats);
//            //date++;
//        //}
//
////        this.allStats = tempData;
//    }
//
////    public List<NewWebData> setWetAllStatsLise(String country, String state, List<Integer> caseOfAllCountries)//, List<NewWebData> wbd){
////    {
////        // allStats k ander Integer<list> ko update kerna hai
////        // yani
////        // allStats.CasesInASingleDay = caseOfAllCountries ho jaye ksi tarah se
////        this.allStats.add(new NewWebData(country, state, caseOfAllCountries));
////
////        return this.allStats;
////    }
//
//    @Bean
//    public List<NewWebData> getAllStats() {
//        return allStats;
//    }
//}






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//              Version 3.0
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////@Service
////public class readCsvDataWithRespectToDate {
////
////    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
////
////    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();
////
////    private String dateSince = "3/5/20";
////
////    public void setDate(String dateSince) {
////        this.allStats = new ArrayList<>();
////        this.dateSince = dateSince;
////    }
////
////    @PostConstruct
////    public void fetchData() throws IOException, InterruptedException {
////        List<Integer> CaseOfAllCountries = new ArrayList<>();
////        HttpClient client = HttpClient.newHttpClient();
////        HttpRequest rqst = HttpRequest.newBuilder()
////                .uri(URI.create(csv_file_url)).build();
////
////        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
////
////        StringReader in = new StringReader(rspns.body());
////
////        //////////////////////////////////////////////////////////////////////
////        // Usage here: It will take in the records and will add to list.
////        // I am using it to get the headers of csv.
////        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
////        CSVRecord header = parser.getRecords().get(0);
////
//////        ArrayList<Object> list = new ArrayList<>(recording.size());
////        ArrayList<Object> list = new ArrayList<>(header.size());
//////for (int i = 0; i < recording.size(); i++) { // formally this line
////        for (int i = 0; i < header.size(); i++) {
////            list.add(header.get(i));
////        }
////        System.out.println("header = " + list);
////        System.out.println("Index of header[dataSince] = " + list.indexOf(this.dateSince));
////        //// Idher tak mje mil jaienge saray header, parser.getRecords().get(any_index)
////        //// Is se mje us puri row ki cheezien mil jaiengi.
////        //// Asan ilfaz mein, ye mje rows return ker k deta hai
////        //////////////////////////////////////////////////////////////////////
////
////
////
////        int beginDateIndexNumber = list.indexOf(this.dateSince);
////        System.out.println("beginDate = " + beginDateIndexNumber);
////        String endDate = header.get(header.size()-4);
////        int endDateIndexNumber = list.indexOf(endDate);
//////        System.out.println("");
////        System.out.println("endDate = " + endDate);
////        System.out.println("endDateIndexNumber = " + endDateIndexNumber);
////        List<CSVRecord> records = parser.getRecords();
//////        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//////        for (CSVRecord record : records) {
////
////        System.out.println("Before While");
////        for (;beginDateIndexNumber < (endDateIndexNumber - 1);beginDateIndexNumber++) {
////            System.out.println("After While");
////            for (CSVRecord record : records) {
////                System.out.println("First For");
////                // country wise cha; raha hai
////                // yani pehli iteration mein afghanistan
////                // phr agla phr agla country and so on
////                NewWebData locationStat = new NewWebData();
////                locationStat.setCountry(record.get("Country/Region"));
////                locationStat.setState(record.get("Province/State"));
////
////                System.out.println("record = " + record);
////
////
////                // This inner once again revisit records each time.
////                // So to add all cases in locationStat (a temp list)
////                Iterable<CSVRecord> recordsCopy = new ArrayList<>(records);
////                for (CSVRecord tempRecord : recordsCopy) {
////                    System.out.println("Second For");
////                    System.out.println("tempRecord = " + tempRecord);
////                    // Chal abi b country-wise raha hai, par ye sath mein
////                    // Integer ki list mein add krta ja raha hai.
////                    locationStat.addCase(Integer.parseInt(tempRecord.get(beginDateIndexNumber))); //tempDateHolder
////                }
////                System.out.println("After Second For");
////                System.out.println("Country = " + " " + locationStat.getCountry());
////                System.out.println("locationStat = " + " " + locationStat);
////                // Bhai ye tou return ker raha ha k sab countries k jitne cases the,
////                // dateSince wali date ko, yani dataSince ki date ko sab counties k
////                // cases bta raha hai.
////                // Asan lafzon mein yeh mje column de deta us puri date ka.
////                //////////////////////////////////////////////////////////////////////
////
////                CaseOfAllCountries = locationStat.getCasesInASingleDay();
////                System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
////                this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
////                System.out.println("this.addStats = " + this.allStats.get(1));
////            }
//////            beginDate++;
////        }
////        System.out.println("After First For");
////    } //uncomment it too
////
////    public List<NewWebData> setWetAllStatsLise(String country, String state, List<Integer> caseOfAllCountries)//, List<NewWebData> wbd){
////    {
////        //// allStats k ander Integer<list> ko update kerna hai
////        //// yani
////        //// allStats.CasesInASingleDay = caseOfAllCountries ho jaye ksi tarah se
////        this.allStats.add(new NewWebData(country, state, caseOfAllCountries));
////
////        return this.allStats;
////    }
////
////    @Bean
////    public List<NewWebData> getAllStats() {
////        return allStats;
////    }
////}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//              Version 2.0
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//@Service
//public class readCsvDataWithRespectToDate{
//
//    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//
//    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();
//
//    private String dateSince =  "3/5/20";
//
////        public readCsvDataWithRespectToDate(){
////        this.allStats = new ArrayList<>();
////        dateSince = " ";
////    }
//
////    public readCsvDataWithRespectToDate(String dateSince){
////        this.allStats = new ArrayList<>();
////        this.dateSince = dateSince;
////    }
//
////    public readCsvDataWithRespectToDate(List<NewWebData> allStats, String dateSince){
////        this.allStats = allStats;
////        this.dateSince = dateSince;
////    }
//
//    public void setDate(String dateSince){
//        this.allStats = new ArrayList<>();
//        this.dateSince = dateSince;
//    }
//
////    private List<String[]> getRecords(String content, CSVFormat format) throws IOException {
////        List<String[]> records = new ArrayList<>();
////        CSVParser parser = CSVParser.parse(content, format);
////        for (CSVRecord record : parser.getRecords()) {
////            String[] line = new String[record.size()];
////            for (int i = 0; i < line.length; i++) {
////                line[i] = record.get(i);
////            }
////            records.add(line);
////        }
////        return records;
////    }
//
//
//    @PostConstruct
//    public void fetchData() throws IOException, InterruptedException {
////        String tempDateHolder = dateSince;
////        List<NewWebData> tempData = new ArrayList<>();
//        List<Integer> CaseOfAllCountries = new ArrayList<>();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest rqst = HttpRequest.newBuilder()
//                .uri(URI.create(csv_file_url)).build();
//
//        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
//
//        StringReader in = new StringReader(rspns.body());
//
////
//
////        //CSVParser csvParser = new CSVParser((CSVRecord) rspns.body() , CSVFormat.DEFAULT);
//        //////////////////////////////////////////////////////////////////////
//        // Usage here: It will take in the records and will add to list.
//        // I am using it to get the headers of csv.
//        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
//        CSVRecord header = parser.getRecords().get(0);
//
////        ArrayList<Object> list = new ArrayList<>(recording.size());
//        ArrayList<Object> list = new ArrayList<>(header.size());
////for (int i = 0; i < recording.size(); i++) { // formally this line
//        for (int i = 0;i < header.size();i++){
//            list.add(header.get(i));
//        }
//        System.out.println("header = "  + list);
//        System.out.println("Index of header[dataSince] = "  + list.indexOf(this.dateSince));
//        //// Idher tak mje mil jaienge saray header, parser.getRecords().get(any_index)
//        //// Is se mje us puri row ki cheezien mil jaiengi.
//        //// Asan ilfaz mein, ye mje rows return ker k deta hai
//        //////////////////////////////////////////////////////////////////////
//
//        int beginDate = list.indexOf(this.dateSince);
//        int endDate = list.indexOf(header.size() - 1);
//
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//        for (CSVRecord record : records) {
//        ////////////////////////////////////////////////////////////////////////
//////***/ What is below line  Doing? meanint this System.out.println("Record = " + " " + (record)); ?
//// It is showing each entry of CSVRecord" ////
////Record =  CSVRecord [comment=null, mapping={Province/State=0, Country/Region=1, Lat=2, Long=3, 1/22/20=4, 1/23/20=5, 1/24/20=6, 1/25/20=7, 1/26/20=8, 1/27/20=9, 1/28/20=10, 1/29/20=11, 1/30/20=12, 1/31/20=13, 2/1/20=14, 2/2/20=15, 2/3/20=16, 2/4/20=17, 2/5/20=18, 2/6/20=19, 2/7/20=20, 2/8/20=21, 2/9/20=22, 2/10/20=23, 2/11/20=24, 2/12/20=25, 2/13/20=26, 2/14/20=27, 2/15/20=28, 2/16/20=29, 2/17/20=30, 2/18/20=31, 2/19/20=32, 2/20/20=33, 2/21/20=34, 2/22/20=35, 2/23/20=36, 2/24/20=37, 2/25/20=38, 2/26/20=39, 2/27/20=40, 2/28/20=41, 2/29/20=42, 3/1/20=43, 3/2/20=44, 3/3/20=45, 3/4/20=46, 3/5/20=47, 3/6/20=48, 3/7/20=49, 3/8/20=50, 3/9/20=51, 3/10/20=52, 3/11/20=53, 3/12/20=54, 3/13/20=55, 3/14/20=56, 3/15/20=57, 3/16/20=58, 3/17/20=59, 3/18/20=60, 3/19/20=61, 3/20/20=62, 3/21/20=63, 3/22/20=64, 3/23/20=65, 3/24/20=66, 3/25/20=67, 3/26/20=68, 3/27/20=69, 3/28/20=70, 3/29/20=71, 3/30/20=72, 3/31/20=73, 4/1/20=74, 4/2/20=75, 4/3/20=76, 4/4/20=77, 4/5/20=78, 4/6/20=79, 4/7/20=80, 4/8/20=81, 4/9/20=82, 4/10/20=83, 4/11/20=84, 4/12/20=85, 4/13/20=86, 4/14/20=87, 4/15/20=88, 4/16/20=89, 4/17/20=90, 4/18/20=91, 4/19/20=92, 4/20/20=93, 4/21/20=94, 4/22/20=95, 4/23/20=96, 4/24/20=97, 4/25/20=98, 4/26/20=99, 4/27/20=100, 4/28/20=101, 4/29/20=102, 4/30/20=103, 5/1/20=104, 5/2/20=105, 5/3/20=106, 5/4/20=107, 5/5/20=108, 5/6/20=109, 5/7/20=110, 5/8/20=111, 5/9/20=112, 5/10/20=113, 5/11/20=114, 5/12/20=115, 5/13/20=116, 5/14/20=117, 5/15/20=118, 5/16/20=119, 5/17/20=120, 5/18/20=121, 5/19/20=122, 5/20/20=123, 5/21/20=124, 5/22/20=125, 5/23/20=126, 5/24/20=127, 5/25/20=128, 5/26/20=129, 5/27/20=130, 5/28/20=131, 5/29/20=132, 5/30/20=133, 5/31/20=134, 6/1/20=135, 6/2/20=136, 6/3/20=137, 6/4/20=138, 6/5/20=139, 6/6/20=140}, recordNumber=1, values=[, Afghanistan, 33.0, 65.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 5, 7, 7, 7, 11, 16, 21, 22, 22, 22, 24, 24, 40, 40, 74, 84, 94, 110, 110, 120, 170, 174, 237, 273, 281, 299, 349, 367, 423, 444, 484, 521, 555, 607, 665, 714, 784, 840, 906, 933, 996, 1026, 1092, 1176, 1279, 1351, 1463, 1531, 1703, 1828, 1939, 2171, 2335, 2469, 2704, 2894, 3224, 3392, 3563, 3778, 4033, 4402, 4687, 4963, 5226, 5639, 6053, 6402, 6664, 7072, 7653, 8145, 8676, 9216, 9998, 10582, 11173, 11831, 12456, 13036, 13659, 14525, 15205, 15750, 16509, 17267, 18054, 18969, 19551]]
////System.out.println("Record = " + " " + (record)); // print addresses of each record
////System.out.println("Record Iter = " + " " + (record.iterator())); // print addresses of each record
//
//////***/ What Doing? It is showing all cases on just "tempDate" ////
//           /////String svc = record.get(tempDate);//Integer.parseInt(record.get(tempDate));
//            ////List<Integer> dvc = new ArrayList<>();
//            /////dvc.add(svc);
///////System.out.println("svc = " + " " + svc);//Integer.parseInt(record.get(tempDate)));
//
//
//            for(Iterator<String> it = record.iterator(); !it.hasNext();record.iterator().next()) {
//                System.out.println("it = " + it);
//                it.hasNext();
//            }
//
//            //////////////////////////////////////////////////////////////////////
//            // this is printing address
//
//            int date = beginDate;
//            /*locationStats locationStat = new locationStats() */
//            NewWebData locationStat = new NewWebData();
//            locationStat.setCountry(record.get("Country/Region"));
//            locationStat.setState(record.get("Province/State"));
//
////            int x = Integer.parseInt(record.get(tempDate)) ;///-- This line is the problem
////            System.out.println("x = " + " " + x );
////            int maximu = Integer.parseInt(record.get(record.size() -1));
////            System.out.println("max " + " " + maximu);
//
////            Integer x = Integer.parseInt(record.get(date)); // ise b comment out kia hai
//
//            //////////////////////////////////////////////////////////////////////
//            // This inner once again revisit records each time.
//            // So to add all cases in locationStat (a temp list)
//            Iterable<CSVRecord> recordsCopy = new ArrayList<>();
//            recordsCopy = records;
//            for (CSVRecord tempRecord : recordsCopy) {
//                System.out.println("\nRecord = " + " " + (record) + '\n');
//                System.out.println("tempRecord = " + " " + (tempRecord) +'\n');
//                locationStat.addCase(Integer.parseInt(tempRecord.get(date))); //tempDateHolder
//            }
////            System.out.println("locationStat = " + " " +  locationStat);
//            // Bhai ye tou return ker raha ha k sab countries k jitne cases the,
//            // dateSince wali date ko, yani dataSince ki date ko sab counties k
//            // cases bta raha hai.
//            // Asan lafzon mein yeh mje column de deta us puri date ka.
//            //////////////////////////////////////////////////////////////////////
////            int i  = 0;
////
////            while (i != 10){ // Issue: I am not updating the date
////                locationStat.addCase(Integer.parseInt(record.get(tempDate)));
////                i++;
//////                System.out.println("locationStat.setLatestCases = " + " " +  locationStat);
//////                i++;
////            }
//
//            CaseOfAllCountries = locationStat.getCasesInASingleDay();
//            System.out.println("CaseOfAllCountries = " + CaseOfAllCountries);
////            String id = record.get("Province/State");
////            System.out.println(locationStat);
//            this.allStats.add(new NewWebData(locationStat.getCountry(), locationStat.getState(), locationStat.getCasesInASingleDay()));
//            date++;
////            System.out.println("this.addStats = " + this.allStats);
//        }
//
////        this.allStats = tempData;
//    }
//
////    public List<NewWebData> setWetAllStatsLise(String country, String state, List<Integer> caseOfAllCountries)//, List<NewWebData> wbd){
////    {
////        // allStats k ander Integer<list> ko update kerna hai
////        // yani
////        // allStats.CasesInASingleDay = caseOfAllCountries ho jaye ksi tarah se
////        this.allStats.add(new NewWebData(country, state, caseOfAllCountries));
////
////        return this.allStats;
////    }
//
//    @Bean
//    public List<NewWebData> getAllStats() {
//        return allStats;
//    }
//}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//              Version 1.0
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//@Service
//public class readCsvDataWithRespectToDate{
//
//    private String csv_file_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
//    //    private myUrl csv_file_url ;
//
//
////    public readCsvDataWithRespectToDate() {
////    }
//
//    private List<NewWebData> allStats = new ArrayList<>(); // in tutorial did the new Array<>();
//
//    private String dateSince = "3/5/20";
//
////    public readCsvDataWithRespectToDate(){
////        this.allStats = new ArrayList<>();
////        dateSince = " ";
////    }
////
////    public readCsvDataWithRespectToDate(String dateSince){
////        this.allStats = new ArrayList<>();
////        this.dateSince = dateSince;
////    }
////
////    public readCsvDataWithRespectToDate(List<NewWebData> allStats, String dateSince){
////        this.allStats = allStats;
////        this.dateSince = dateSince;
////    }
//     public void setDate(String dateSince){
//         this.allStats = new ArrayList<>();
//         this.dateSince = dateSince;
//     }
//
//    @PostConstruct
//    //public List<NewWebData> fetchData() throws IOException, InterruptedException {
//    public void fetchData() throws IOException, InterruptedException {
//        List<NewWebData> tempData = new ArrayList<>();
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest rqst = HttpRequest.newBuilder()
//                .uri(URI.create(csv_file_url)).build();
//
//        HttpResponse<String> rspns = client.send(rqst, HttpResponse.BodyHandlers.ofString());
//
////        CSVReader csvReader = new CSVReaderBuilder(filereader)
////                .withSkipLines(1)
////                .build();
////        List<String[]> allData = csvReader.readAll();
//
////        Date d1 = new Date();
////        System.out.println("Current date is " + d1.toString());
//        StringReader in = new StringReader(rspns.body());
////        System.out.println("Single Record: " + " " +in.toString()); // prints address
//
////        CSVParser csvParser = new CSVParser((CSVRecord) rspns.body() , CSVFormat.DEFAULT);
//
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
//
//        // These are also very important
//        // Thtat's the one doing it
////        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
////        List<CSVRecord> csvRecords2 = parser.getRecords();
////        for (CSVRecord record : csvRecords2) {
////            System.out.println("Single Record: " + " " + record); // p
////        }
//
//        //////// Below one 4 lines are important
////        CSVParser parser = CSVParser.parse(rspns.body(), CSVFormat.DEFAULT);
////        for (CSVRecord csvRecord : parser) {
////            System.out.println("CSVParse = " + parser.getRecords());
////        }
//
////        System.out.println("CSVRecords2 = " + csvRecords2.get(0));
//
//        //        int idni = 0;
////        for (CSVRecord csvRecord : parser) {
////            System.out.println("CSVParse idni = " + idni+ " " + csvRecord.get(idni) + "\n");
////            idni++;
////        }
//
//
//
//        //.getRecordNumber()
////        int i = 2;
//        for (CSVRecord record : records) {
//            System.out.println("Get Record Number = " + " " + (record.getRecordNumber()));
//            System.out.println("Get & Int = " + " " + Integer.parseInt(record.get(dateSince)));
//
//            // this is printing address
//            /*locationStats locationStat = new locationStats() */
//            NewWebData locationStat = new NewWebData();
//            locationStat.setCountry(record.get("Country/Region"));
//            locationStat.setState(record.get("Province/State"));
////            int x = Integer.parseInt(record.get(record.get(dateSince)));
////            System.out.println("x = " + " " + x );
////            int max = Integer.parseInt(record.get(record.size() -1));
////            System.out.println("max " + " " + max);
//            int i  = 0;
//            while (i != 10){ // Issue: I am not updating the date
//                locationStat.addCase(Integer.parseInt(record.get(dateSince)));
////                System.out.println("locationStat.setLatestCases = " + " " +  locationStat);
////                i++;
//            }
//
//            tempData.add(locationStat);
////            System.out.println("Single Record: " + " " + record ); // if records prints address (because records is a list)
//            // if record then it prints smoothly because record a single element in list names records
//
//            /*
//            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
//            for (CSVRecord csvRecord : csvParser) {
//                // Accessing Values by Column Index
//                String name = csvRecord.get(0);
//                String email = csvRecord.get(1);
//                String phone = csvRecord.get(2);
//                String country = csvRecord.get(3);
//
//                System.out.println("Record No - " + csvRecord.getRecordNumber());
//                System.out.println("---------------");
//                System.out.println("Name : " + name);
//                System.out.println("Email : " + email);
//                System.out.println("Phone : " + phone);
//                System.out.println("Country : " + country);
//                System.out.println("---------------\n\n");
//            */
//
//
//
//            String id = record.get("Province/State");
////            System.out.println(locationStat);
//        }
//        this.allStats = tempData;
//        //System.out.println(rspns.body());
////        return this.allStats; // return type: List<NewWebData?
//    }
//
//    @Bean
//    public List<NewWebData> getAllStats() {
//        return allStats;
//    }
//
//
//}
