package com.example.tourbud;
import java.util.Scanner;

public class UserAuthentication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tour Bud!");
        boolean running = true;

        while (running){
            System.out.println("Type '1' to register a new account or type '2' to sign in an existing account");
            String input = scanner.nextLine();

            try{
                int choice = Integer.parseInt(input);
                switch (choice){
                    case 1 :
                        UserRegistration userRegistration = new UserRegistration();
                        userRegistration.main(args);
                        break;

                    case 2:
                        UserLogin userLogin = new UserLogin();
                        userLogin.verifyIdentity();
                        break;

                    default:
                        running = false;
                        break;
                }
            } catch (NumberFormatException e){
                running = false;
            }
        }
        System.out.println("Thank you for using Tour Bud!");
    }
}