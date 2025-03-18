package com.example.tourbud;

import java.util.Scanner;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class UserRegistration {
    public static void main(String[] args) {
        Person person = readFromUserInput();
        System.out.println("Password set successfully!");
    }
    private static Person readFromUserInput() {
        Person person = new Person();
        //asking user input for the different variables
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();
        person.setFirstName(firstName);

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();
        person.setLastName(lastName);

        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        person.setEmail(email);

        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        person.setAge(age);

        System.out.println("Enter country code (UK or US): ");
        String countryCode = scanner.next().toUpperCase();
        person.setCountryCode(countryCode);

        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        person.setPhoneNumber(phoneNumber, phoneNumber);

        //passing the values as attributes for the object person

        scanner.close();
        try {
            return new Person(firstName, lastName, email, age, phoneNumber, countryCode);
        } catch (IllegalArgumentException e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }
}
class Person {
    private String firstName, lastName, email, password, phoneNumber, countryCode;
    private int age;
    private static final int minAge = 16;
    private static final int maxAge = 120;
    private Object role;
    // no-args Person class constructor
    public Person(String firstName, String lastName, String email, int age, String phoneNumber, String countryCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAge(age);
        setPhoneNumber(phoneNumber,countryCode);
    }
    // args Person class constructor
    public Person(){
        this(null, null, null, 0, null, null);
    }

    // getter for first name
    public String getFirstName() {
        return firstName;
    }

    // setter for first name
    public void setFirstName(String firstName) {
        // checks if first name is empty
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First Name cannot be empty");
        }
        // checks if the first name only contains alphabets
        if (!(firstName.matches("^[a-zA-Z\\s]+$"))) {
            throw new IllegalArgumentException("Invalid: First Name must contain only letters and spaces");
        }
        this.firstName = firstName;
    }
    // getter for Last Name
    public String getLastName() {
        return lastName;
    }

    // setter for Last Name
    public void setLastName(String lastName) {
        // checks if last name is empty
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last Name cannot be empty");
        }
        // checks if last name only contains alphabets
        if (!(lastName.matches("^[a-zA-Z\\s]+$"))) {
            throw new IllegalArgumentException("Invalid: Last Name must contain only letters and spaces");
        }
        this.lastName = lastName;
    }
    // getter for email
    public String getEmail() {
        return email;
    }

    // setter for email
    public void setEmail(String email) {
        // checks if email is empty
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        // checks if the email follows the following format: [a-z][0-9]@[a-z][0-9]
        if (!(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }
    // getter for age
    public int getAge() {
        return age;
    }

    // setter for age
    public void setAge(int age) {
        // checking that age falls within the range to 16 to 120
        if (age <= minAge || age >= maxAge) {
            throw new IllegalArgumentException("Age must be between " + minAge + " and " + maxAge);
        }
        this.age = age;
    }

    // getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber, String countryCode) {
        // removes space
        phoneNumber = phoneNumber.trim();
        // checks if the phone number is null
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        //
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber parsedNumber = phoneUtil.parse(phoneNumber, countryCode);
            if (phoneUtil.isValidNumber(parsedNumber)) {
                this.phoneNumber = phoneUtil.format(parsedNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
            } else {
                throw new IllegalArgumentException("Invalid phone number");
            }
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Invalid phone number: " + e.getMessage());
        }
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        if (countryCode == null || countryCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Country Code cannot be empty");
        }
        if (!countryCode.matches("^[A-Z]{2}$")) {
            throw new IllegalArgumentException("Invalid country code");
        }
        this.countryCode = countryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter your password: ");
            password = input.nextLine();

            if (!(validatePassword(password))){
                System.out.println("Password must contain at least one uppercase letter, one lowercase letter, one number and be at least 6 characters long.");
            }
        } while (!(validatePassword(password)));
        input.close();
        this.password = password;
    }

    private boolean validatePassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$");
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type 'tourist' or 'tour guide'");
        String roleChoice = input.nextLine();
        input.close();
        if (roleChoice.equalsIgnoreCase("Tourist")) {
            this.role = new Tourist();
        } else if (roleChoice.equalsIgnoreCase("Tour Guide")) {
            this.role = new TourGuide();
        } else {
            throw new IllegalArgumentException("Invalid role choice");
        }
    }

    public String getRoleAsString(){
        if (this.role instanceof Tourist) {
            return "Tourist";
        } else if (this.role instanceof TourGuide) {
            return "Tour Guide";
        } else {
            return "Error";
        }
    }
}