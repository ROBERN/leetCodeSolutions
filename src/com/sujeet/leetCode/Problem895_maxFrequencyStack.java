package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem895_maxFrequencyStack {
    private Map<Integer, Integer> freqOfItems;
    private Map<Integer, Stack<Integer>> stackAtFrequencyMap;
    private int maxFreq;
    public Problem895_maxFrequencyStack() {
        freqOfItems = new HashMap<>();
        stackAtFrequencyMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int freq = freqOfItems.getOrDefault(x, 0)+1;
        freqOfItems.put(x, freq);
        stackAtFrequencyMap.computeIfAbsent(freq, st -> new Stack<>()).push(x);
        maxFreq = Math.min(maxFreq, freq);
    }

    public int pop() {
        int popedValue = stackAtFrequencyMap.get(maxFreq).pop();
        if (stackAtFrequencyMap.get(maxFreq).size() == 0) {
            maxFreq--;
        }
        freqOfItems.put(popedValue,  freqOfItems.get(popedValue)-1);
        return popedValue;
    }
}
