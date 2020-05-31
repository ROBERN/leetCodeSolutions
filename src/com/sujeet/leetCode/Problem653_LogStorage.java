package com.sujeet.leetCode;

import java.util.*;

public class Problem653_LogStorage {
    private static class LogNode implements Comparable<LogNode>{
        private int[] time;
        private int id;
        LogNode(int id, String log){
            this.id = id;
            time = new int[6];
            String[] logs = log.split(":");
            if(logs.length != 6)
                throw new RuntimeException();
            for(int i = 0; i < 6; i++) {
                time[i] = Integer.parseInt(logs[i]);
            }
        }

        @Override
        public int compareTo(LogNode log) {
            for(int i = 0; i < 6; i++) {
                if(log.time[i] != time[i]) {
                    return time[i] - log.time[i];
                }
            }
            return 0;
        }

        public int compareWithGranularity(LogNode log, String gran) {
            int granularity = 6;
            switch(gran) {
                case "Year":
                    granularity = 1;
                    break;
                case "Month":
                    granularity = 2;
                    break;
                case "Day":
                    granularity = 3;
                    break;
                case "Hour":
                    granularity = 4;
                    break;
                case "Minute":
                    granularity = 5;
                    break;
                default:
                    granularity = 6;
            }
            for(int i = 0; i < granularity; i++) {
                if(log.time[i] != time[i]) {
                    return time[i] - log.time[i];
                }
            }
            return 0;

        }
    }

    TreeSet<LogNode> storage;
    public Problem653_LogStorage() {
        storage = new TreeSet<>();
    }

    public void put(int id, String timestamp) {
        storage.add(new LogNode(id, timestamp));
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        LogNode end = new LogNode(0, e);
        LogNode start = new LogNode(0, s);
        List<Integer> result = new ArrayList<>();
        for (LogNode node : storage) {
            if (node.compareWithGranularity(end, gra) > 0)
                break;
            if (node.compareWithGranularity(start, gra) >= 0 && node.compareWithGranularity(end, gra) <= 0) {
                result.add(node.id);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem653_LogStorage obj= new Problem653_LogStorage();
        obj.put(1, "2017:01:01:23:59:59");
        obj.put(2, "2017:01:01:22:59:59");
        obj.put(3, "2016:01:01:00:00:00");

        System.out.println(obj.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"));
        System.out.println(obj.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"));
    }

}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 *
 * ["LogSystem","put","put","put","retrieve","retrieve"]
 * [[],[1,"2017:01:01:23:59:59"],[2,"2017:01:01:22:59:59"],[3,"2016:01:01:00:00:00"],["2016:01:01:01:01:01","2017:01:01:23:00:00","Year"],["2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"]]
 */
