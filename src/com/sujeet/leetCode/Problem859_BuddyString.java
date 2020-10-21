package com.sujeet.leetCode;

public class Problem859_BuddyString {
    public boolean buddyStrings(String A, String B) {
        if(A.length() != B.length())
            return false;
        Character mismatchA = null;
        Character mismatchB = null;

        for(int idx = 0; idx < A.length(); idx++) {
            if(A.charAt(idx) != B.charAt(idx)) {
                if(mismatchA == null) {
                    mismatchA = A.charAt(idx);
                    mismatchB = B.charAt(idx);
                } else {
                    if(mismatchB != A.charAt(idx) || mismatchA != B.charAt(idx))
                        return false;
                    return A.substring(idx+1).equals(B.substring(idx+1));
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Problem859_BuddyString obj = new Problem859_BuddyString();
        obj.buddyStrings("ab", "ba");
    }
}
