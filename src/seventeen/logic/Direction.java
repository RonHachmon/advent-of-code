package seventeen.logic;



public enum Direction {
    UP,DOWN,LEFT,RIGHT;

    public static Direction getDirection(Point enterPoint, Point currentPoint){
        if(enterPoint.getX() == currentPoint.getX()){
            if(enterPoint.getY() < currentPoint.getY()){
                return LEFT;
            }else{
                return RIGHT;
            }
        }else{
            if(enterPoint.getX() < currentPoint.getX()){
                return UP;
            }else{
                return DOWN;
            }
        }
    }

}
