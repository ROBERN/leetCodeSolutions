package com.sujeet.leetCode;

import java.util.List;

public class problem364_NestedWeight {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int height = getDepth(nestedList, 0);
        return getWeightedSum(nestedList, height);
    }

    private int getWeightedSum(List<NestedInteger> nestedList, int height)  {
        int sum =0;
        for(NestedInteger ns : nestedList) {
            if(ns.isInteger()) {
                sum += ns.getInteger()*height;
            } else{
                sum += getWeightedSum(ns.getList(), height-1);
            }
        }
        return sum;
    }

    private int getDepth(List<NestedInteger> nestedList, int depth) {
        int maxDepth = depth;
        for(NestedInteger ns : nestedList) {
            if(!ns.isInteger()) {
                maxDepth = Math.max(maxDepth, getDepth(ns.getList(), depth+1));
            } else{
                maxDepth = Math.max(maxDepth, depth+1);
            }
        }
        return maxDepth;
    }
}
