package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem1153_StringTransform {
    private boolean hasValidMaps = true;
    private Set<Character> visiting = new HashSet<>();
    private Set<Character> visited = new HashSet<>();
    public boolean canConvert(String str1, String str2) {
        if(str1.length() != str2.length())
            return false;
        Map<Character, Character> graph = createGraph(str1, str2);
        if(!hasValidMaps)
            return false;
        if(hasCycles(graph))
            return false;
        return true;
    }
    private Map<Character, Character> createGraph(String str1, String str2) {
        Map<Character, Character> graph = new HashMap<>();
        for(int idx = 0; idx < str1.length(); idx++) {
            char c1 = str1.charAt(idx);
            char c2 = str2.charAt(idx);
            if(graph.containsKey(c1) && graph.get(c1) != c2) {
                hasValidMaps = false;
                return graph;
            } else {
                graph.put(c1, c2);
            }
        }
        return graph;
    }

    private boolean hasCycles(Map<Character, Character> graph) {
        for(Character c : graph.keySet()) {
            if(!dfs(graph, c))
                return true;
        }
        return false;
    }

    private boolean dfs(Map<Character, Character> graph, char c) {
        if(visited.contains(c))
            return true;
        if(visiting.contains(c)) {
            return false;
        }
        visiting.add(c);
        if(graph.containsKey(c)) {
            if(!dfs(graph, graph.get(c))) {
                return false;
            }
        }
        visiting.remove(c);
        visited.add(c);
        return true;
    }

    public static void main(String[] args) {
        Problem1153_StringTransform obj = new Problem1153_StringTransform();
        obj.canConvert("aabcc", "ccdee");
    }
}
