package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.*;

public class Problem675_CutTreesForGolf {
    private static class TreeNode {
        int height;
        int r;
        int c;
        TreeNode(int h, int r1, int c1) {
            height = h;
            r = r1;
            c = c1;
        }
    }

    private static class QueueNode {
        int row;
        int col;
        int time;
        QueueNode(int r, int c, int t) {
            row = r;
            col = c;
            time = t;
        }
    }

    public int cutOffTree(List<List<Integer>> forest) {
        List<TreeNode> nodes = getSortedListOfTreeNodes(forest);
        int totalWalk = 0;

        int currX = 0;
        int currY = 0;

        for(TreeNode node : nodes) {
            int walk = getMinDistance(forest, currX, currY, node.r, node.c);
            if(walk == -1)
                return -1;
            totalWalk += walk;
            currX = node.r;
            currY = node.c;
        }
        return totalWalk;
    }

    private int getMinDistance(List<List<Integer>> forest, int sr, int sc, int dr, int dc) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();// replace pair with point to improve readability.
        Queue<QueueNode> q = new LinkedList<>();
        QueueNode srcNode = new QueueNode(sr, sc, 0);
        q.add(srcNode);

        while(!q.isEmpty()) {
            QueueNode point = q.poll();
            int currRow = point.row;
            int currCol = point.col;
            int currTime = point.time;
            if(currRow == dr && currCol == dc) return point.time;
            addToQueueNodeIfValid(forest, currRow+1, currCol, visited, currTime+1, q);
            addToQueueNodeIfValid(forest, currRow-1, currCol, visited, currTime+1, q);
            addToQueueNodeIfValid(forest, currRow, currCol+1, visited, currTime+1, q);
            addToQueueNodeIfValid(forest, currRow, currCol-1, visited, currTime+1, q);
        }
        return -1;
    }

    private void addToQueueNodeIfValid(List<List<Integer>> forest, int r, int c,  Set<Pair<Integer, Integer>> visited, int time, Queue<QueueNode> q) {
        if(r < forest.size() && r >= 0 && c < forest.get(0).size() && c >= 0 && !visited.contains(new Pair<>(r, c)) && forest.get(r).get(c) != 0) {
            q.add(new QueueNode(r, c, time));
            visited.add(new Pair<>(r, c));
        }
    }

    private List<TreeNode> getSortedListOfTreeNodes(List<List<Integer>> forest) {
        List<TreeNode> treeNodes = new ArrayList<>();
        for(int rIdx = 0; rIdx < forest.size(); rIdx++) {
            for(int cIdx = 0; cIdx < forest.get(0).size(); cIdx++) {
                if(isTree(forest, rIdx, cIdx)) {
                    treeNodes.add(new TreeNode(forest.get(rIdx).get(cIdx), rIdx, cIdx));
                }
            }
        }
        treeNodes.sort(Comparator.comparingInt(a -> a.height)); // Always cut off the tree with lowest height first
        return treeNodes;

    }

    private boolean isTree(List<List<Integer>> forest, int r, int c) {
        return forest.get(r).get(c) > 1;
    }

    public static void main(String[] args) {
        Problem675_CutTreesForGolf obj = new Problem675_CutTreesForGolf();
        System.out.println(obj.cutOffTree(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 4),
                Arrays.asList(7, 6, 5)
        )));
    }
}
