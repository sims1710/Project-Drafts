package com.example.tourbud;

import java.util.List;
import java.util.Scanner;

public class UserLogin {
    //assume this list is populated withPerson objects from the database
    private List<Person> personList;
    public void verifyIdentity(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        Person person = findPersonByPNum(phoneNumber);
        if (person == null){
            System.out.println("No current user with this phone number ");
            return;
        }

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        if (person.getPassword().equals(password)){
            System.out.println("Identity verified!");
        } else {
            System.out.println("Incorrect password");
        }
    }

    private Person findPersonByPNum(String phoneNumber) {
        for (Person person : personList) {
            if (person.getPhoneNumber().equals(phoneNumber)) {
                return person;
            }
        }
        return null;
    }
}