package com.sujeet.leetCode;

import java.util.*;

public class Problem1086_HighFive {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> studentToScoreCountPair = new HashMap<>();

        for(int[] item : items) {
            studentToScoreCountPair.computeIfAbsent(item[0], empty -> new PriorityQueue<Integer>(Comparator.reverseOrder())).add(item[1]);
        }

        Map<Integer, Integer> studentToAvgScore = new HashMap<>();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : studentToScoreCountPair.entrySet()) {
            int student = entry.getKey();
            PriorityQueue<Integer> scores = entry.getValue();
            int count=0;
            int sum = 0;
            while (count < 5 && !scores.isEmpty()) {
                sum += scores.poll();
                count++;
            }
            int avg = sum/count;
            studentToAvgScore.put(student, avg);
        }

        int[][] result = new int[studentToAvgScore.size()][2];
        Set<Integer> studentsInTop = studentToAvgScore.keySet();
        int idx = 0;
        for(int[] item : items) {
            if (studentsInTop.contains(item[0])) {
                result[idx][0] = item[0];
                result[idx][1] = studentToAvgScore.get(item[0]);
                idx++;
                studentsInTop.remove(item[0]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem1086_HighFive obj = new Problem1086_HighFive();
        System.out.println(Arrays.deepToString(obj.highFive(new int[][]{{1, 91}, {1, 92}, {2, 93}, {2, 97}
        , {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100},
                // {2, 100}, {2, 76}
        })));
    }
}
