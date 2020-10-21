package com.sujeet.leetCode;

import java.util.*;

public class Problem399_EvaluateDivision {
    private static class MathNode {
        double weight;
        String s;
        MathNode(double w, String str) {
            weight = w;
            s = str;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<MathNode>> graph = createGraph(equations, values);

        double[] result = new double[queries.size()];
        int idx = 0;
        for(List<String> query : queries) {
            String a = query.get(0);
            String b = query.get(1);
            result[idx++] = getDist(a, b, graph, new HashSet<>());
        }
        return result;
    }

    private double getDist(String a, String b, Map<String, List<MathNode>> graph, Set<String> visited) {
        if(a.equals(b) && graph.containsKey(a))
            return 1.0;
        if(!graph.containsKey(a) || visited.contains(a)) {
            return -1.0;
        }
        visited.add(a);
        List<MathNode> neigh = graph.get(a);
        for(MathNode node: neigh) {
            double dist = node.weight*getDist(node.s, b, graph, visited);
            if(dist >= -.0001)
                return dist;
        }
        return -1.0;
    }

    private Map<String, List<MathNode>> createGraph(List<List<String>> equations, double[] values) {
        Map<String, List<MathNode>> graph = new HashMap<>();
        for(int idx = 0; idx < equations.size(); idx++) {
            String a = equations.get(idx).get(0);
            String b = equations.get(idx).get(1);
            double val = values[idx];
            double div = 1/val;
            graph.computeIfAbsent(a, list -> new ArrayList<>()).add(new MathNode(val, b));
            graph.computeIfAbsent(b, list -> new ArrayList<>()).add(new MathNode(div, a));
        }
        return graph;
    }
}
