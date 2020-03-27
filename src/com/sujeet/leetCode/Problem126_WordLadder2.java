package com.sujeet.leetCode;

import java.util.*;

public class Problem126_WordLadder2 {
    class Node {
        Node parent;
        int distance;
        String val;

        Node(Node p, int d, String v) {
            parent = p;
            distance = d;
            val = v;
        }
    }

    private List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        int len = beginWord.length();
        if (endWord.length() != len) return result;
        //beginWord: hit -> *it, h*t, hi*. But do it for all words to make a graph.

        Map<String, List<String>> mappedFromGeneric = new HashMap<>();
        for (String word : wordList) {
            if (word.length() != len) continue;

            for (int i = 0; i < len; i++) {
                String genericKey = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> mappedSpecifics = mappedFromGeneric.getOrDefault(genericKey, new ArrayList<>());
                mappedSpecifics.add(word);
                mappedFromGeneric.put(genericKey, mappedSpecifics);
            }
        }


        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>(wordList.size());
        visited.add(beginWord);
        q.add(new Node(null, 1, beginWord));
        boolean found = false;
        while (!q.isEmpty()) {
            Queue<Node> q1 = new LinkedList<>();
            while (!q.isEmpty()) {
                Node node = q.poll();
                int distance = node.distance;
                String word = node.val;
                for (int i = 0; i < len; i++) {
                    String genericKey = word.substring(0, i) + "*" + word.substring(i + 1, len);
                    List<String> mappedSpecifics = mappedFromGeneric.getOrDefault(genericKey, new ArrayList<>());
                    for (String specific : mappedSpecifics) {
                        if (specific.equals(endWord)) {
                            found = true;
                            result.add(getPathToEnd(endWord, node));
                        } else if(!visited.contains(specific)) {
                            q1.add(new Node(node, distance + 1, specific));
                        }
                    }
                }
            }
            for(Node node : q1){
                visited.add(node.val);
            }
            if (found) {
                break;
            }
            q = q1;
        }
        return result;
    }

    List<String> getPathToEnd(String endWord, Node node) {
        List<String> path = new ArrayList<>();
        path.add(endWord);
        while (node != null) {
            path.add(node.val);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Problem126_WordLadder2 sol = new Problem126_WordLadder2();
        System.out.println(
        sol.findLadders("red", "tax",
                        Arrays.asList("ted","tex","red","tax","tad","den","rex","pee")
        )
        );
    }
}
