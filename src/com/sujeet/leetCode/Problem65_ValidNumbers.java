package com.sujeet.leetCode;

public class Problem65_ValidNumbers {
    public boolean isNumber(String s) {
        s = trimSpaces(s);
        String[] parts = s.split("e");
        if (eCount(s) > 1)
            return false;
        if (parts.length > 2 || parts.length == 0) {
            return false;
        }
        if (s.contains("e") && (parts.length != 2 || (s.length() != parts[0].length()+parts[1].length()+1)))
            return false;
        if (parts.length == 2 && !allNumbers(parts[1], false)) {
            return false;
        }
        s = parts[0];
        if(s.length() == 0)
            return false;
        int idx = 0;
        if(s.charAt(idx) == '-' || s.charAt(idx) == '+')
            idx++;
        if(idx == s.length())
            return false;
        boolean seenNumber = false;
        while(idx < s.length()) {
            char c = s.charAt(idx);
            if(c < '0' || c > '9') {
                if (c == '.') {
                    return allNumbers(s.substring(idx+1), seenNumber);
                } else{
                    return false;
                }
            } else {
                seenNumber = true;
            }
            idx++;
        }
        return true;
    }

    private boolean allNumbers(String s, boolean allowZeroSize) {
        if(0 == s.length() && !allowZeroSize)
            return false;
        int stIdx= 0;
        if(s.charAt(stIdx) == '-' || s.charAt(stIdx) == '+' )
            stIdx++;
        if(stIdx == s.length())
            return false;
        for(int idx = stIdx; idx < s.length(); idx++) {
            if(s.charAt(idx) < '0' || s.charAt(idx) > '9') {
                return false;
            }
        }
        return true;
    }

    private String trimSpaces(String s) {
        int idx = 0;
        while(idx < s.length() && s.charAt(idx) == ' ')
            idx++;
        int idx1 = s.length()-1;
        while(idx1 >= 0 && s.charAt(idx1) == ' ')
            idx1--;
        if(idx1 < idx)
            return "";
        s = s.substring(idx, idx1+1);
        return s;
    }

    private boolean allSpaces(String s, int stIdx) {
        int idx = stIdx;
        while(idx < s.length() && s.charAt(idx) == ' ')
            idx++;
        return idx == s.length();
    }

    private int eCount(String s) {
        int count = 0;
        for (char c : s.toCharArray()){
            if (c == 'e')
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Problem65_ValidNumbers obj = new Problem65_ValidNumbers();
        System.out.println(obj.isNumber("+3. e04116"));
    }
}
