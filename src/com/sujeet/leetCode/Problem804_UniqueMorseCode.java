package com.sujeet.leetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem804_UniqueMorseCode {
    public int uniqueMorseRepresentations(String[] words) {
        String[] values= new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> morseCode = new HashSet<>();
        for(String word : words) {

            StringBuilder code = new StringBuilder();
            for(char c : word.toCharArray()) {
                code.append(values[c-'a']);
            }
            String str = code.toString();
            System.out.println(str);
            morseCode.add(str);
        }

        return morseCode.size();
    }

    public static void main(String[] args) {
        Problem804_UniqueMorseCode obj = new Problem804_UniqueMorseCode();
         System.out.println(obj.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }
}
