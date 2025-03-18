package com.example.tourbud;

import java.util.LinkedList;

public class TourLinkedList {
    private LinkedList<Tour> list;

    public TourLinkedList() {
        list = new LinkedList<Tour>();
    }

    public void addTour(Tour tour) {
        list.addLast(tour);
    }

    public void display() {
        for (Tour node : list) {
            node.displayTour();
        }
    }
}

