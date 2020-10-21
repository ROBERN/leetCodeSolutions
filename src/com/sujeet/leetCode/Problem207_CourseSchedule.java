package com.sujeet.leetCode;

import java.util.*;

public class Problem207_CourseSchedule {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for(int[] preReq : prerequisites) {
            graph.computeIfAbsent(preReq[0], p -> new ArrayList<>()).add(preReq[1]);
        }
        for(Map.Entry<Integer,List<Integer>> node : graph.entrySet()) {
            if(hasCycles(graph, node.getKey()))
                return false;
        }
        return true;
    }

    private Set<Integer> visited = new HashSet<>();
    private Set<Integer> visiting = new HashSet<>();
    private boolean hasCycles(Map<Integer, List<Integer>> graph, int nodeVal) {
        if(visiting.contains(nodeVal)) {
            return true;
        }
        if(visited.contains(nodeVal)) {
            return false;
        }
        visiting.add(nodeVal);
        if(graph.containsKey(nodeVal)) {
            for(int adjacent : graph.get(nodeVal)) {
                if(hasCycles(graph, adjacent)) {
                    return true;
                }
            }
        }
        visiting.remove(nodeVal);
        visited.add(nodeVal);
        return false;
    }
}
