package com.example.tourbud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TagSelector {
    private Tag tag;

    public TagSelector(Tag tag) {
        this.tag = tag;
    }

    public void viewTagDictionary() {
        System.out.println("Tag Dictionary:");
        for (String tagType : tag.getTags()) {
            System.out.println("- " + tagType + ": " + String.join(", ", tag.getTags()));
        }
    }

    public List<String> selectTags() {
        Scanner scanner = new Scanner(System.in);
        List<String> selectedTags = new ArrayList<>();

        boolean done = false;
        while (!done) {
            System.out.println("Enter a tag type or 'done' to finish:");
            String tagType = scanner.nextLine().toLowerCase();
            if (tagType.equals("done")) {
                done = true;
            } else if (!tag.getTags().contains(tagType)) {
                System.out.println("Invalid tag type. Please try again.");
            } else {
                System.out.println("Available tags in the " + tagType + " category:");
                for (String tagValue : tag.getTags()) {
                    System.out.println("- " + tagValue);
                }
                System.out.println("Enter a tag or 'done' to finish:");
                String tagValue = scanner.nextLine().toLowerCase();
                while (!tagValue.equals("done")) {
                    if (!tag.getTags().contains(tagValue)) {
                        System.out.println("Invalid tag. Please try again.");
                    } else {
                        selectedTags.add(tagValue);
                        System.out.println("Tag added.");
                    }
                    System.out.println("Enter another tag or 'done' to finish:");
                    tagValue = scanner.nextLine().toLowerCase();
                }
            }
        }

        return selectedTags;
    }
}