package ten;

import java.util.ArrayList;
import java.util.List;

public enum PipeSymbol {
    NORTH_SOUTH {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row()+1,currentPoint.column()));
            connectedPoints.add(new Point(currentPoint.row()-1,currentPoint.column()));
            return connectedPoints;

        }
    },
    WEST_EAST {

        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()-1));
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()+1));
            return connectedPoints;

        }
    },
    NORTH_EAST {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row()-1,currentPoint.column()));
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()+1));
            return connectedPoints;

        }
    },
    NORTH_WEST {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row()-1,currentPoint.column()));
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()-1));
            return connectedPoints;

        }
    },
    SOUTH_WEST {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()-1));
            connectedPoints.add(new Point(currentPoint.row()+1,currentPoint.column()));
            return connectedPoints;

        }
    },
    SOUTH_EAST {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()+1));
            connectedPoints.add(new Point(currentPoint.row()+1,currentPoint.column()));
            return connectedPoints;

        }
    },
    GROUND {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            return new ArrayList<>();
        }
    },
    START {
        @Override
        public List<Point> ConnectedPoint(Point currentPoint) {

            List<Point> connectedPoints=new ArrayList<>();
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()+1));
            connectedPoints.add(new Point(currentPoint.row(),currentPoint.column()-1));
            connectedPoints.add(new Point(currentPoint.row()+1,currentPoint.column()));
            connectedPoints.add(new Point(currentPoint.row()-1,currentPoint.column()));
            return connectedPoints;

        }
    };
    private  char symbol;
    public abstract List<Point> ConnectedPoint(Point currentPoint);

    public static PipeSymbol identifyPipeSymbol(char symbol)
    {
        switch (symbol)
        {
            case '.':
                return GROUND;
            case 'S':
                return START;
            case '|':
                return NORTH_SOUTH;
            case '-':
                return WEST_EAST;
            case 'L':
                return NORTH_EAST;
            case 'J':
                return NORTH_WEST;
            case '7':
                return SOUTH_WEST;
            case 'F':
                return SOUTH_EAST;

        }
        return GROUND;
    }
}




