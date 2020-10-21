package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.*;

public class Problem51_NQueen {
    private boolean[] colUsed;
    private boolean[] diagUsed;
    private boolean[] antiDiagUsed;
    private int N;
    private int[] queens;
    private List<List<String>> solution;
    public List<List<String>> solveNQueens(int n) {
        N = n;
        colUsed = new boolean[n];
        diagUsed = new boolean[4*n-1];
        antiDiagUsed = new boolean[2*n-1];
        queens = new int[n];
        solution = new ArrayList<>();
        backTrack(0);
        return solution;
    }
    private boolean isUnderAttack(int row, int col) {
        return(colUsed[col] || diagUsed[row-col+2*N] || antiDiagUsed[row+col]);
    }

    private void placeQueen(int row, int col) {
        queens[row]=col;
        colUsed[col] = true;
        diagUsed[row-col+2*N] = true;
        antiDiagUsed[row+col] = true;
    }

    private void removeQueen(int row, int col) {
        colUsed[col] = false;
        diagUsed[row-col+2*N] = false;
        antiDiagUsed[row+col] = false;
        queens[row]=0;
    }

    private void backTrack(int row) {
        for(int col = 0; col < N; col++) {
            if(!isUnderAttack(row, col)) {
                placeQueen(row, col);

                if(row==N-1)
                    addSolution();
                else
                    backTrack(row+1);
                removeQueen(row, col);
            }
        }
    }

    private void addSolution() {
        List<String> result = new ArrayList<>();
        for(int row = 0; row<N; row++) {
            int pos = queens[row];
            StringBuilder sb = new StringBuilder();
            for(int col = 0; col < N; col++) {
                if(col == pos) {
                    sb.append('Q');
                } else
                    sb.append('.');
            }
            result.add(sb.toString());
        }
        solution.add(result);
    }

    public static void main(String[] args) {
        Problem51_NQueen obj = new Problem51_NQueen();
        System.out.println(obj.solveNQueens(4));
    }
}
