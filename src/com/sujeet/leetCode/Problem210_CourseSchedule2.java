package com.sujeet.leetCode;

import java.util.*;

public class Problem210_CourseSchedule2 {
    private List<Integer> results;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        results = new ArrayList<>();
        Map<Integer, List<Integer>> graph = createGraph(prerequisites);

        for(int course = 0; course < numCourses; course++){
            if(!dfsWithValidity(graph, course))
                return new int[0];
        }

        int[] ret = new int[results.size()];
        int idx = 0;
        for(int res : results) {
            ret[idx++] = res;
        }
        return ret;
    }
    private Set<Integer> visited = new HashSet<>();
    private Set<Integer> visiting = new HashSet<>();

    private boolean dfsWithValidity(Map<Integer, List<Integer>> graph, int course) {
        if(visited.contains(course)) {
            return true;
        }
        if(visiting.contains(course)){
            return false;
        }
        visiting.add(course);
        if(graph.containsKey(course)) {
            for(int courseNext : graph.get(course)) {
                if(!dfsWithValidity(graph, courseNext)) {
                    return false;
                }
            }
        }
        results.add(course);
        visiting.remove(course);
        visited.add(course);
        return true;
    }

    private Map<Integer, List<Integer>> createGraph(int[][] prerequisites) {
        Map<Integer, List<Integer>>  graph = new HashMap<>();
        for(int[] preReq : prerequisites) {
            graph.computeIfAbsent(preReq[0], empty -> new ArrayList<>()).add(preReq[1]);
        }
        return graph;
    }
}
