package com.sujeet.leetCode;

public class Problem418_ScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int totalLength = 0;
        int r = 0;
        int c  = 0;
        int idx = 0;

        int counts = 0;
        while(r < rows) {
            if(idx == sentence.length) {
                counts++;
                idx = 0;
            }
            if(sentence[idx].length() + c < cols) {
                c += sentence[idx].length()+1;
            } else {
                r++;
                System.out.println();
                c = sentence[idx].length()+1;
            }
            System.out.print(sentence[idx]+ " ");
            idx++;
        }
        if(idx == sentence.length)
            counts++;

        return counts;
    }

    public static void main(String[] args) {
        Problem418_ScreenFitting obj = new Problem418_ScreenFitting();
        obj.wordsTyping(new String[]{
                "f","p","a"
        }, 8, 7);
    }
}
