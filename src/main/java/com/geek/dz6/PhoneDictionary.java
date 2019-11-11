package com.geek.dz6;

import java.util.*;

public class PhoneDictionary {
    private Map<String, HashSet<String>> phoneDictionary;

    public PhoneDictionary() {
        phoneDictionary = new HashMap();
    }

    public void add(String name, String phone) {
        HashSet<String> phones;
        phones = phoneDictionary.getOrDefault(name, new HashSet<>());
        phones.add(phone);
        phoneDictionary.put(name, phones);
    }

    public HashSet<String> get(String name) {
        return phoneDictionary.get(name);
    }
}
