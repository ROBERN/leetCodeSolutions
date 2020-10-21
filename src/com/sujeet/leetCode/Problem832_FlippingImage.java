package com.sujeet.leetCode;

public class Problem832_FlippingImage {
    public int[][] flipAndInvertImage(int[][] A) {
        for(int[] imageItem : A) {
            flipAndInvert(imageItem);
        }

        return A;
    }

    private void flipAndInvert(int[] imageItem) {
        int l = 0;
        int r = imageItem.length-1;

        while(l <= r) {
            flip(imageItem, l, r);
            invert(imageItem, l);
            if (l!=r) {
                invert(imageItem, r);
            }
            l++;
            r--;
        }
    }

    private void flip(int[] imageItem, int l, int r) {
        int temp = imageItem[l];
        imageItem[l] = imageItem[r];
        imageItem[r] = temp;
    }

    private void invert(int[] imageItem, int idx) {
        if(imageItem[idx] == 0) {
            imageItem[idx] = 1;
        } else {
            imageItem[idx] = 0;
        }
    }

    public static void main(String[] args) {
        Problem832_FlippingImage obj = new Problem832_FlippingImage();
        obj.flipAndInvertImage(new int[][]{{1,1,0},{1,0,1},{0,0,0}});
    }
}
