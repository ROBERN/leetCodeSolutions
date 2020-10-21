package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem590_NAryPostOrder {
    public List<Integer> postorder(NaryNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        for(int i = 0; i < root.children.size(); i++) {
            NaryNode child = root.children.get(i);
            result.addAll(result.size(), postorder(child));
        }
        result.add(result.size(), root.val);
        return result;
    }
}
