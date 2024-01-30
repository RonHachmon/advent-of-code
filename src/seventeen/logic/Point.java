package seventeen.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    private final int x;
    private final int y;


    private final int weight;


    public Point(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int getX() {
        return x;
    }



    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y ;
    }
    public int getWeight() {
        return weight;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public List<Point> GetSquareNeighbours(int squareLength) {
        List<Point> neighbours = new ArrayList<>();
        for (int i=-squareLength;i<=squareLength;i++)
        {
            for (int j = -squareLength; j <=squareLength ; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newX = x + i;
                int newY = y + j;
                neighbours.add(new Point(newX,newY, weight));
            }
        }
        return neighbours;
    }
    public List<Point> GetCrossNeighbours(int crossLength) {
        List<Point> neighbours = new ArrayList<>();
        for (int i = 1; i <=crossLength ; i++) {
            neighbours.add(new Point(x -i , y, weight));
            neighbours.add(new Point(x +i , y, weight));
            neighbours.add(new Point(x  , y-i, weight));
            neighbours.add(new Point(x  , y+i, weight));
        }
        return neighbours;
    }

    public boolean IsVerticalOrHorizontalToPoint(Point currentPoint) {
        return x == currentPoint.x || y == currentPoint.y;
    }

    @Override
    public String toString() {
        return ""+this.getWeight();
    }
}
