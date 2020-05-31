package com.sujeet.leetCode;

public class Problem308_RangeSumQuery2D {

    private class IndexRSQ {
        IndexRSQ(int pR, int pC, int rowSt, int rowEnd, int colSt, int colEnd) {
            posRow = pR;
            posCol = pC;
            rSt = rowSt;
            rEnd = rowEnd;
            cSt = colSt;
            cEnd = colEnd;
        }

        int posRow;
        int posCol;
        int rSt;
        int rEnd;
        int cSt;
        int cEnd;
    }

    private int[][] rangeSumQuery;
    private int rowSz;
    private int colSz;
    private int[][] matrix;
    public Problem308_RangeSumQuery2D(int[][] matrix) {
        rowSz = matrix.length;
        if(rowSz == 0)
            return;
        colSz = matrix[0].length;
        if(colSz == 0)
            return;
        this.matrix = matrix;
        int log2RowSz = (int)(Math.ceil(Math.log(rowSz)/Math.log(2)));
        int log2ColSz = (int)(Math.ceil(Math.log(colSz)/Math.log(2)));
        int rsqRowSz = (int)(2*Math.pow(2, log2RowSz)-1);
        int rsqColSz = (int)(2*Math.pow(2, log2ColSz)-1);

        rangeSumQuery = new int[rsqRowSz][rsqColSz];

        IndexRSQ pos0 = new IndexRSQ(0, 0, 0, rowSz-1, 0, colSz-1);
        constructRangeSumQuery(pos0);
    }

    private int constructRangeSumQuery(IndexRSQ rsq) {
        int rowSt = rsq.rSt;
        int rowEnd = rsq.rEnd;
        int colSt = rsq.cSt;
        int colEnd = rsq.cEnd;
        int posRow = rsq.posRow;
        int posCol = rsq.posCol;
        if(rowSt == rowEnd && colSt == colEnd) {
            return rangeSumQuery[posRow][posCol] = this.matrix[rowSt][colSt];
        }
        int rowEndLeft = rowSt +(rowEnd-rowSt)/2;
        int colEndLeft = colSt +(colEnd-colSt)/2;

        int rowStRight = rowSt == rowEnd ? rowSt : rowEndLeft+1;
        int colStRight = colSt == colEnd ? colSt : colEndLeft+1;

        IndexRSQ posLeft = new IndexRSQ(posRow*2+1, posCol*2+1, rowSt, rowEndLeft, colSt, colEndLeft);
        IndexRSQ posRight = new IndexRSQ(posRow*2+2, posCol*2+2, rowStRight, rowEnd, colStRight, colEnd);
        return rangeSumQuery[posRow][posCol] = constructRangeSumQuery(posLeft)//left
                + constructRangeSumQuery(posRight);//right
    }

    public void update(int row, int col, int val) {
        IndexRSQ pos0 = new IndexRSQ(0, 0, 0, rowSz-1, 0, colSz-1);
        int diff = val - matrix[row][col];
        update(pos0, row, col, diff);
        this.matrix[row][col] = val;
    }

    private int update(IndexRSQ rsq, int rowUpdate, int colUpdate, int diff) {
        int rowSt = rsq.rSt;
        int rowEnd = rsq.rEnd;
        int colSt = rsq.cSt;
        int colEnd = rsq.cEnd;
        int posRow = rsq.posRow;
        int posCol = rsq.posCol;

        if(rowSt == rowEnd && colSt == colEnd) {
            if(rowSt == rowUpdate && colSt == colUpdate) {
                return rangeSumQuery[posRow][posCol] += diff;
            }
            return 0;
        }
        if(rowSt > rowUpdate || rowEnd < rowUpdate || colSt > colUpdate || colEnd < colUpdate) {
            return 0;
        }
        int rowEndLeft = rowSt + (rowEnd-rowSt)/2;
        int colEndLeft = colSt + (colEnd-colSt)/2;

        int rowStRight = rowSt == rowEnd ? rowSt : rowEndLeft+1;
        int colStRight = colSt == colEnd ? colSt : colEndLeft+1;

        IndexRSQ posLeft = new IndexRSQ(posRow*2+1, posCol*2+1, rowSt, rowEndLeft, colSt, colEndLeft);
        IndexRSQ posRight = new IndexRSQ(posRow*2+2, posCol*2+2, rowStRight, rowEnd, colStRight, colEnd);
        return rangeSumQuery[posRow][posCol] += update(posLeft, rowUpdate, colUpdate, diff) + update(posRight, rowUpdate, colUpdate, diff);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        IndexRSQ pos0 = new IndexRSQ(0, 0, 0, rowSz-1, 0, colSz-1);
        return sumRegion(pos0, row1, col1, row2, col2);
    }

    private int sumRegion(IndexRSQ rsq, int rowStQuery, int rowEndQuery, int colStQuery,  int colEndQuery) {
        int rowSt = rsq.rSt;
        int rowEnd = rsq.rEnd;
        int colSt = rsq.cSt;
        int colEnd = rsq.cEnd;
        int posRow = rsq.posRow;
        int posCol = rsq.posCol;

        if(rowSt > rowEndQuery || rowEnd < rowStQuery || colSt > colStQuery || colEnd < colEndQuery) {
            return 0;
        }

        if(rowSt >= rowStQuery && rowEnd <= rowEndQuery && colSt >= colEndQuery && colEnd <= colEndQuery) {
            return rangeSumQuery[posRow][posCol];
        }
        int endLeftRow = rowSt + (rowEnd-rowSt)/2;
        int endLeftCol = colSt + (colEnd-colSt)/2;

        int stRightRow = rowSt == rowEnd ? rowSt : endLeftRow+1;
        int stRightCol = colSt == colEnd ? colSt : endLeftCol+1;

        IndexRSQ posLeft = new IndexRSQ(posRow*2+1, posCol*2+1, rowSt, endLeftRow, colSt, endLeftCol);
        IndexRSQ posRight = new IndexRSQ(posRow*2+2, posCol*2+2, stRightRow, rowEnd, stRightCol, colEnd);

        return rangeSumQuery[posRow][posCol] = sumRegion(posLeft, rowStQuery, rowEndQuery, colStQuery, colEndQuery)
                + sumRegion(posRight, rowStQuery, rowEndQuery, colStQuery, colEndQuery);
    }

    public static void main(String[] args) {
        Problem308_RangeSumQuery2D obj = new Problem308_RangeSumQuery2D(new int[][]{
            {3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}
        });
        System.out.println(obj.sumRegion(2,1,4,3));
    }
}

/**
 ["NumMatrix","sumRegion","update","sumRegion"]
 [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],
 [2,1,4,3],[3,2,2],[2,1,4,3]]
 **/