package com.example.tourbud;

import java.util.LinkedList;


public class Tourist {
    private LinkedList<Tour> list;

    public Tourist(){
        this(null);
    }

    public Tourist(LinkedList<Tour> list) {
        this.list = list;
    }

    public void displayTours() {
        for (Tour tour : list) {
            tour.displayTour();
        }
    }

    public void searchToursByTags(String[] selectedTags) {
        boolean found = false;
        for (Tour tour : list) {
            for (String tag : selectedTags) {
                if (tour.getPlace().getSelectedTags().contains(tag)) {
                    found = true;
                } else {
                    found = false;
                    break;
                }
            }
            if (found) {
                System.out.println("Tour found:");
                tour.displayTour();
            }
        }
    }
    //messaging left
}
