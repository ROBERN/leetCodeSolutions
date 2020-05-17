package com.sujeet.Practise;

import java.util.Arrays;

public class ProblemLimit {

    class LabeledItems {
        int label;
        int value;
        LabeledItems(int v, int l) {
            label = l;
            value = v;
        }
    }

    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        LabeledItems[] items = new LabeledItems[values.length];
        for(int i = 0; i < values.length; i++) {
            items[i] = new LabeledItems(values[i], labels[i]);
        }

        Arrays.sort(items, (a, b) -> b.value - a.value);
        int seen = 0;
        int sum = 0;
        for(int i = 0; i < items.length && seen<num_wanted ; i++) {
            if(items[i].label <= use_limit) {
                seen++;
                sum += items[i].value;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ProblemLimit problemLimit = new ProblemLimit();
        System.out.println(problemLimit.largestValsFromLabels(new int[] {5,4,3,2,1}, new int[]{1,3,3,3,2}, 3, 2));
    }
}
