package com.sujeet.Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SpiralMatrix {
    static void spiralMatrix(List<List<Integer>> matrix) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int matrixSize = matrix.size();
        for(int iteration = 0; iteration <= matrix.size()/2; iteration++) {
            int offSet = matrixSize-iteration-1;
            for(int right = iteration; right < matrixSize-iteration; right++) {
                int val = matrix.get(iteration).get(right);
                System.out.print(val);
                map.put(val, map.getOrDefault(val, 0)+1);
                System.out.print(" ");
            }
            for(int down = iteration+1; down < matrixSize-iteration; down++) {
                int val = matrix.get(down).get(matrixSize-iteration-1);
                System.out.print(val);
                map.put(val, map.getOrDefault(val, 0)+1);
                System.out.print(" ");
            }
            for (int left = matrixSize-iteration-2; left >= iteration; left--) {
                int val = matrix.get(matrixSize-iteration-1).get(left);
                System.out.print(val);
                map.put(val, map.getOrDefault(val, 0)+1);
                System.out.print(" ");
            }
            for (int up = matrixSize-iteration-2; up > iteration; up--) {
                int val =matrix.get(up).get(iteration);
                System.out.print(val);
                map.put(val, map.getOrDefault(val, 0)+1);
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1,2,3, 4, 5));
        matrix.add(Arrays.asList(1,2,3, 4, 5));
        matrix.add(Arrays.asList(1,2,3, 4, 5));
        matrix.add(Arrays.asList(1,2,3, 4, 5));
        matrix.add(Arrays.asList(1,2,3, 4, 5));
        spiralMatrix(matrix);
    }

}
