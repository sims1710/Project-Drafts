package com.example.tourbud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Tour {
    private Place place;
    private String tourName;
    private List<Place> places;

    public Tour(String tourName) {
        this.tourName = tourName;
        places = new ArrayList<>();
    }
    public void addPlace(Place place){
        if (places.contains(place)) {
            System.out.println("This place has already been added.");
        } else {
            places.add(place);
        }
    }

    public void addPlaces(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of places for the tour " + tourName + ": ");
        int numberOfPlaces = scanner.nextInt();
        scanner.nextLine(); //consume the newline character

        for (int i = 0; i < numberOfPlaces; i++) {
            Place place = new Place();

            System.out.println("Enter place " + (i+1) + "details:");
            System.out.println("Name: ");
            String name = scanner.nextLine().trim();
            place.setPlaceName(name);

            System.out.print("Location: ");
            String location = scanner.nextLine().trim();
            place.setLocation(location);

            System.out.print("Date (dd-MM-yyyy): ");
            String date = scanner.nextLine().trim();
            place.setDate(date);

            System.out.print("Time (HH:mm): ");
            String time = scanner.nextLine().trim();
            place.setTime(time);
        }
    }

    public void displayTour(){
        System.out.println("Tour itinerary for " + tourName + ":");
        for (int i = 0; i < places.size(); i++) {
            System.out.println((i+1) + ". " + places.get(i).getPlaceName() + ", " + places.get(i).getLocation() + ", " + places.get(i).getDate() + ", " + places.get(i).getTime() + ", " + places.get(i).getSelectedTags());
        }
    }
    public Place getPlace() {
        return place;
    }
}

class Place {
    private String placeName, location, date, time;
    private List<String> selectedTags;

    public Place(String placeName, String location, String date, String time) {
        setPlaceName(placeName);
        setLocation(location);
        setDate(date);
        setTime(time);
    }

    public Place(){
        this(null, null, null, null);
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        if (placeName == null && placeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Place name cannot be empty");
        }
        if (!placeName.matches(".*[a-zA-Z]+.*")) {
            throw new IllegalArgumentException("Place name must contain at least one letter");
        }
        this.placeName = placeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null & !(location.trim().isEmpty())){
            throw new IllegalArgumentException("Location cannot be empty");
        }
        if (!location.matches(".*[a-zA-Z]+.*")){
            throw new IllegalArgumentException("Location must contain at least one letter");
        }
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date != null & !(date.trim().isEmpty())){
            throw new IllegalArgumentException("Date cannot be empty");
        }
        String[] dateParts = date.split("-");  // input dd-mm-yyyy
        if (dateParts.length != 3) {
            throw new IllegalArgumentException("Invalid date format");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1; //Month starts from 0
            int year = cal.get(Calendar.YEAR);

            //Check if the year is valid
            if (year < Calendar.getInstance().get(Calendar.YEAR)) {
                throw new IllegalArgumentException("Invalid year");
            }

            //Check if the month is valid
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Invalid month");
            }

            //Check if the day is valid
            int maxDays = 31;
            if (month == 4 || month == 6 || month == 9 || month ==11){
                maxDays = 30;
            } else if (month == 2) {
                if ((year % 4 == 0 && year % 100 !=0) || year % 400 == 0) {
                    maxDays = 29;
                } else {
                    maxDays = 28;
                }
            }
            if (day < 1 || day > maxDays) {
                throw new IllegalArgumentException("Invalid day");
            }
            this.date = date;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (time != null & !(time.trim().isEmpty())){
            throw new IllegalArgumentException("Time cannot be empty");
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setLenient(false); // to enforce strict time parsing

        Date parsedTime = null;
        try{
            parsedTime = timeFormat.parse(time);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format");
        }

        //combine the date and time to create a new Date object
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateTimeFormat.setLenient(false); // teto enforce strict date-time parsing

        Date parsedDateTime = null;
        try {
            parsedDateTime = dateTimeFormat.parse(this.date + " " + time);
        } catch (ParseException e){
            throw new IllegalArgumentException("Onvalid date-time format");
        }

        //check that parsed date-time is not less than today's date time
        Date currentDateTime = new Date();
        if (parsedDateTime.before(currentDateTime)) {
            throw new IllegalArgumentException("Time cannot be less than today's time");
        }

        //check that parsed time is greater than current time by 1 hour
        Calendar parsedCalendar = Calendar.getInstance();
        parsedCalendar.setTime(parsedDateTime);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDateTime);
        currentCalendar.add(Calendar.HOUR_OF_DAY, 1);

        if (this.date.equals(new SimpleDateFormat("dd-MM-yyyy").format(currentDateTime))) {
            if (parsedCalendar.before(currentCalendar)) {
                throw new IllegalArgumentException("Time must be at least 1 hour after current time");
            }
        }
        this.time = timeFormat.format(parsedTime);
    }

    public List<String> tagChosen() {
        Tag tag = new Tag();
        TagSelector tagSelector = new TagSelector(tag);
        tagSelector.viewTagDictionary();
        return tagSelector.selectTags();
    }

    public List<String> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags() {
        this.selectedTags = tagChosen();
    }
}
