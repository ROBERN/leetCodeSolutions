package com.sujeet.leetCode;

import java.util.*;

public class Problem432_Order1 {
    /** Initialize your data structure here. */

    private Map<String, Integer> forwardMap;
    private TreeMap<Integer, Set<String>> revMap;
    public Problem432_Order1() {
        forwardMap = new HashMap<>();
        revMap = new TreeMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int newVal = 1;
        if(forwardMap.containsKey(key)) {
            int val = forwardMap.get(key);
            newVal = val+1;
            revMap.get(val).remove(key);
            // removed something? Check if its value is empty
            if (revMap.get(val).size() == 0)
                revMap.remove(val);
        }
        forwardMap.put(key, newVal);
        revMap.computeIfAbsent(newVal, empty -> new HashSet<>()).add(key);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(forwardMap.containsKey(key)) {
            int val = forwardMap.get(key);
            int newVal = val-1;
            revMap.get(val).remove(key);
            // removed something? Check if its value is empty
            if (revMap.get(val).size() == 0)
                revMap.remove(val);
            // removed something? Check if its value is empty
            if(newVal == 0) {
                forwardMap.remove(key);
            } else {
                forwardMap.put(key, newVal);
                revMap.computeIfAbsent(newVal, empty -> new HashSet<>()).add(key);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (revMap.size() == 0)
            return "";
        Set<String> keys = revMap.lastEntry().getValue();
        return keys.size() == 0 ? "" : keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (revMap.size() == 0)
            return "";
        Set<String> keys = revMap.firstEntry().getValue();
        return  keys.size() == 0 ? "" : keys.iterator().next();
    }

    public static void main(String[] args) {
        Problem432_Order1 obj = new Problem432_Order1();
        obj.inc("hello");
        obj.inc("hello");
        obj.getMaxKey();
        obj.getMinKey();
        obj.inc("leet");
        obj.getMaxKey();
        obj.getMinKey();
    }
}
