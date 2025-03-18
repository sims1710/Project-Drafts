package com.example.tourbud;

import java.util.ArrayList;

//to display the welcome page
public class Welcome {
    private Person person;
    private String role;

    public Welcome(Person person){
        this.person = person;
        this.role = person.getRoleAsString();
    }


}

