package com.geek.dz6;

import java.util.*;

public class PhoneDictionary {
    private Map<String, ArrayList<String>> phoneDictionary;

    public PhoneDictionary() {
        phoneDictionary = new LinkedHashMap();
    }

    public void add(String name, String phone) {
        ArrayList<String> phones;
        phones = phoneDictionary.getOrDefault(name, new ArrayList<String>());
        phones.add(phone);
        phoneDictionary.put(name, phones);
    }

    public ArrayList<String> get(String name) {
        return phoneDictionary.get(name);
    }
}
