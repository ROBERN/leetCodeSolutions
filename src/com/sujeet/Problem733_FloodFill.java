package com.sujeet;

public class Problem733_FloodFill {
    private int newColor;
    private int currentColor;
    private int rowCount;
    private int colCount;
    private Boolean[][] visited;
    public int[][] floodFill(int[][] image, int sr, int sc, int nextColor) {
        rowCount = image.length;
        if(rowCount == 0) {
            return image;
        }
        colCount = image[0].length;
        if(!isValid(sr, sc) || image[sr][sc] == nextColor) {
            return image;
        }
        currentColor = image[sr][sc];
        newColor = nextColor;
        visited = new Boolean[rowCount][colCount];
        for(int r = 0; r < rowCount; r++) {
            for(int c = 0; c < colCount; c++) {
                visited[r][c] = false;
            }
        }
        floodFill(image, sr, sc);
        return image;
    }

    private void floodFill(int[][] image, int sr, int sc) {
        if(!isValid(sr, sc) || image[sr][sc] != currentColor || visited[sr][sc]) {
            return ;
        }
        visited[sr][sc] = true;
        image[sr][sc] = newColor;
        floodFill(image, sr+1, sc);
        floodFill(image, sr-1, sc);
        floodFill(image, sr, sc-1);
        floodFill(image, sr, sc+1);
    }

    private Boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < rowCount && col < colCount;
    }
}
