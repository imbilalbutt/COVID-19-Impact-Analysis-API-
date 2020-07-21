package com.imbilalbutt.bilalButtarbisoftv14.data;

import com.imbilalbutt.bilalButtarbisoftv14.models.Person;
import com.imbilalbutt.bilalButtarbisoftv14.service.ActiveUserStore;
import org.springframework.context.annotation.Bean;

public class fakePersonsRepository {

    private ActiveUserStore fakePersonRepo;

    public void addFakePersons() {
        this.fakePersonRepo.addPerson(new Person("abc", "abc", "admin"));
        this.fakePersonRepo.addPerson(new Person ("def", "def", "user"));
        this.fakePersonRepo.addPerson(new Person("ghi", "ghi", "user"));
        this.fakePersonRepo.addPerson(new Person("jkl", "jkl", "user"));
        this.fakePersonRepo.addPerson(new Person("mno", "mno", "user"));
        this.fakePersonRepo.addPerson(new Person("pqr", "pqr", "user"));
        this.fakePersonRepo.addPerson(new Person("stu", "stu", "user"));
        this.fakePersonRepo.addPerson(new Person("vwx", "uvx", "user"));
        this.fakePersonRepo.addPerson(new Person("xyz", "xyz", "admin"));
        this.fakePersonRepo.addPerson(new Person ("bilal", "123", "admin"));
        this.fakePersonRepo.addPerson(new Person ("admin", "admin", "admin"));

        System.out.println("Inside ActiveUserStore::getSpecificUser");
        this.fakePersonRepo.getSpecificUser("abc");
        System.out.println("Done with addfakeperson");
    }
}
