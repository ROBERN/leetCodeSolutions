package com.sujeet.Practise;

import java.util.*;

public class GraphMap {

    private class Neighbour {
        int neigh;
        int weight;
        Neighbour(int n, int w) {
            neigh = n;
            weight = w;
        }
    }

    private class QueueNode {
        int node;
        int weightParent;
        QueueNode(int n, int w) {
            node = n;
            weightParent = w;
        }
    }

    int maxValue= 0;
    Map<Integer, List<Neighbour>> map = new HashMap<>();
    public int networkDelayTime(int[][] times, int N, int K) {
        for(int[] time : times) {
            List<Neighbour> list = map.getOrDefault(time[0], new ArrayList<Neighbour>());
            list.add(new Neighbour(time[1], time[2]));
            map.put(time[0], list);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<QueueNode> q = new LinkedList<>();
        q.add(new QueueNode(K, 0));
        visited.add(K);
        bfs(map, q, visited);
        return visited.size() < N ? -1 : maxValue;
    }

    private void bfs(Map<Integer, List<Neighbour>> graph, Queue<QueueNode> q, Set<Integer> visited) {
        while(!q.isEmpty()) {
            QueueNode item = q.poll();
            for(Neighbour neigh : graph.getOrDefault(item.node, new ArrayList<>())) {
                if(!visited.contains(neigh.neigh)) {
                    visited.add(neigh.neigh);
                    int newWeight = item.weightParent + neigh.weight;
                    q.add(new QueueNode(neigh.neigh, newWeight));
                    if(newWeight > maxValue) {
                        maxValue = newWeight;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphMap map = new GraphMap();
        map.networkDelayTime(new int[][]{
                {1,2,1},
                {2,3,2},
                {1,3,2}
        }, 3, 1);
    }
}
