package com.sujeet.Practise;

public class ProblemBookShelf {
    int maxWidth;
    int[][] books;

    int minHeight = Integer.MAX_VALUE;
    public int minHeightShelves(int[][] bks, int shelf_width) {
        books = bks;
        maxWidth = shelf_width;

        getMinHeight(0, 0, 0);
        return minHeight;
    }

    int[][] mem = new int[1001][1001];
    int getMinHeight(int idx, int w, int h) {

        if(idx >= books.length) {
            return h;
        }
        if(mem[idx][w] != 0)
            return mem[idx][w];
        int remainingWidth = maxWidth-w;
        // current shelf
        mem[idx][w] = Math.min(
                getMinHeight(idx+1, w + books[idx][0], Math.max(h, books[idx][1])),
                // next shelf
                w > maxWidth ? Integer.MAX_VALUE : h + getMinHeight(idx+1, books[idx][0], books[idx][1])
        );
        return mem[idx][w];
    }

    public static void main(String[] args) {
        ProblemBookShelf problemBookShelf = new ProblemBookShelf();
        System.out.println(problemBookShelf.minHeightShelves(new int[][] {
                {1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2},
        }, 4));
    }
}
