package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

interface Robot {
      // Returns true if the cell in front is open and robot moves into the cell.
     // Returns false if the cell in front is blocked and robot stays in the current cell.
     public boolean move();
      // Robot will stay in the same cell after calling turnLeft/turnRight.
      // Each turn will be 90 degrees.
     public void turnLeft();
     public void turnRight();

     // Clean the current cell.
     public void clean();
  }

public class Problem489_RobotCleanRoom {
    Map<Integer, Set<Integer>> visited= new HashMap<>();
    int currX = 0;
    int currY = 0;
    char[] dirs = new char[]{'U', 'R', 'D', 'L'};
    int dir = 0;
    public void cleanRoom(Robot robot) {
        if(visited.containsKey(currX) &&
                visited.get(currX).contains(currY)) {
            return;
        }
        robot.clean();
        if(visited.containsKey(currX)) {
            visited.get(currX).add(currY);
        } else {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(currY);
            visited.put(currX, newSet);
        }
        //say Up
        if(robot.move()) {
            recordMove();
            cleanRoom(robot);
            robot.turnRight();
            robot.turnRight();
            dir = (dir+2)%4;
            robot.move();
            recordMove();
        }

        //say Down
        if(robot.move()) {
            recordMove();
            cleanRoom(robot);
            robot.turnRight();
            robot.turnRight();
            dir = (dir+2)%4;
            robot.move();
            recordMove();
        }

        //say Right
        robot.turnRight();
        dir = (dir+1)%4;
        if(robot.move()) {
            recordMove();
            cleanRoom(robot);
            robot.turnRight();
            robot.turnRight();
            robot.move();
            recordMove();
        }

        //say left
        if(robot.move()) {
            recordMove();
            cleanRoom(robot);
            robot.turnRight();
            robot.turnRight();
            robot.move();
            recordMove();
        }
        dir = (dir+1)%4;
    }

    private void recordMove() {
        switch(dir) {
            case 'U':
                currY++;
                break;
            case 'D':
                currY--;
                break;
            case 'R':
                currX++;
                break;
            case 'L':
                currX--;
                break;
        }
    }
}
