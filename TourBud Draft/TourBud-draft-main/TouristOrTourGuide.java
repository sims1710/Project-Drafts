package com.example.tourbud;

public class TouristOrTourGuide {
    private Person person;
    private String role;

    public TouristOrTourGuide(Person person){
        this.person = person;
        this.role = person.getRoleAsString();
    }

    public void displayMenu(){
        System.out.println("Welcome " + person.getFirstName() + "!");
        System.out.println("You have logged in as a" + role);
    }

}
