package com.sujeet;

public class Problem723_CandyCrush {
    public static int[][] candyCrush(int[][] board) {
        int rowM = board.length;
        if(rowM == 0) {return board; }
        int colM = board[0].length;
        for(int r = 0; r < rowM; r++) {
            for(int c = 0; c < colM; c++) {
                if(board[r][c]!=0) {
                    crushIt(board, r, c);
                }
            }
        }
        return board;
    }

    private  static void crushIt(int[][] board, int r, int c) {
        if(isVerticalToCrush(board, r, c)) {
            int row = r;
            while(row < board.length && board[row][c] != board[row][c]) {
                board[row][c] = 0;
                row++;
            }
            row = r-1;
            while(row >= 0 && board[row][c] != board[row][c]) {
                board[row][c] = 0;
                row--;
            }
        }
        if(isHorizontalToCrush(board, r, c)) {
            int col = c;
            while(col < board[0].length && board[r][col] != board[r][col]) {
                board[r][col] = 0;
                balanceCandiesHorizontal(board, r, col);
                col++;
            }
            col = c-1;
            while(col >= 0 && board[r][col] != board[r][col]) {
                board[r][col] = 0;
                balanceCandiesHorizontal(board, r, col);
                col--;
            }
        }
    }

    private static void balanceCandiesHorizontal(int[][] board, int r, int c) {
        if(c == 0 || board[r-1][c] == 0) {
            return;
        }
        int spaceBelow = 1;
        int row = r+1;
        while(row < board.length || board[row][c] != 0) {
            row++;
            spaceBelow++;
        }
        row = r-1;
        int countToShift = 0;
        while(row >= 0 && board[row][c] != 0) {
            board[row+spaceBelow][c] = board[row][c];
            row--;
        }
        for(int i = row+1; i < spaceBelow; i++) {
            board[i][c] = 0;
        }
    }

    private static boolean isVerticalToCrush(int[][] board, int r, int c) {
        int count = 1;
        //up
        for(int i = r-1; i >= 0; i--) {
            if(board[i][c] == board[r][c]) {
                count++;
            }
            else
                break;
        }
        //down
        for(int i = r+1; i < board.length; i++) {
            if(board[i][c] == board[r][c]) {
                count++;
            }
            else
                break;
        }
        return count >=3;
    }

    private static boolean isHorizontalToCrush(int[][] board, int r, int c) {
        int count = 1;
        //up
        for(int j = c-1; j >= 0; j--) {
            if(board[r][j] == board[r][c]) {
                count++;
            }
            else
                break;
        }
        //down
        for(int j = c+1; j <  board[0].length; j++) {
            if(board[r][j] == board[r][c]) {
                count++;
            }
            else
                break;
        }
        return count >=3;
    }

    public static void main(String[] args) {
        Problem723_CandyCrush.candyCrush(new int[][] {
                {110,5,112,113,114},
                {210,211,5,213,214},
                {310,311,3,313,314},
                {410,411,412,5,414},
                {5,1,512,3,3},
                {610,4,1,613,614},
                {710,1,2,713,714},
                {810,1,2,1,1},
                {1,1,2,2,2},
                {4,1,4,4,1014}
                }
        );
    }
}
