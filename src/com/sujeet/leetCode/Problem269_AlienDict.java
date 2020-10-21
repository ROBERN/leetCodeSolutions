package com.sujeet.leetCode;

import java.util.*;

public class Problem269_AlienDict {
    private Map<Character, Set<Character>> graph = new HashMap<>();
    private Set<Character> charSet = new HashSet<>();
    private Set<Character> sortedChars = new HashSet<>();
    private boolean illegal = false;

    public String alienOrder(String[] words) {
        createGraph(words, 0, words.length-1, 0);
        // topologicalSort
        List<Character> sortedValues = topologicalSort();
        StringBuilder sb = new StringBuilder();
        for(int idx = sortedValues.size()-1; idx >= 0; idx--) {
            sb.append(sortedValues.get(idx));
        }
        if(!illegal) {
            for(char c : charSet) {
                if(!sortedChars.contains(c)) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    private List<Character> topologicalSort() {
        List<Character> result = new ArrayList<>();
        Set<Character> visited = new HashSet<>();
        for(char node : graph.keySet()) {
            if(!dfs(node, result, visited, new HashSet<>())) {
                illegal = true;
                return new ArrayList<>();
            }
        }
        return result;
    }

    private boolean dfs(Character node, List<Character> result, Set<Character> visited, Set<Character> visiting) {
        if(visited.contains(node))
            return true;
        if(visiting.contains(node))
            return false;
        visiting.add(node);
        for(char adj : graph.getOrDefault(node, new HashSet<Character>())) {
            if(!dfs(adj, result, visited, visiting))
                return false;
        }
        visiting.remove(node);
        visited.add(node);
        result.add(node);
        sortedChars.add(node);
        return true;
    }

    private void createGraph(String[] words, int st, int end, int col) {
        if(end <= st || end >= words.length)
            return;
        Set<Character> seenChars = new HashSet<>();
        int prevSeenIdx = 0;
        for(int idx = st; idx <= end; idx++) { // idx in wods from st to end
            if(col >= words[idx].length())
                continue;
            char currChar = words[idx].charAt(col);
            charSet.add(currChar);
            if (!seenChars.contains(currChar)) {
                for (char seen : seenChars) {
                    graph.computeIfAbsent(seen, set -> new HashSet<>()).add(currChar);
                }
            }
            if (words[prevSeenIdx].charAt(idx) != currChar)
                createGraph(words, prevSeenIdx, idx-1, col+1); // next recursive col creation. Will return early if 1 or less Strings.
            if (idx == end)
                createGraph(words, prevSeenIdx, idx, col+1); // next recursive col creation. Will return early if 1 or less Strings.
            prevSeenIdx = idx;
            seenChars.add(currChar); // should not already have it
        }
    }

    public static void main(String[] args) {
        Problem269_AlienDict obj = new Problem269_AlienDict();
        System.out.println(obj.alienOrder(new String[] {
                "zy",
                "zx"
        }));
    }
}
