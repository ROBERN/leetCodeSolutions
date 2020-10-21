package com.sujeet.leetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem37_SudokuSolver {

    private int n = 3;
    private int N = 9;

    private int[][] rows = new int[N][N+1];
    private int[][] cols = new int[N][N+1];
    private int[][] sudokuNum = new int[N][N+1];

    private char[][] board;
    private boolean isSudokuSolved;

    private void setSudokuTaken(int val, int r, int c) {
        rows[r][val]++;
        cols[c][val]++;
        int sudokuIdx = (r/n)*n + c/n;
        sudokuNum[sudokuIdx][val]++;
        board[r][c] = (char)(val + '0');
    }

    private boolean canPlaceNumber(int val, int r, int c) {
        int sudokuIdx = (r/n)*n + c/n;
        return rows[r][val] + cols[c][val] + sudokuNum[sudokuIdx][val] == 0;
    }

    private void backTrackNext(int r, int c) {
        int nextCol = (c+1)%N;
        int nextRow = (c == N-1) ? r+1 : r;
        backtrack(nextRow, nextCol);
    }

    private void removeSudokuNum(int val, int r, int c) {
        rows[r][val]--;
        cols[c][val]--;
        int sudokuIdx = (r/n)*n + c/n;
        sudokuNum[sudokuIdx][val]--;
        board[r][c] = '.';
    }

    private void backtrack(int r, int c) {
        if (r > 8) {
            isSudokuSolved = true;
            return;
        }
        if (board[r][c] == '.') {
            for(int v = 1; v <= 9; v++) {
                if (canPlaceNumber(v, r, c)){
                    setSudokuTaken(v, r, c);
                    backTrackNext(r, c);
                    if (!isSudokuSolved) {
                        removeSudokuNum(v, r, c);
                    }
                }
            }
        } else {
            backTrackNext(r, c);
        }
    }

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.isSudokuSolved = false;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char num = board[r][c];
                if (num != '.') {
                    int val = Character.getNumericValue(num);
                    setSudokuTaken(val, r, c);
                }
            }
        }

        backtrack(0, 0);
    }

    public static void main(String[] args) {
        Problem37_SudokuSolver obj = new Problem37_SudokuSolver();
        char[][] sudoku = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        obj.solveSudoku(sudoku);
        int count = 0 ;
        int stRow = 0;
        int stCol = 0;
        while(count < 81) {
            for (int r = stRow; r < stRow+3; r++) {
                for (int c = stCol; c < stCol+3; c++) {
                    System.out.print(sudoku[r][c] + " ");
                    count++;
                }
            }
            if (stCol != 6) {
                stCol = (stCol+3);
            } else {
                stCol = 0;
                stRow = (stRow+3);
            }
            System.out.println();
        }
    }

}
