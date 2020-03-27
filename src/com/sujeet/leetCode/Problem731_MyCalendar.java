package com.sujeet.leetCode;

import java.util.LinkedList;
import java.util.List;

public class Problem731_MyCalendar {
    private List<int[]> singles;
    private List<int[]> overlapped;
    public Problem731_MyCalendar() {
        singles = new LinkedList<>();
        overlapped = new LinkedList<>();
    }

    public boolean book(int start, int end) {
        int[] slot = new int[]{start, end};

        for (int[] overlapSlot : overlapped) {
            int[] overlap = overlapping(slot, overlapSlot);
            if(overlap.length != 0) {
                return false;
            }
        }

        for(int[] single : singles) {
            int[] overlap = overlapping(slot, single);
            if(overlap.length != 0) {
                overlapped.add(overlap);
            }
        }
        singles.add(new int[]{start, end});
        return true;
    }

    private int[] overlapping(int[] a, int[] b) {
        int maxStart = Math.max(a[0], b[0]);
        int minEnd = Math.min(a[1], b[1]);

        if(minEnd > maxStart) {
            return new int[]{maxStart, minEnd};
        }
        return new int[0];
    }

    public static void main(String[] args) {
        Problem731_MyCalendar obj = new Problem731_MyCalendar();
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(50, 60));
        System.out.println(obj.book(10, 40));
        System.out.println(obj.book(5, 15));
        System.out.println(obj.book(5, 10));
        System.out.println(obj.book(25, 55));
    }
}
