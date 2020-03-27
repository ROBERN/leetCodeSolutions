package com.sujeet.leetCode;

import java.util.*;

class Count {
    int val;
    char c;
    Count(int v, char ch) {
        val = v;
        c = ch;
    }
}
public class Problem621_TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        for (char c : tasks) {
            counts[c - 'A']++;
        }

        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0)
                q.add(counts[i]);
        }

        int time = 0;
        while (!q.isEmpty()) {
            List<Integer> outerList = new ArrayList<>();
            int iter = 0;
            while (iter <= n) {
                if (!q.isEmpty()) {
                    outerList.add(q.poll() - 1);
                }
                time++;
                if (outerList.size() == 0 && q.isEmpty())
                    break;
                iter++;
            }
            if (outerList.size() > 0) {
                for (int listVal : outerList) {
                    if (listVal > 0)
                        q.add(listVal);
                }
            }
        }
        return time;

    }

    public static void main(String[] args) {
        System.out.println(Problem621_TaskScheduler.leastInterval(new char[]{
                'A','A','B','B','C','C','D','D','E','E','F','F','G','G','H','H','I','I','J','J','K','K','L','L','M','M','N','N','O','O','P','P','Q','Q','R','R','S','S','T','T','U','U','V','V','W','W','X','X','Y','Y','Z','Z'
        }, 2));
    }
}
