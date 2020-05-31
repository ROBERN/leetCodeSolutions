package com.sujeet.leetCode;

import java.util.*;

public class Problem332_ReorderItinerary {

    // represents the edge : 'to' vertex and index where this edge is w.r.t original ticketList
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
    private boolean[] visited; //w.r.t ticketList
    private int visitCount; // we want to break when we have seen all the tickets.
    private List<String> findItinerary(List<List<String>> tickets) {
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
            // the comparison for both neigh and index is important, since you may have "JFK", "SFO", "JFK", "ALT", "JFK, "SFO" - so SFO should be added twice, without index comparison, it will be added once (since comparison will return 0).
            graph.computeIfAbsent(ticket.get(0), empty -> new TreeSet<>((a, b) -> {
                if (a.neigh.compareTo(b.neigh) != 0)
                    return a.neigh.compareTo(b.neigh);
                return a.ticketIdx - b.ticketIdx;
            })).add(new Neigh(ticket.get(1), idx));
            idx++;
        }
    }
    // driver of the DFS, since we want to start from JFK always,
    private void topologicalSorting(String st) {
        for(Neigh adj : graph.getOrDefault(st, new HashSet<>())) {
            dfs(adj);
        }
        // Add the JFK- its not yet added
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
            return; // we are done!!

        // backTrack if this route didn't work
        topologicalSortedStrings.remove(curr);
        visited[node.ticketIdx] = false;
        visitCount--;
    }
    public static void main(String[] args) {
        Problem332_ReorderItinerary obj =  new Problem332_ReorderItinerary();
        System.out.println(obj.findItinerary(Arrays.asList(
                Arrays.asList("EZE","AXA"),
                Arrays.asList("TIA","ANU"),
                Arrays.asList("ANU","JFK"),
                Arrays.asList("JFK","ANU"),
                Arrays.asList("ANU","EZE"),
                Arrays.asList("TIA","ANU"),
                Arrays.asList("AXA","TIA"),
                Arrays.asList("TIA","JFK"),
                Arrays.asList("ANU","TIA"),
                Arrays.asList("JFK","TIA")
        )));
    }
}
