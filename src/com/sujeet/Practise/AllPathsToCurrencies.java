package com.sujeet.Practise;

import com.sun.tools.javac.util.Pair;

import java.util.*;
public class AllPathsToCurrencies {
    public static class Edge {
        double edgeWeight;
        String neighbour;

        Edge(double edgeWeight, String neighbour) {
            this.edgeWeight = edgeWeight;
            this.neighbour = neighbour;
        }
    }

    private Map<String, List<Edge>> graph = new HashMap<>();

    public AllPathsToCurrencies(List<String> orderBook) {
        for (String order : orderBook) {
            String[] values = order.split(":");
            if (values.length != 3)
                throw new IllegalArgumentException();
            String[] currencies = values[0].split("-");
        }
    }

    private void createGraph(Map<String, Pair<Double, Double>> currencyMap) {
        for (Map.Entry<String, Pair<Double, Double>> entry : currencyMap.entrySet()) {
            String[] currencies = entry.getKey().split("-");
            String currency1 = currencies[0];
            String currency2 = currencies[1];

            double ask = entry.getValue().fst;
            double bid = entry.getValue().snd;

            graph.computeIfAbsent(currency1, val -> new ArrayList<>()).add(new Edge(bid, currency2));
            graph.computeIfAbsent(currency2, val -> new ArrayList<>()).add(new Edge(1/ask, currency1));
        }
    }


    private static class Store {
        List<String> paths; // USD, EUR, RUP  5.4 ;
        List<Double> fractions;
        Store() {
            paths = new ArrayList<>();
            fractions = new ArrayList<>();
        }
    }

    private Map<String, Double> currentBest = new HashMap<>();
    public List<Pair<List<String>, Double>> getAllSortedPaths(String start, String end) {
        Map<String, Store> storeMap = new HashMap<>();

        dfs(start, new HashSet<>(), storeMap, "", 0.0);

        return new ArrayList<>();// placeholder
    }

    private void dfs(String currentNode, Set<String> visited, Map<String, Store> storeMap, String path, double fraction) {
        if (!graph.containsKey(currentNode))
            return;

        if (currentBest.containsKey(currentNode)) {
            if (currentBest.get(currentNode) > fraction)
                return;
        }
        String newPath = path + currentNode;
        storeMap.get(currentNode).paths.add(newPath);
        storeMap.get(currentNode).fractions.add(fraction);
        visited.add(currentNode);
        // cycle happens, then check the value currently in the Store and update if its better.
        for (Edge edge : graph.get(currentNode)) {
            String neighCurrency = edge.neighbour;
            double nextFraction = fraction*edge.edgeWeight;
            dfs(edge.neighbour, visited, storeMap, newPath, nextFraction);
        }
    }

}
