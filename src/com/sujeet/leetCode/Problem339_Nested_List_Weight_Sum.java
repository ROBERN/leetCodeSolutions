package com.sujeet.leetCode;

import java.util.List;

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
    void add(NestedInteger ni);
}

public class Problem339_Nested_List_Weight_Sum {

    int sumD = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }
    public int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for(NestedInteger nested : nestedList) {
            if(nested.isInteger()) {
                sum += depth*nested.getInteger();
            } else{
                sum += depthSum(nested.getList(), depth+1);
            }
        }
        return sum;
    }
}
