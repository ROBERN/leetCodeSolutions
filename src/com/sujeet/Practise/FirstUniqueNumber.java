package com.sujeet.Practise;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FirstUniqueNumber {
    private class Node {
        int idx;
        int val;
        Node(int i, int v) {
            idx = i;
            val = v;
        }
    }
    private Map<Integer, Node> mapToIdx;
    private TreeSet<Node> set;
    int idx = 0;
    public FirstUniqueNumber(int[] nums) {
        set = new TreeSet<>(Comparator.comparingInt(a -> a.idx));
        mapToIdx = new HashMap<>();

        for(int num : nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
        if (set.size() == 0)
            return -1;
        return set.first().val;
    }

    public void add(int value) {
        if(!mapToIdx.containsKey(value)) {
            Node node = new Node(idx, value);
            mapToIdx.put(value, node);
            idx++;
            set.add(node);
        } else {
            set.remove(mapToIdx.get(value));
        }
    }

    public static void main(String[] args) {
        FirstUniqueNumber obj = new FirstUniqueNumber(new int[]{2,3,5});
        obj.showFirstUnique();
        obj.add(5);
        obj.showFirstUnique();
        obj.add(2);
        obj.showFirstUnique();
        obj.add(3);
        obj.showFirstUnique();
    }
}
