package com.sujeet.leetCode;

public class Problem765_CouplesHoldingHands {

    private int[][] couples;
    int N;
    public int minSwapsCouples(int[] row) {
        N = row.length/2;

        couples = new int[N][2];
        for(int i = 0; i < N; i++) {
            couples[i][0] = row[2*i]/2;
            couples[i][1] = row[2*i+1]/2;
        }

        return computeMinSwap(0);
    }

    private int computeMinSwap(int idx) {
        if(idx == N)
            return 0;
        if(couples[idx][0] == couples[idx][1])
            return computeMinSwap(idx+1);

        int swapIdx1 = -1;
        int xyPos1 = - 1;
        int swapIdx2 = -1;
        int xyPos2 = - 1;
        for(int nextIdx = idx+1; nextIdx < N; nextIdx++) {
            for(int xy = 0; xy <= 1; xy++) {
                if(couples[nextIdx][xy] == couples[idx][0]) {
                    swapIdx1 = nextIdx;
                    xyPos1 = xy;
                }
                if(couples[nextIdx][xy] == couples[idx][1]) {
                    swapIdx2 = nextIdx;
                    xyPos2 = xy;
                }
            }
        }

        swap(idx, 1, swapIdx1, xyPos1);
        int ans1 = 1+computeMinSwap(idx+1);
        swap(idx, 1, swapIdx1, xyPos1);

        swap(idx, 0, swapIdx2, xyPos2);
        int ans2 = 1+computeMinSwap(idx+1);
        swap(idx, 0, swapIdx2, xyPos2);

        return Math.min(ans1, ans2);
    }

    public void swap(int idx1, int xy1, int idx2, int xy2) {
        int temp = couples[idx1][xy1];
        couples[idx1][xy1] = couples[idx2][xy2];
        couples[idx2][xy2] = temp;
    }

    private int findIndex(int stIdx, int val, boolean isXSearch) {
        for(int idx = stIdx; idx < N; idx++) {
            if(isXSearch && couples[idx][0] == val)
                return idx;
            if(!isXSearch && couples[idx][1] == val)
                return idx;
        }
        return -1;
    }

    public static void main(String[] args) {
        Problem765_CouplesHoldingHands obj =  new Problem765_CouplesHoldingHands();
        System.out.println(obj.minSwapsCouples(new int[]{3, 2, 0, 1}));
    }
}
