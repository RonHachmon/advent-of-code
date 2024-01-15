package test;

import eleven.Point;
import sixteen.Direction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public int[][] maze;
    private final int MAZE_WIDTH;
    private final int MAZE_HEIGHT;

    public Test(String path) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            maze = new int[allLines.size()][allLines.get(0).length()];
            MAZE_WIDTH = maze[0].length;
            MAZE_HEIGHT = maze.length;
            for (int i = 0; i < allLines.size(); i++) {
                String s = allLines.get(i);
                for (int j = 0; j < s.length(); j++) {
                    maze[i][j] = s.charAt(i) - '0';
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int ShortestPath() {
        PointAndDirection firstPoint = new PointAndDirection(new Point(1, 0), Direction.DOWN);
        PointAndDirection secondPoint = new PointAndDirection(new Point(0, 1), Direction.RIGHT);
        return Math.min(shortestPath(firstPoint,1,0),shortestPath(secondPoint,1,0));
    }

    private int shortestPath(PointAndDirection currentPointAndDirection,int steps,int total) {
        if (isEndPoint(currentPointAndDirection)) {
            return total+maze[MAZE_HEIGHT-1][MAZE_WIDTH-1];
        }
        total += maze[currentPointAndDirection.getPoint().row()][currentPointAndDirection.getPoint().column()];
        List<PointAndDirection> neighbours = this.validAdjacentPoints(currentPointAndDirection);
        List<Integer> weights = new ArrayList<>();
        for (PointAndDirection neighbour : neighbours) {
            if (neighbour.getDirection()==currentPointAndDirection.getDirection())
            {
                if(steps!=3)
                {
                    weights.add(shortestPath(neighbour,steps+1,total));
                }
            }
            else
            {
                weights.add(shortestPath(neighbour,1,total));
            }
        }

        return minNumInArray(weights);
    }

    private int minNumInArray(List<Integer> weights) {
        int min = Integer.MAX_VALUE;
        for (int weight : weights) {
            if (weight < min) {
                min = weight;
            }
        }
        return min;
    }

    private boolean isEndPoint(PointAndDirection neighbour) {
        return neighbour.getPoint().row() == MAZE_HEIGHT - 1 && neighbour.getPoint().column() == MAZE_WIDTH - 1;
    }

    private List<PointAndDirection> validAdjacentPoints(PointAndDirection pointAndDirection) {
        Point point=pointAndDirection.getPoint();
        Direction direction=pointAndDirection.getDirection();
        List<PointAndDirection> neighbours = new ArrayList<>();
        if (point.row() > 0 && direction != Direction.DOWN)
            neighbours.add(new PointAndDirection(new Point(point.row() - 1, point.column()),Direction.UP));
        if (point.row() < MAZE_HEIGHT - 1 && direction != Direction.UP)
            neighbours.add(new PointAndDirection(new Point(point.row() - 1, point.column()),Direction.DOWN));
        if (point.column() > 0 && direction != Direction.RIGHT)
            neighbours.add(new PointAndDirection(new Point(point.row() , point.column()-1),Direction.LEFT));
        if (point.column() < MAZE_WIDTH - 1 && direction != Direction.LEFT)
            neighbours.add(new PointAndDirection(new Point(point.row(), point.column() + 1),Direction.RIGHT));
        return neighbours;

    }
    private void PrintMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
