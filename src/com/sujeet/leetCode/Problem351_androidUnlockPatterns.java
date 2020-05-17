package com.sujeet.leetCode;

import java.util.HashSet;

public class Problem351_androidUnlockPatterns {

    static class Point {
        int r;
        int c;
        Point(int row, int col) {
            r = row;
            c = col;
        }
        @Override
        public boolean equals(Object b) {
            if(!(b instanceof Point))
                return false;
            Point that = (Point)b;
            return this.r == that.r && this.c == that.c;
        }

        @Override
        public int hashCode() {
            return r*3 + c;
        }

    }

    private int minKeys = 0;
    private int maxKeys = 0;
    private int countOfPatterns = 0;
    private int numberOfPatterns(int m, int n) {
        this.minKeys = m;
        this.maxKeys = n;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                visit(r, c, new HashSet<>(), 0);
            }
        }
        return countOfPatterns;
    }

    private void visit(int r, int c, HashSet<Integer> visiting, int seen) {
        Point currentPoint = new Point(r, c);
        if(r<0 || r>2 || c< 0|| c>2 || seen > this.maxKeys || visiting.contains(currentPoint.hashCode())) {
            return;
        }
        if(!visiting.contains(currentPoint.hashCode())) {
            visiting.add(currentPoint.hashCode());
            seen++;
            if(seen >= this.minKeys && seen <= this.maxKeys) {
                countOfPatterns++;
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == r && j == c)
                    continue;
                Point nextPoint = new Point(i, j);
                if(isValid(nextPoint.hashCode(), currentPoint.hashCode(), visiting))
                    visit(i, j, visiting, seen);
            }
        }
        visiting.remove(currentPoint.hashCode());
    }

    private boolean isValid(int index, int last, HashSet<Integer> visiting) {
        if (visiting.contains(index))
            return false;
        // first digit of the pattern
        if (last == -1)
            return true;
        // knight moves or adjacent cells (in a row or in a column)
        if ((index + last) % 2 == 1)
            return true;
        // indexes are at both end of the diagonals for example 0,0, and 8,8
        int mid = (index + last)/2;
        if (mid == 4)
            return visiting.contains(mid);
        // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
        if ((index%3 != last%3) && (index/3 != last/3)) {
            return true;
        }
        // all other cells which are not adjacent
        return visiting.contains(mid);
    }

    public static void main(String[] args) {
        Problem351_androidUnlockPatterns obj = new Problem351_androidUnlockPatterns();
        System.out.print(obj.numberOfPatterns(1, 1));
    }
}
