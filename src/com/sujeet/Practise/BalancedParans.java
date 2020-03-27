package com.sujeet.Practise;

public class BalancedParans {
    /*
     * Complete the 'doit' function below.
     *
     * The function accepts STRING expression as parameter.
     */
    private static final String EXPRESSION_CANT_BE_BALANCED = "Can't be made balanced using reversals";
    public static void doit(String expression) {
        if(expression == null) { // as per verified test case, the expected value is case of null is 0
            System.out.println("0");
            return;
        }
        // a string of odd length can never be balanced.
        if(expression.length() %2 != 0) {
            System.out.println(EXPRESSION_CANT_BE_BALANCED);
            return;
        }
        int countOfExtraOpenBraces = 0; // tracks extra open braces when scanning the string
        int mismatchedExtraOpenBraces = 0; // tracks open braces already mismatched.
        for(char currChar : expression.toCharArray()) {
            if(currChar == '{') {
                countOfExtraOpenBraces++;
            } else { // '}'
                if(countOfExtraOpenBraces > 0) {
                    countOfExtraOpenBraces--;
                } else {
                    // this is not a balanced set. A closing brace with no open brace. So make it open brace
                    mismatchedExtraOpenBraces++;
                    countOfExtraOpenBraces++; // changed to open brace.
                }
            }
        }
        int swapNeeded = mismatchedExtraOpenBraces;
        // now lets try to change all extra open braces to a balanced set.
        // this needs the countOfExtraOpenBraces to be even and half of the braces can be made to balance with the other half
        // (can be done in several ways. e.g. of 4 ({{{{) : {{}} {}{})
        if(countOfExtraOpenBraces % 2 == 0) {
            swapNeeded += countOfExtraOpenBraces/2;
        } else {
            System.out.println(EXPRESSION_CANT_BE_BALANCED);
        }
        // if possible
        System.out.println(swapNeeded);
    }


    // As per Hacker rank : "{}{}{}{{{}}}" my test case produces EXPRESSION_CANT_BE_BALANCED. But I verified locally that it is 0.

    // hacker rank fails for
    // matrix.add(Arrays.asList(1,2,3, 4, 5));
    // matrix.add(Arrays.asList(1,2,3, 4, 5));
    // matrix.add(Arrays.asList(1,2,3, 4, 5));
    // matrix.add(Arrays.asList(1,2,3, 4, 5));
    // matrix.add(Arrays.asList(1,2,3, 4, 5));
    // but the answer is correct. Verified locally : 1 2 3 4 5, 5 5 5 5,  4 3 2 1,  1 1 1,  2 3 4, 4 4,  3 2,  2, 3
    public static void main(String[] args) {
        doit("{}{}{}{{{}}}");
    }
}
