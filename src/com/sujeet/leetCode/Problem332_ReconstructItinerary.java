package com.sujeet.leetCode;

import java.util.*;

public class Problem332_ReconstructItinerary {
    private static class Neigh {
        String neigh;
        int ticketIdx;
        Neigh(String n, int idx) {
            neigh = n;
            ticketIdx = idx;
        }
    }

    private Map<String, Set<Neigh>> graph;
    private List<String> topologicalSortedStrings;
    private boolean[] visited;
    private int visitCount;
    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<String, Set<Neigh>>();
        topologicalSortedStrings = new ArrayList<>();
        visited = new boolean[tickets.size()];
        visitCount = 0;

        constructGraph(tickets);
        topologicalSorting("JFK");

        Collections.reverse(topologicalSortedStrings); // since we want to start from lowest finsining time.
        return topologicalSortedStrings;
    }

    private void constructGraph(List<List<String>> tickets) {
        int idx = 0;
        for(List<String> ticket : tickets) {
            // check length == 2
            graph.computeIfAbsent(ticket.get(0), empty -> new TreeSet<>((a, b) -> {
                if (a.neigh.compareTo(b.neigh) != 0)
                    return a.neigh.compareTo(b.neigh);
                return a.ticketIdx - b.ticketIdx;
            })).add(new Neigh(ticket.get(1), idx));
            idx++;
        }
    }

    private void topologicalSorting(String st) {
        for(Neigh adj : graph.getOrDefault(st, new HashSet<>())) {
            dfs(adj);
        }
        topologicalSortedStrings.add(st);
    }

    private void dfs(Neigh node) {
        if(visitCount == visited.length || visited[node.ticketIdx])
            return;

        visited[node.ticketIdx] = true;
        visitCount++;
        String curr = node.neigh;
        for(Neigh adj : graph.getOrDefault(curr, new HashSet<>())) {
            if(!visited[adj.ticketIdx])
                dfs(adj);
        }
        topologicalSortedStrings.add(curr);
        if(visitCount == visited.length)
            return;
        topologicalSortedStrings.remove(curr);
        visited[node.ticketIdx] = false;
        visitCount--;
    }
}
