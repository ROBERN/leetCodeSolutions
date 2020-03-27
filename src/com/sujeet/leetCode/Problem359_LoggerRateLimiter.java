package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem359_LoggerRateLimiter {
    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public Problem359_LoggerRateLimiter() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(map.containsKey(message)) {
            int lastTime = map.get(message);
            if(lastTime+10 > timestamp) {
                return false;
            }
        }
        map.put(message, timestamp);
        return true;
    }
}
