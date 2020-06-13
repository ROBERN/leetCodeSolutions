package com.sujeet.leetCode;

public class Problem60_PermuteSeq {
    public String getPermutation(int n, int k) {
        if(n == 1 || n == 0)
            return Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(i+1);
        return permutation(sb.toString(), k);
    }

    public String permutation(String s, int k) {
        if(s.length() == 0)
            return "";
        if(k == 0) {
            return sortString(s);
        }
        int len = s.length();
        int nextPermCount = getFactorial(len-1);
        int count = 0;
        int idxToConsiderFirst = 0;
        while(nextPermCount + count < k) {
            count +=nextPermCount;
            idxToConsiderFirst++;
        }

        return s.charAt(idxToConsiderFirst) + permutation(s.substring(0, idxToConsiderFirst) + s.substring(idxToConsiderFirst+1), k-count);
    }

    private String sortString(String s) {
        return s.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private int getFactorial(int n) {
        int ans = 1;
        while(n > 1){
            ans *= n;
            n--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem60_PermuteSeq obj = new Problem60_PermuteSeq();
        System.out.println(obj.getPermutation(3, 3));
    }
}
