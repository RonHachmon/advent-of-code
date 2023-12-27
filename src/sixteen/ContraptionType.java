package sixteen;

import eleven.Point;

import java.util.ArrayList;
import java.util.List;

public  enum ContraptionType {
    MIRROR_FORWARD('/') {
        @Override
        public List<Point> getExitPoints(Direction direction, Point currentPoint) {
            List<Point> exitPoints = new ArrayList<>();
            switch (direction) {
                case UP:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column() + 1));
                    break;
                case DOWN:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column() - 1));
                    break;
                case LEFT:
                    exitPoints.add(new Point(currentPoint.row() - 1, currentPoint.column()));
                    break;
                case RIGHT:
                    exitPoints.add(new Point(currentPoint.row() + 1, currentPoint.column()));
                    break;
                default:
                    break;
            }
            return exitPoints;
        }
    },
    MIRROR_BACKWARD('\\') {
        @Override
        public List<Point> getExitPoints(Direction direction, Point currentPoint) {
            List<Point> exitPoints = new ArrayList<>();
            switch (direction) {
                case UP:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column() - 1));
                    break;
                case DOWN:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column() + 1));
                    break;
                case LEFT:
                    exitPoints.add(new Point(currentPoint.row() + 1, currentPoint.column()));
                    break;
                case RIGHT:
                    exitPoints.add(new Point(currentPoint.row() - 1, currentPoint.column()));
                    break;
                default:
                    break;
            }
            return exitPoints;
        }
    },SPLIT_HORIZONTAL('|') {
        @Override
        public List<Point> getExitPoints(Direction direction, Point currentPoint) {
            List<Point> exitPoints = new ArrayList<>();
            switch (direction) {
                case UP:
                    exitPoints.add(new Point(currentPoint.row()-1, currentPoint.column()));
                    break;
                case DOWN:
                    exitPoints.add(new Point(currentPoint.row()+1, currentPoint.column()));
                    break;
                case LEFT,RIGHT:
                    exitPoints.add(new Point(currentPoint.row()+1, currentPoint.column()));
                    exitPoints.add(new Point(currentPoint.row()-1, currentPoint.column()));
                    break;
                default:
                    break;
            }
            return exitPoints;
        }
    },SPLIT_VERTICAL('-') {
        @Override
        public List<Point> getExitPoints(Direction direction, Point currentPoint) {
            List<Point> exitPoints = new ArrayList<>();
            switch (direction) {
                case UP,DOWN:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column()+1));
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column()-1));
                    break;
                case LEFT:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column()+1));
                    break;
                case RIGHT:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column()-1));
                    break;
                default:

                    break;
            }
            return exitPoints;
        }
    },DOT('.') {
        @Override
        public List<Point> getExitPoints(Direction direction, Point currentPoint) {
            List<Point> exitPoints = new ArrayList<>();
            switch (direction) {
                case UP:
                    exitPoints.add(new Point(currentPoint.row()-1, currentPoint.column()));
                    break;
                case DOWN:
                    exitPoints.add(new Point(currentPoint.row()+1, currentPoint.column()));
                    break;
                case LEFT:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column()+1));
                    break;
                case RIGHT:
                    exitPoints.add(new Point(currentPoint.row(), currentPoint.column()-1));
                    break;
                default:
                    break;
            }
            return exitPoints;
        }
    };

    private final char contraptionType;

    public char getContraptionType() {
        return contraptionType;
    }

    ContraptionType(char c) {
        this.contraptionType=c;

    }
    public static ContraptionType getType(char c){
        for(ContraptionType type:ContraptionType.values()){
            if(type.getContraptionType()==c)
                return type;
        }
        return null;
    }

    public abstract List<Point> getExitPoints(Direction direction,Point currentPoint) ;


}
