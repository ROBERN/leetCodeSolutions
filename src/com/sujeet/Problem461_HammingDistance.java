package com.sujeet;

public class Problem461_HammingDistance {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
