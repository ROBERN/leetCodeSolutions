package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

class NaryNode {
    public int val;
    public List<NaryNode> children;

    public NaryNode() {}

    public NaryNode(int _val) {
        val = _val;
    }

    public NaryNode(int _val, List<NaryNode> _children) {
        val = _val;
        children = _children;
    }
};

public class Problem589_NAryPreOrder {
    public List<Integer> preorder(NaryNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        result.add(result.size(), root.val);
        for(int i = 0; i < root.children.size(); i++) {
            NaryNode child = root.children.get(i);
            result.addAll(result.size(), preorder(child));
        }

        return result;
    }
}
