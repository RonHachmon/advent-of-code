package sixteen;

import eleven.Point;

public enum Direction {
    UP,DOWN,LEFT,RIGHT;

    public static Direction getDirection(Point enterPoint, Point currentPoint){
        if(enterPoint.row() == currentPoint.row()){
            if(enterPoint.column() < currentPoint.column()){
                return LEFT;
            }else{
                return RIGHT;
            }
        }else{
            if(enterPoint.row() < currentPoint.row()){
                return DOWN;
            }else{
                return UP;
            }
        }
    }
}
