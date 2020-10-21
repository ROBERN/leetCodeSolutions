package com.sujeet.leetCode;

import java.util.*;

public class Problem381_RandomizedCollection {
    private List<Integer> nums;
    private Random rand;
    private HashMap<Integer, Set<Integer>> index;
    /** Initialize your data structure here. */
    public Problem381_RandomizedCollection() {
        nums = new ArrayList<>();
        rand = new Random();
        index = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        int idx = nums.size()-1;
        if (index.containsKey(val)) {
            index.get(val).add(idx);
            return false;
        } else{
            index.computeIfAbsent(val, empty -> new HashSet<>()).add(idx);
            return true;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!index.containsKey(val)) {
            return false;
        }

        int removeIndex = index.get(val).iterator().next();
        index.get(val).remove(removeIndex);
        if (index.get(val).size() == 0)
            index.remove(val);
        int lastIndex = nums.size()-1;
        int lastVal = nums.get(lastIndex);
        if (lastIndex != removeIndex) {
            index.get(lastVal).remove(lastIndex);
            index.get(lastVal).add(removeIndex);
        }
        nums.set(removeIndex, lastVal);
        nums.remove(lastIndex);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }

    public static void main(String[] args) {
        Problem381_RandomizedCollection obj = new Problem381_RandomizedCollection();
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(1));
        System.out.println(obj.insert(1));

    }
}
