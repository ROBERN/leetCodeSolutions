package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.*;

public class Problem692_topKFrequent {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCounts= new HashMap<>();

        for(String word : words) {
            int val = wordCounts.computeIfAbsent(word, count -> 0);
            wordCounts.put(word, val+1);
        }

        PriorityQueue<Pair<String,Integer>> maxHeap = new PriorityQueue<Pair<String,Integer>>((word1, word2) -> {
            if(!word1.getValue().equals(word2.getValue())) {
                return word2.getValue() - word1.getValue();
            }
            return word1.getKey().compareTo(word2.getKey());
        });

        for(Map.Entry<String,Integer> wordEntry : wordCounts.entrySet()) {
            Pair<String,Integer> heapNode = new Pair<>(wordEntry.getKey(), wordEntry.getValue());
            maxHeap.add(heapNode);
        }
        List<String> result = new ArrayList<>();
        while(!maxHeap.isEmpty()) {
            result.add(maxHeap.poll().getKey());
        }
        return result;
    }
}
