package com.sujeet.leetCode;

public class Problem1342_numberOfSteps {
    public int numberOfSteps (int num) {
        int steps = 0;
        String binaryString = Integer.toBinaryString(num);
        for(char c : binaryString.toCharArray()) {
            if(c == '1') {
                steps += 2;
            } else{
                steps +=1;
            }
        }
        return steps-1;
    }
}
