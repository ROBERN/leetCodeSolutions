package com.sujeet.leetCode;

import java.util.*;

public class Problem981_TimeValMap {

        private static class Store {
            String val;
            int time;
            Store(String v, int t) {
                val = v;
                time = t;
            }
        }

        private Map<String, TreeSet<Store>> cache;
        /** Initialize your data structure here. */
        public Problem981_TimeValMap() {
            cache = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            cache.computeIfAbsent(key, empty ->
                    new TreeSet<>(Comparator.comparingInt(a -> a.time))
            ).add(new Store(value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!cache.containsKey(key)) {
                return "";
            }

            SortedSet<Store> set = cache.get(key).headSet(
                    new Store("dummy", timestamp), false
            );

            return set.size() > 0 ? set.last().val : "";
        }

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
}
