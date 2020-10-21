package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class Problem302_SmallestRectPixel {
    private static class Boundaries {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
    }

    public int minArea(char[][] image, int x, int y) {
        if(image.length == 0)
            return 0;
        Boundaries bounds = new Boundaries();
        Deque<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(x, y));
        image[x][y] = '2';
        updateBounds(bounds, x, y);

        while(!queue.isEmpty()) {
            Deque<Pair<Integer, Integer>> tempQueue = new LinkedList<>();

            while(!queue.isEmpty()) {
                Pair<Integer, Integer> pr = queue.poll();
                addIfValid(pr.getKey()+1, pr.getValue(), bounds, image, tempQueue);
                addIfValid(pr.getKey()-1, pr.getValue(), bounds, image, tempQueue);
                addIfValid(pr.getKey(), pr.getValue()+1, bounds, image, tempQueue);
                addIfValid(pr.getKey(), pr.getValue()-1, bounds, image, tempQueue);
            }
            queue = tempQueue;
        }
        return (bounds.maxRow-bounds.minRow+1)*(bounds.maxCol-bounds.minCol+1);
    }

    private void addIfValid(int r, int c, Boundaries bounds, char[][] image, Deque<Pair<Integer, Integer>> queue) {
        if(r >= image.length || c >= image[0].length || r < 0 || c < 0 || image[r][c] != '1')
            return;
        queue.add(new Pair<>(r, c));
        image[r][c] = '2';
        updateBounds(bounds, r, c);
    }

    private void updateBounds(Boundaries bounds, int x, int y) {
        bounds.minRow = Math.min(bounds.minRow, x);
        bounds.maxRow = Math.max(bounds.maxRow, x);
        bounds.minCol = Math.min(bounds.minCol, y);
        bounds.maxCol = Math.max(bounds.maxCol, y);
    }
}
