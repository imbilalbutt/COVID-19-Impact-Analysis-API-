package com.imbilalbutt.bilalButtarbisoftv14.service;

import com.imbilalbutt.bilalButtarbisoftv14.models.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

//@Service("repoOfUsers")
@Service
public class ActiveUserStore {
    List<Person> persons = new ArrayList<>();


    // Check out this Bean, this good example for your problem with annotations.
    // It is beans where it it returns same type of data as class name.
    // here class name is PersonService and here returning type is PersonService
//    @Bean
    public ActiveUserStore activeUserStore(){
        return new ActiveUserStore();
    }

    public int addPerson(@RequestBody Person person){
        this.persons.add(person);
        return 1;
    }

    public int addUniquePerson(Person person){
        if(!this.persons.contains(person))
            this.persons.add(person);
        return 1;
    }

    public int addPerson(String name, String pswd, String role){
        Person tempPerson = new Person();
        tempPerson.setName(name);
        tempPerson.setPassword(pswd);
        tempPerson.setRole(role);

        this.persons.add(tempPerson);
        return 1;
    }

    public void setPersonList(List<Person> persons) {
        this.persons = persons;
    }

    public Person getSpecificUser(String username){
        Person tempPerson = new Person();
        for (Person singlePerson: this.persons) {
            if(singlePerson.getName().equals(username)) {
                System.out.println("Inside ActiveUserStore::getSpecificUser");
                System.out.println(singlePerson.getName() + " " + singlePerson.getPassword()
                        + " " + singlePerson.getRole());
                tempPerson.setName(singlePerson.getName());
                tempPerson.setPassword(singlePerson.getPassword());
                tempPerson.setRole(singlePerson.getRole());
            }
        }

        return tempPerson;
    }

    public List<Person> getPersons() {
        return this.persons;
    }

    public void removeSpecificUser(String username){
        Person person = getSpecificUser(username);
        this.persons.remove(person);
    }

}
