package seventeen;

import seventeen.logic.Direction;
import seventeen.logic.Point;
import seventeen.logic.StarPoint;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Seventeen {
    private final int MAZE_WIDTH;
    private final int MAZE_LENGTH;
    public StarPoint[][] maze;
    private StarPoint start = null;
    private StarPoint end = null;

    private int maxUntilTurn =10;

    private int minimumUntilTurn = 4;

    private PriorityQueue<StarPoint> enteredPoints = new PriorityQueue<>();
    private Set<StarPoint> finishedPoints = new HashSet<>();
    private Set<Point> restingPoints =new HashSet<>();



    public Seventeen(String path) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            MAZE_WIDTH = allLines.size();
            MAZE_LENGTH = allLines.get(0).length();
            maze = new StarPoint[MAZE_WIDTH][MAZE_LENGTH];
            buildMaze(allLines);
            setEndAndStartPoint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEndAndStartPoint() {
        start = maze[0][0];
        start.SetCostFromStart(0);
        end = maze[MAZE_WIDTH - 1][MAZE_LENGTH - 1];
    }

    private void buildMaze(List<String> allLines) {
        for (int i = 0; i < MAZE_WIDTH; i++) {
            String s = allLines.get(i);
            for (int j = 0; j < MAZE_LENGTH; j++) {
                int value = s.charAt(j) - '0';
                maze[i][j] = new StarPoint(new Point(i, j, value));
            }

        }
    }

    public void getPath() {
        restingPoints.add(start.getCurrentPoint());
        visitPoint(start);
        this.updateAllValidNeighboursCost(start);

        while (!this.isReachedToEnd() || enteredPoints.isEmpty()) {
            StarPoint minCostPoint = this.removeMinCostPoint();
            visitPoint(minCostPoint);
            updateAllValidNeighboursCost(minCostPoint);
        }

        //print cost of end goal
        for (StarPoint point : this.enteredPoints) {
            if (point.getCurrentPoint().equals(end.getCurrentPoint())) {
                //this.PrintPath(point);
                System.out.println(point.GetCostFromStart());
            }
        }


    }

    private void printEnteredPoint() {
        for (StarPoint point : enteredPoints) {
            System.out.println(point.getCurrentPoint().getX() + " " + point.getCurrentPoint().getY());
            System.out.println(point.getCurrentPoint().getWeight());
            System.out.println(point.GetTotalCost());
            System.out.println("_________");
        }

    }

    private void PrintPath(StarPoint point) {
        List<String> path = new ArrayList<>();
        while (point!=null) {
            path.add(point.getCurrentPoint().getX()+" " + point.getCurrentPoint().getY());
            point = point.getParent();
        }
        Collections.reverse(path);
        for (String s : path) {
            System.out.println(s);


        }
    }

    private boolean isReachedToEnd() {
        return this.restingPoints.contains(end.getCurrentPoint());
    }

    private void visitPoint(StarPoint start) {
        finishedPoints.add(start);
    }

    private void updateAllValidNeighboursCost(StarPoint currentPoint) {

        List<seventeen.logic.Point> neighbours = currentPoint.getCurrentPoint().GetCrossNeighbours(1);
        for (seventeen.logic.Point neighbour : neighbours) {
            if (isPointInGridBorder(neighbour) && !isVisitedOnPath(currentPoint, neighbour)) {
                StarPoint neighbourPoint = new StarPoint(maze[neighbour.getX()][neighbour.getY()].getCurrentPoint());
                neighbourPoint.setEnteredDirection(Direction.getDirection(neighbourPoint.getCurrentPoint(), currentPoint.getCurrentPoint()));
                if (currentPoint.getEnteredDirection() != null) {
                    if (isOppositeDirection(currentPoint.getEnteredDirection(), neighbourPoint.getEnteredDirection())) {
                        continue;
                    }
                    if (isSameDirection(currentPoint, neighbourPoint)) {
                        if (currentPoint.getStep() == maxUntilTurn) {
                            continue;
                        } else {
                            neighbourPoint.setStep(currentPoint.getStep() + 1);
                        }

                    } else {
                        if(currentPoint.getStep()< minimumUntilTurn)
                        {
                            continue;
                        }
                        else
                        {
                            neighbourPoint.setStep(1);
                        }
                    }
                } else {
                    neighbourPoint.setStep(1);
                }
                if (!this.finishedPoints.contains(neighbourPoint)) {
                    updateStarPointCost(currentPoint, neighbourPoint);

                }
            }
        }
    }

    private  boolean isSameDirection(StarPoint currentPoint, StarPoint neighbourPoint) {
        return currentPoint.getEnteredDirection() == neighbourPoint.getEnteredDirection();
    }

    private boolean isVisitedOnPath(StarPoint currentPoint, Point neighbour) {
        StarPoint parent = currentPoint.getParent();
        while (parent != null) {
            if (parent.getCurrentPoint() == neighbour) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;

    }

    private boolean isOppositeDirection(Direction enteredDirection, Direction enteredDirection1) {
        if (enteredDirection == Direction.UP && enteredDirection1 == Direction.DOWN) {
            return true;
        }
        if (enteredDirection == Direction.DOWN && enteredDirection1 == Direction.UP) {
            return true;
        }
        if (enteredDirection == Direction.LEFT && enteredDirection1 == Direction.RIGHT) {
            return true;
        }
        if (enteredDirection == Direction.RIGHT && enteredDirection1 == Direction.LEFT) {
            return true;
        }
        return false;
    }

    private StarPoint removeMinCostPoint() {
        return enteredPoints.poll();
    }


    private void updateStarPointCost(StarPoint currentPoint, StarPoint neighbourPoint) {
        neighbourPoint.SetHeuristicCost(this.calculateHeuristicCost(neighbourPoint.getCurrentPoint()));

        neighbourPoint.SetCostFromStart(currentPoint.GetCostFromStart() + neighbourPoint.getCurrentPoint().getWeight());
        neighbourPoint.setParent(currentPoint);
        if (!isPointWillBeVisitedOrAlreadyVisited(neighbourPoint)) {

            enteredPoints.add(neighbourPoint);
            if(neighbourPoint.getStep()>=this.minimumUntilTurn)
            {
                this.restingPoints.add(neighbourPoint.getCurrentPoint());
            }
        }
    }

    private boolean isPointWillBeVisitedOrAlreadyVisited(StarPoint neighbourPoint) {
//        if(finishedPoints.contains(neighbourPoint))
//        {
//            return true;
//        }


        for (StarPoint point : finishedPoints) {
            if (point.getCurrentPoint() == neighbourPoint.getCurrentPoint() &&
                    isSameDirection(point, neighbourPoint) &&
                    neighbourPoint.getStep() ==  point.getStep()) {
                return true;
            }
        }

        for (StarPoint point : enteredPoints) {
            if (point.getCurrentPoint() == neighbourPoint.getCurrentPoint() &&
                    isSameDirection(point, neighbourPoint)
                    && neighbourPoint.getStep() ==  point.getStep()) {
                return true;
            }
        }
        return false;
    }


    private int calculateHeuristicCost(seventeen.logic.Point currentPoint) {
        int a = Math.abs(currentPoint.getX() - end.getCurrentPoint().getX());
        int b = Math.abs(currentPoint.getY() - end.getCurrentPoint().getY());

        return a + b;
    }

    private boolean isPointInGridBorder(seventeen.logic.Point point) {
        return point.getX() >= 0 && point.getX() < MAZE_WIDTH && point.getY() >= 0 && point.getY() < MAZE_LENGTH;
    }




    public void PrintMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
