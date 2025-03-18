package com.example.tourbud;

import java.util.LinkedList;
import java.util.Scanner;

public class TourGuide {
    private static TourLinkedList tourList;

    public static void main(String[] args) {
        tourList = new TourLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Do you want to create a tour object? (y/n): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("y")) {
                System.out.print("Enter tour name: ");
                String name = scanner.nextLine().trim();

                Tour tour = new Tour(name);
                tourList.addTour(tour);
            } else {
                break;
            }
        }
        tourList.display();
    }

    //need to implement messaging
}

