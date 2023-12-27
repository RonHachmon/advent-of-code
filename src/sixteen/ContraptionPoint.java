package sixteen;

import eleven.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContraptionPoint {
    private Point currentPoint;
    private List<Point> enterPoints =new ArrayList<>();
    private ContraptionType contraptionType;
    private boolean isEnergized = false;
    private Set<Direction> enteredDirection=new HashSet<>();

    public ContraptionPoint(Point currentPoint, char contraptionType) {
        this.currentPoint = currentPoint;
        this.contraptionType = ContraptionType.getType(contraptionType);
    }


    public List<Point> getExitPoints() {
        List<Point> exitPoints = new ArrayList<>();
        for (Point entryPoint : enterPoints) {
            Direction direction = Direction.getDirection(entryPoint, currentPoint);
            boolean isAdded = enteredDirection.add(direction);
            if(isAdded)
            {
                exitPoints.addAll(contraptionType.getExitPoints(direction, currentPoint));

            }
        }
        enterPoints.clear();
        return exitPoints;

    }

    public Point getCurrentPoint() {
        return currentPoint;
    }



    public ContraptionType getContraptionType() {
        return contraptionType;
    }

    public boolean isEnergized() {
        return isEnergized;
    }

    public void setEnterPoints(Point enterPoints) {
        this.enterPoints.add(enterPoints);
    }

    public void setEnergized(boolean energized){
        isEnergized = energized;
    }
    public void Reset()
    {
        this.setEnergized(false);
        this.enteredDirection.clear();
        this.enterPoints.clear();
    }
}
