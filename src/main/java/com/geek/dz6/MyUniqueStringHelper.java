package com.geek.dz6;

import java.util.*;

public class MyUniqueStringHelper {
    public static HashSet<String> getUniqueElements(ArrayList<String> stringArray) {
        return new HashSet<String>(stringArray);
    }

    public static HashMap getComputedWordsCount(ArrayList<String> stringArray) {
        HashMap<String, Integer> resultMap = new HashMap<String, Integer>();

        HashSet<String> uniqueSet = getUniqueElements(stringArray);

        Iterator uniqueIterator = uniqueSet.iterator();
        while (uniqueIterator.hasNext()) {
            String el = uniqueIterator.next().toString();
            resultMap.put(el, Collections.frequency(stringArray, el));
        }

        return resultMap;

    }

}
