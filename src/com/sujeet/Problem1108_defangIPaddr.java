package com.sujeet;

public class Problem1108_defangIPaddr {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if(c == '.') {
                sb.append('[');
                sb.append(c);
                sb.append(']');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
