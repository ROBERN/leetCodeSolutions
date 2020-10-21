package com.sujeet.leetCode;

public class Problem378_KthLargestMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;

        int start = matrix[0][0];
        int end = matrix[N-1][N-1];

        while(start < end) {
            int mid = start+(end-start)/2;

            int[] smallLargePair = {matrix[0][0], matrix[N-1][N-1]};
            int count = countLessEqual(matrix, mid, smallLargePair);

            if(count == k) {
                return smallLargePair[0];
            }
            if(count < k) {
                start = smallLargePair[1];
            } else {
                end = smallLargePair[0];
            }
        }
        return start;
    }

    private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int N = matrix.length;
        int row = N-1;
        int col = 0;

        while(row >= 0 && col < N) {
            if(matrix[row][col] > mid) {
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row+1;
                col++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Problem378_KthLargestMatrix obj = new Problem378_KthLargestMatrix();
        System.out.println(obj.kthSmallest(new int[][]{
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}
            }, 8)
        );
    }
}
