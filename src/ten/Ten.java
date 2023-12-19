package ten;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Ten {
    public static final int NOT_FOUND = -1;
    private MazePoint[][] maze;
    private MazePoint startingPoint;

    public static int MAZE_LENGTH;
    public static int MAZE_WIDTH;

    public Ten(String path) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            initMatrixProperties(strings);
            initMaze(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalTrapInCircle() {
        int total = 0;
        boolean foundBorder=false;
        BorderType startBorderType;
        BorderType previousEndBorderType=BorderType.HORIZONTAL;
        int startIndex = 0;
        int startValue = 0;
        for (int row = 1; row < MAZE_LENGTH-1; row++) {
            startBorderType=null;
            for (int column = 1; column < MAZE_WIDTH-1; column++) {
                MazePoint mazePoint = maze[row][column];
                if (isPartOfCircleBorder(mazePoint)) {
                    BorderType borderType = identifyBorderType(mazePoint);
                    if(borderType!=BorderType.HORIZONTAL)
                    {
                        if(startBorderType==null&&borderType!=previousEndBorderType)
                        {
                            startBorderType=borderType;
                            startIndex = column;
                        }
                        else
                        {
                            if(startBorderType!=borderType)
                            {
                                previousEndBorderType=borderType;
                                startBorderType=null;
                            }
                        }
                    }
                } else {
                    if (startBorderType!=null) {
                        total++;
                        mazePoint.setNumericValue(555);
                    }
                }
            }

        }
        this.PrintMazeNumericValues();
        return total;
    }

    private  BorderType identifyBorderType(MazePoint mazePointBorder) {
        int currentNumericValue = mazePointBorder.getNumericValue();
        MazePoint upperMazePoint= this.maze[mazePointBorder.getPoint().row()-1][mazePointBorder.getPoint().column()];
        MazePoint lowerMazePoint= this.maze[mazePointBorder.getPoint().row()+1][mazePointBorder.getPoint().column()];
        if(upperMazePoint.getNumericValue()-1==currentNumericValue)
        {
            return BorderType.UP_BORDER;
        }
        if(upperMazePoint.getNumericValue()+1==currentNumericValue)
        {
            return BorderType.DOWN_BORDER;
        }
        if(lowerMazePoint.getNumericValue()-1==currentNumericValue)
        {
            return BorderType.DOWN_BORDER;
        }
        if(lowerMazePoint.getNumericValue()+1==currentNumericValue)
        {
            return BorderType.UP_BORDER;
        }
        return BorderType.HORIZONTAL;
    }

    private static boolean isPartOfCircleBorder(MazePoint mazePoint) {
        return mazePoint.getNumericValue() != NOT_FOUND;
    }

    private boolean isContinousOfSameLine(int startValue, MazePoint mazePoint) {
        if (mazePoint.getNumericValue() + 1 == startValue || mazePoint.getNumericValue() - 1 == startValue) {
            return true;
        }
        return false;
    }

    public int FindCircle() {
        List<Point> connectedMazePoint = startingPoint.getConnectedMazePoint();
        validatePoints(startingPoint, connectedMazePoint);
        for (int i = 0; i < connectedMazePoint.size(); i++) {
            Point point = connectedMazePoint.get(i);
            MazePoint mazePoint = maze[point.row()][point.column()];
            mazePoint.setNumericValue(startingPoint.getNumericValue() + 1);
            int circleLength = findCircleLength2(mazePoint);
            if (circleLength != NOT_FOUND) {
                return circleLength / 2;
            }

        }
        return NOT_FOUND;
    }

    private int findCircleLength2(MazePoint currentPoint) {
        int numericValue = 1;

        while (currentPoint != this.startingPoint) {
            currentPoint.setNumericValue(numericValue);

            List<Point> connectedMazePoint = currentPoint.getConnectedMazePoint();
            validatePoints(currentPoint, connectedMazePoint);
            if (connectedMazePoint == null) {
                PrintMazeNumericValues();
                return NOT_FOUND;
            }
            currentPoint = findUnreachedPipe(connectedMazePoint);
            if (currentPoint == null) {
                if (isCurrentPointCanReachStart(connectedMazePoint)) {
                    currentPoint = this.startingPoint;
                } else {
                    PrintMazeNumericValues();
                    return NOT_FOUND;
                }
            }
            numericValue++;
        }
        this.PrintMazeNumericValues();
        return numericValue;
    }

    private int findCircleLength(MazePoint currentPoint) {
        int previousPointNumericValue;

        do {
            previousPointNumericValue = currentPoint.getNumericValue();
            List<Point> connectedMazePoint = currentPoint.getConnectedMazePoint();
            validatePoints(currentPoint, connectedMazePoint);
            if (connectedMazePoint == null) {
                return NOT_FOUND;
            }
            currentPoint = findUnreachedPipe(connectedMazePoint);
            if (currentPoint == null) {
                if (isCurrentPointCanReachStart(connectedMazePoint)) {
                    return previousPointNumericValue + 1;
                } else {
                    return -1;
                }
            }
            currentPoint.setNumericValue(previousPointNumericValue + 1);
            //this.PrintMazeNumericValues();
        } while (currentPoint != startingPoint);
        return NOT_FOUND;
    }

    private boolean isCurrentPointCanReachStart(List<Point> connectedMazePoint) {
        for (int i = 0; i < connectedMazePoint.size(); i++) {
            Point point = connectedMazePoint.get(i);
            MazePoint mazePoint = maze[point.row()][point.column()];
            if (mazePoint.getNumericValue() == 0) {
                return true;
            }
        }
        return false;
    }

    private MazePoint findUnreachedPipe(List<Point> connectedMazePoint) {
        for (int i = 0; i < connectedMazePoint.size(); i++) {
            Point point = connectedMazePoint.get(i);
            MazePoint mazePoint = maze[point.row()][point.column()];
            if (mazePoint.getNumericValue() == -1) {
                return mazePoint;
            }
        }
        return null;
    }

    private void validatePoints(MazePoint currentPoint, List<Point> connectedMazePoint) {
        if (connectedMazePoint != null) {
            this.validateMazePointBounds(connectedMazePoint);
            this.validateReachableMazePointToCurrentPoint(currentPoint, connectedMazePoint);
        }
    }

    private void validateReachableMazePointToCurrentPoint(MazePoint currentPoint, List<Point> connectedPoints) {
        for (int pointIndex = 0; pointIndex < connectedPoints.size(); pointIndex++) {

            Point point = connectedPoints.get(pointIndex);
            MazePoint mazePoint = maze[point.row()][point.column()];
            List<Point> ConnectedPointToCurrentPoint = mazePoint.getConnectedMazePoint();
            if (!ConnectedPointToCurrentPoint.contains(currentPoint.getPoint())) {
                connectedPoints.remove(pointIndex);
            }

        }

    }


    private void validateMazePointBounds(List<Point> connectedMazePoint) {
        for (int pointIndex = 0; pointIndex < connectedMazePoint.size(); pointIndex++) {
            Point point = connectedMazePoint.get(pointIndex);
            if (point.row() >= MAZE_LENGTH || point.row() < 0) {
                connectedMazePoint.remove(pointIndex);
            }
            if (point.column() >= MAZE_WIDTH || point.column() < 0) {
                connectedMazePoint.remove(pointIndex);
            }
        }
    }

    private void initMaze(List<String> strings) {
        for (int row = 0; row < MAZE_LENGTH; row++) {
            String matrixRow = strings.get(row);
            for (int column = 0; column < MAZE_WIDTH; column++) {
                MazePoint mazePoint = new MazePoint(row, column, matrixRow.charAt(column));
                if (mazePoint.getPipeSymbol() == PipeSymbol.START) {
                    this.startingPoint = mazePoint;
                    this.startingPoint.setNumericValue(0);
                }
                maze[row][column] = mazePoint;
            }
        }
    }

    public void PrintMazeNumericValues() {
        for (int row = 0; row < MAZE_LENGTH; row++) {
            for (int column = 0; column < MAZE_WIDTH; column++) {
                maze[row][column].PrintNumericValue();
                if (column != MAZE_WIDTH - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------");
    }

    public void PrintMazeSymbols() {
        for (int row = 0; row < MAZE_LENGTH; row++) {
            for (int column = 0; column < MAZE_WIDTH; column++) {
                maze[row][column].PrintSymbolValue();
                if (column != MAZE_WIDTH - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    private void initMatrixProperties(List<String> strings) {
        MAZE_LENGTH = strings.size();
        MAZE_WIDTH = strings.get(0).length();
        maze = new MazePoint[MAZE_LENGTH][MAZE_WIDTH];
    }
}
