package com.imbilalbutt.bilalButtarbisoftv14.controllers;


//import com.imbilalbutt.bilalButtarbisoftv14.filters.LoggedUser;
import com.imbilalbutt.bilalButtarbisoftv14.models.Person;
import com.imbilalbutt.bilalButtarbisoftv14.service.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;

@Controller
public class ActiveUserController {

//    @Qualifier("repoOfUsers")
    @Autowired private ActiveUserStore activeUserStore;

//    @Autowired
//    LoggedUser loggedUser;

    @GetMapping("/loggedUsers")
    public String getLoggedUsers(Locale locale, Model model4) {
//        List<Person> personList = loggedUser.getActiveUserStore().getPersons();
        List<Person> personList = activeUserStore.getPersons();

        // Test display
//        for (Person singleUser: personList) {
//            System.out.println(singleUser.getName());
//        }
        model4.addAttribute("personList", personList);
        return "loggedUsers";
    }

}
