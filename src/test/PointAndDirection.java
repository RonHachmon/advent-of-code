package test;

import eleven.Point;
import sixteen.Direction;

public class PointAndDirection {
    private Point point;
    private Direction direction;

    public PointAndDirection(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }
}
