package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.*;

public class Problem403_FrogJump {
    private Map<Pair<Integer, Integer>, Boolean> jump;
    private Set<Integer> positions;
    public boolean canCross(int[] stones) {
        if(stones.length == 0)
            return true;
        positions = new HashSet<>();
        for (int stone : stones)
            positions.add(stone);
        jump = new HashMap<>();
        return canCross(stones, 0, 0);
    }

    public boolean canCross(int[] stones, int pos, int k) {
        int len = stones.length;
        if(!positions.contains(pos)) {
            return false;
        }
        if(pos == stones[len-1]) {
            return true;
        }
        Pair<Integer, Integer> key = new Pair<>(pos, k);
        if(jump.containsKey(key)) {
            return jump.get(key);
        }
        jump.put(key, false);
        jump.put(key,
                canCross(stones, pos+k-1, k-1) ||
                        canCross(stones, pos+k, k) ||
                        canCross(stones, pos+k+1, k+1)
        );

        return jump.get(key);
    }

    public static void main(String[] args) {
        Problem403_FrogJump obj = new Problem403_FrogJump();
        System.out.println(obj.canCross(new int[]{0,1,3,5,6,8,12,17}));
    }
}
