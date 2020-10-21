package com.sujeet.leetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Problem179_LargestNum {
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        List<String> numStrings = Arrays.stream(nums).mapToObj(Integer::toString).sorted(new LargerNumberComparator())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (String num : numStrings)
            sb.append(num);

        if (sb.charAt(0) == '0' && sb.length() > 1) {
            int endIdx = 1;
            while(sb.charAt(endIdx) == '0' && endIdx < sb.length()-1) {
                endIdx++;
            }
            sb.delete(0, endIdx);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem179_LargestNum obj = new Problem179_LargestNum();
        System.out.println(obj.largestNumber(new int[]{0, 0, 0}));
    }
}
