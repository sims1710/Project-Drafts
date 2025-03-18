package com.example.tourbud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Scanner;

public class Tag {
    private Map<String, List<String>> tagDictionary;

    public Tag() {
        tagDictionary = new HashMap<>();
    }

    public void addTag(String tagType, String tagValue) {
        if (tagDictionary.containsKey(tagType)) {
            List<String> locationTags = tagDictionary.get(tagType);
            if (!locationTags.contains(tagValue)) {
                locationTags.add(tagValue);
            }
        } else {
            List<String> locationTags = new ArrayList<>();
            locationTags.add(tagValue);
            tagDictionary.put(tagType, locationTags);
        }
    }

    public List<String> getTags() {
        return new ArrayList<>(tagDictionary.keySet());
    }

    public void removeTag(String tagType, String tagValue) {
        if (tagDictionary.containsKey(tagType)) {
            List<String> locationTags = tagDictionary.get(tagType);
            locationTags.remove(tagValue);
            if (locationTags.isEmpty()) {
                tagDictionary.remove(tagType);
            }
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> tagDictionary = new HashMap<>();
        List<String> foodTags = Arrays.asList("Restaurant", "Cafe", "Bar", "Bakery");
        List<String> shoppingTags = Arrays.asList("Mall", "Market", "Outlet", "Boutique");
        List<String> sightseeingTags = Arrays.asList("Museum", "Monument", "Park", "Zoo", "Aquarium");
        List<String> outdoorTags = Arrays.asList("Beach", "Hiking", "Camping", "Fishing", "Skiing");
        tagDictionary.put("Food", foodTags);
        tagDictionary.put("Shopping", shoppingTags);
        tagDictionary.put("Sightseeing", sightseeingTags);
        tagDictionary.put("Outdoor", outdoorTags);

    }
}
