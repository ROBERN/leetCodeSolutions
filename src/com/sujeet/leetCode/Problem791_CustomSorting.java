package com.sujeet.leetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Problem791_CustomSorting {
    public static String customSortString(String S, String T) {
        Map<Character, Integer> order = new HashMap<>();
        for (int idx = 0; idx < S.length(); idx++) {
            order.put(S.charAt(idx), idx);
        }
        Character[] Tchars = new Character[T.length()];
        int idx = 0;
        int endIdx = T.length() - 1;
        for (char c : T.toCharArray()) {
            if (order.containsKey(c)) {
                Tchars[idx++] = c;
            } else {
                Tchars[endIdx--] = c;
            }
        }
        Arrays.sort(Tchars, 0, idx, Comparator.comparingInt(order::get));
        StringBuilder sb = new StringBuilder();
        for (char c : Tchars) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem791_CustomSorting.customSortString(
                "hucw",
                "utzoampdgkalexslxoqfkdjoczajxtuhqyxvlfatmptqdsochtdzgypsfkgqwbgqbcamdqnqztaqhqanirikahtmalzqjjxtqfnh"
        );
    }
}
