package com.sujeet.leetCode;

import java.util.*;

public class Problem444_SequenceReconstruction {
    public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int N = org.length;
        if (N != 0 && seqs.size() == 0)
            return false;
        int[] inDegrees = new int[N];
        int[] outDegrees = new int[N];

        List<Integer> sortedList = new ArrayList<>();

        Map<Integer, List<Integer>> graph = constructGraph(seqs, inDegrees, outDegrees);
        if (graph.size() == 0)
            return false;

        int root = getRoot(inDegrees); // we will use root to start out Topological sort
        if (root == -1)
            return false;
        Queue<Integer> bfsQ = new LinkedList<>();
        bfsQ.add(root);
        while (!bfsQ.isEmpty()) {
            int node = bfsQ.poll();
            if (!bfsQ.isEmpty()) {
                return false;
            }
            sortedList.add(node);
            for (int neigh : graph.getOrDefault(node, new ArrayList<>())) {
                inDegrees[neigh]--;
                if (inDegrees[neigh] == 0)
                    bfsQ.add(neigh);
            }
        }

        if (sortedList.size() != N)
            return false;

        for (int idx = 0; idx < N; idx++) {
            if (org[idx] != sortedList.get(idx))
                return false;
        }

        return true;
    }

    private static Map<Integer, List<Integer>> constructGraph(
            List<List<Integer>> seqs, int[] inDegrees, int[] outDegrees
    ) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        int N = inDegrees.length; // size of elements
        Set<Integer> visited = new HashSet<>();
        for (List<Integer> seq : seqs) {
            if (seq.size() == 0)
                continue;

//            if (seq.size() == 1 && N == 1) {
//                outDegrees[seq.get(0)] = 1;
//                localVisited.add(seq.get(0));
//            }
            if (seq.get(0) >= N || seq.get(0) < 1)
                return new HashMap<>();
            visited.add(seq.get(0));
            for (int idx = 1; idx < seq.size(); idx++) {
                if (seq.get(idx) > N || seq.get(idx) < 1)
                    return new HashMap<>();
                visited.add(seq.get(idx));

                graph.computeIfAbsent(seq.get(idx - 1), empty -> new ArrayList<>()).add(seq.get(idx));
                inDegrees[seq.get(idx)]++;
                outDegrees[seq.get(idx - 1)]++;
            }
        }
        if(visited.size() == inDegrees.length - 1) {
            return graph;
        }
        return new HashMap<>();

    }

    // return -1 if not valid
    private static int getRoot(int[] inDegrees) {
        int root = -1;
        for (int i = 1; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                if (root == -1)
                    root = i;
                else
                    return -1; // can't have two nodes with 0 indegrees for topological sort
            }
        }
        return root; // can be -1 if none of the the nodes have 0 indegree.
    }

    public static void main(String[] args) {
        System.out.println(Problem444_SequenceReconstruction.sequenceReconstruction(
                new int[]{1},
                //Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3))
                Arrays.asList(Arrays.asList(2))
        ));
    }
}

/**
 [1,2,3,4]
 [[1,2,4],[2,3,4],[3,2]]
 **/