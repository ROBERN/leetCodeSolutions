package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem412_FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(i%3 == 0 || i %5 ==0) {
                StringBuilder sb = new StringBuilder();
                if(i%3 == 0)
                    sb.append("Fizz");
                if(i%5 == 0)
                    sb.append("Buzz");
                result.add(sb.toString());
            } else {
                result.add(Integer.toString(i));
            }
        }
        return result;
    }
}
