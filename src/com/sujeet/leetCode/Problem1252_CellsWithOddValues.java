package com.sujeet.leetCode;

public class Problem1252_CellsWithOddValues {
    public int oddCells(int n, int m, int[][] indices) {
        int[] rows = new int[n];
        int[] cols = new int[m];

        for(int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }
        int odds = 0;
        for(int r = 0 ; r < n; r++) {
            for(int c = 0 ; c < m; c++) {
                int count = rows[r] + cols[c];
                if(count % 2 == 1) {
                    odds++;
                }
            }
        }

        return odds;
    }
}
