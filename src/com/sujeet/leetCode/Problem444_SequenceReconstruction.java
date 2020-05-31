package com.sujeet.leetCode;

import java.util.*;

public class Problem444_SequenceReconstruction {

    private boolean[] visited;
    private boolean[] visiting;
    private Map<Integer, List<Integer>> graph;
    private List<Integer> sortedList;
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int N = org.length;
        if (N != 0 && seqs.size() == 0)
            return false;
        visited = new boolean[N+1];
        visiting = new boolean[N+1];
        int[] inDegrees = new int[N+1];
        int[] outDegrees = new int[N+1];
        graph = new HashMap<>();
        sortedList = new ArrayList<>();

        if (!constructGraph(seqs, inDegrees, outDegrees))
            return false;
        if(!validate(outDegrees))
            return false;

        int root = getRoot(inDegrees); // we will use root to start out Topological sort
        if(root == -1)
            return false;
        // now we construct the sequence and check if this is same as org
        if (topologicalSort(root))
            return false;
        Collections.reverse(sortedList);
        for(int i =1;i<=N; i++)
            if(!visited[i])
                return false;
        // compare
        if(sortedList.size() != N)
            return false;

        for(int idx = 0; idx < N; idx++) {
            if(org[idx] != sortedList.get(idx))
                return false;
        }

        return true;
    }

    private static boolean validate(int[] outDegrees) {
        int zeroOutDegreeCount = 0;
        for (int i = 1; i < outDegrees.length; i++)
            if (outDegrees[i] == 0)
                zeroOutDegreeCount++;
        return (zeroOutDegreeCount  == 1 || (zeroOutDegreeCount == 0 && outDegrees.length == 2));
    }

    private boolean topologicalSort(int node) {
        if(visited[node])
            return true;
        if (visiting[node])
            return false;
        visited[node] = true;
        visiting[node] = true;
        for(int adj : graph.getOrDefault(node, new ArrayList<>())) {
            if (topologicalSort(adj))
                return false;
        }
        visiting[node] = false;
        // insert into list
        sortedList.add(node);
        return true;
    }

    private boolean constructGraph(List<List<Integer>> seqs, int[] inDegrees, int[] outDegrees) {
        Set<Integer> localVisited = new HashSet<>();
        for(List<Integer> seq : seqs) {
            if (seq.size() == 1 && outDegrees.length == 2) {
                outDegrees[seq.get(0)] = 1;
                localVisited.add(seq.get(0));
            }
            for(int idx = 1; idx < seq.size(); idx++) {
                graph.computeIfAbsent(seq.get(idx-1), empty -> new ArrayList<>()).add(seq.get(idx));
                inDegrees[seq.get(idx)]++;
                outDegrees[seq.get(idx-1)]++;
                localVisited.add(seq.get(idx));
                localVisited.add(seq.get(idx-1));
            }
        }
        return  localVisited.size() == inDegrees.length-1;
    }

    // return -1 if not valid
    private static int getRoot(int[] indegrees) {
        int root = -1;
        for(int i = 1; i < indegrees.length; i++) {
            if(indegrees[i] == 0) {
                if(root == -1)
                    root = i;
                else
                    return -1; // can't have two nodes with 0 indegrees for topological sort
            }
        }
        return root; // can be -1 if none of the the nodes have 0 indegree.
    }

    public static void main(String[] args) {
        Problem444_SequenceReconstruction obj = new Problem444_SequenceReconstruction();
        System.out.println(obj.sequenceReconstruction(new int[] {1,2,3,4}, Arrays.asList(
                Arrays.asList(1,2,4),
                Arrays.asList(2,3,4),
                Arrays.asList(3,2)
        )));
    }
}

/**
 [1,2,3,4]
 [[1,2,4],[2,3,4],[3,2]]
 **/