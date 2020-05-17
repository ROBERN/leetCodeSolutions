package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem911_OnlineElection {
    private class Node {
        int count;
        int latestTime;
        Node(int c, int t) {
            count = c;
            latestTime = t;
        }
    }
    Map<Integer, Integer> timeToTopPerson = new HashMap<>();
    public Problem911_OnlineElection(int[] persons, int[] times) {
        TreeMap<Integer, Node> map = new TreeMap<Integer, Node>();

        for(int idx  = 0; idx < persons.length; idx++) {
            int person = persons[idx];
            int time = times[idx];
            Node node = null;
            if(map.containsKey(person)) {
                node = new Node(map.get(person).count+1, time);
            } else{
                node = new Node(1, time);
            }
        }
    }

    public int q(int t) {
        return timeToTopPerson.get(t);
    }
}
