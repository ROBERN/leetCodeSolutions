package com.sujeet.leetCode;

public class Problem709_toLowerCase {
    public String toLowerCase(String str) {
        int toUpper = 'A' - 'a';
        int toLower = -1 * toUpper;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length(); i++) {
            char c = str.charAt(i);

            if(c >= 'A' && c <= 'Z') {
                sb.append((char)(c + toLower));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
