# Java Hiring Assignment

**COVID-19 Impact Analysis API:**

This document will exert pressure on approaches taken in the implementation
and the assumptions I took to build this application. 
The implementation strictly follows the MVC architecture. For dependency handling
I followed Maven.

@author Bilal Ahmad Butt
@imbilalbutt
——————————————————————————————————————————————————————————————————————————————————

**Requirement # 1 = To get a JWT Token**
>> For this requirement the files used are:
```
SecurityConfiguration.java
MyUserDetailsService.java
JwtAuthenticationController.java
JwtRequestFilter.java
JwtRequest.java
JwtResponse.java
JwtTokenUtil.java
```

> Approach: First I need to generate the JWTs, for this purpose I made *generateToken()* method.
Then using the details of user and if he successfully gets the token then user logins to further
see the pages. For making a request I defined a *request structure*, and for response I did the
same, that I define the structure of *response* (meaning how to interpret it further).
All doing this does not make authentication possible. For this I added a filter, which first 
listened to http request and then this filter comes in and check if the user have valid 
JWT token. If he does he is allowed to see other pages.


——————————————————————————————————————————————————————————————————————————————————

**Requirement # 2 = All new cases reported today**

>> For this requirement the files used are:
```
webData.java
etCsvDataService.java
svDataController.java
svData.html
```

> Approach: Using the CSVParser which is implemented by Apache, I got the
csv data, when I got all records, I jumped directly at the end. And get 
the information from there.

——————————————————————————————————————————————————————————————————————————————————

**Requirement # 3 = All new cases reported today country wise (sorted by cases reported today descending)**

>> For this requirement the files used are:
```
webData.java
getCsvDataService.java
SortedDataController.java
highest.html
```

> Approach: Using the already obtained information in Requirement id# 2,
I assembled it to thiss functionality, by firstly using builtin sorted
function, in this way I got the sorted ascending order data. After this 
I simply revered it. And I got the results.

——————————————————————————————————————————————————————————————————————————————————

**Requirement # 4 = All new cases reported today in a country**

>> For this requirement the files used are:
```
 webData.java
 getSortedData.java
 PatricularCountryDataService.java
 PatricularCountryDataController.java
 country.html
 ```

> *Assumption* : I hard coded the country name, because as there was not
mentioned that it should be taken from the user, after he logs in. And 
also there was not mentioned that either I should take data from database
or in-memory. Thus, I hard coded the country name as "Australia" but it 
can be changed. Also, I assumed that when the you need country's data, 
then if in dataset, if if contains the states or district. It should be
outputted too. And that's what I assumed for this functionality.


> Approach: When I have a country name, I only took the sorted data. And 
then by iterating inside list-of-sorted-countries (this I obtained from 
getSortedData variable inside **PatricularCountryDataService.java**). 
Then I matched the field which if their countries are similiar, then I 
added them to temporary variable. And returned it using getter.

——————————————————————————————————————————————————————————————————————————————————

**Requirement # 5 = All new cases reported since a date in a country (choose whatever format but explain that in readme file)**

>> For this requirement the files used are:
```
NewWebData.java
readCsvDataWithRespectToDate.java // check for sinceDate
readCsvDataWithRespectToDateController.java
date.html
```
>> *Assumption*: I am assuming that, a country is subjective here. Meaning
requirement saying "one country", it is explicity saying that if you take
a country then all of it's cases from specific date should be outputted.
Thus I am prinitng all countries data. I am not just displaying the one 
country data.

> Approach: For this I created a NewWebData.java, in this NewWebData.java
class, I modified the CasesInASingleDay to List<String>. This is holding 
single country, single state and list of its all cases from that date till
today.

**Note: For same country So if a new states come in, there will be a new 
entry in *List<NewWebData> xyz*. It will certainly not intermingle the
country's data. It will keep the purity by keeping the seperate entry if
a new state comes in even for same country.**

——————————————————————————————————————————————————————————————————————————————————

**Requirement # 6 = Top N countries with most reported cases today**

>> For this requirement the files used are:
```
webData.java
getSortedData.java
TopNCountries.java
topNCountriesController.java
topNCountries.html
```
> Approach: I autowired and got the sortedData, then I sliced and took only the first
ten elements. To get each element I used .get() method, to get each entry of array
type List<webData>.

——————————————————————————————————————————————————————————————————————————————————


**Requirement # 7 = To get list of users currently accessing the API**

>> For this requirement the files used are:
```
Person.java
ActiveUserController.java
ActiveUserStore.java
MyUserDetailsService.java
loggedUsers.html
```
>> Approach: Firstly the users allowed are only those which are added in
"MyUserDetailsService.java", then whenever a person successfully logins, 
I add that user in variable type and name as "ActiveUserStore activeUserStore".
And for view, I also showed their roles. But keep in mind this functionality is 
speciifcally made for admins only.

——————————————————————————————————————————————————————————————————————————————————

# Side Notes:

> To add new user go to **MyUserDetailsService.java** and in the default
> constructor add like this:
> users.add(new Person("abc", "xyc", "admin"));

* To change date and get data accordingly, go to 
* "readCsvDataWithRespectToDate.java" and change the private member
* "private String dateSince" with correct format
* format is d/m/yy 
* yy means only last parts i.e. for 2019, just write 19.



[Link to demo and code files] https://drive.google.com/drive/folders/1v7VPifwAvhnITLcCLVWUOrs0WNvcX7dj?usp=sharing 