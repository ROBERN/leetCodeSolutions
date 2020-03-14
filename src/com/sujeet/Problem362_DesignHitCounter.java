package com.sujeet;

import java.util.Deque;
import java.util.LinkedList;

public class Problem362_DesignHitCounter {
    private int hitsInWindow;
    private Deque<HitCount> dq;

    static class HitCount {
        int timestamp;
        int count;
        HitCount(int t, int c) {
            this.timestamp = t;
            this.count = c;
        }
    }

    /** Initialize your data structure here. */
    public Problem362_DesignHitCounter() {
        hitsInWindow = 0;
        dq = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {

        // Resize queue
        while (!dq.isEmpty() && timestamp - dq.peek().timestamp > 299) {
            hitsInWindow -= dq.peek().count;
            dq.poll();
        }

        // Either a new timestamp or existing
        if (!dq.isEmpty() && dq.peekLast().timestamp == timestamp) {
            dq.getLast().count++;
            hitsInWindow++;
        } else {
            dq.offer(new HitCount(timestamp, 1));
            hitsInWindow++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        // Resize queue
        while (!dq.isEmpty() && timestamp - dq.peek().timestamp > 299) {
            hitsInWindow -= dq.peek().count;
            dq.poll();
        }
        return hitsInWindow;
    }
}
