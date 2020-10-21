package com.sujeet.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem933_NoOfRecentCalls {
    Deque<Integer> array = new LinkedList<>();
    public Problem933_NoOfRecentCalls() {

    }

    public int ping(int t) {
        array.addLast(t);
        while(array.getFirst() + 3000 < t) {
            array.removeFirst();
        }
        return array.size();
    }
}
