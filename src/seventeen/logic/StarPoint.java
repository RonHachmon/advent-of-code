package seventeen.logic;

import java.util.Objects;

public class StarPoint implements Comparable<StarPoint> {
    private final Point currentPoint;

    private StarPoint parent;
    private  Cost cost=new Cost();
    private Direction enteredDirection=null;
    private int step=0;



    public StarPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }


    public StarPoint getParent() {
        return parent;
    }

    public void setParent(StarPoint parent) {
        this.parent = parent;
    }

    public Integer GetCostFromStart() {
        return cost.getCostFromStart();

    }

    public void SetCostFromStart(Integer costFromStart) {
        cost.setCostFromStart(costFromStart);
    }
    public void SetHeuristicCost(Integer heuristicCost) {
        cost.setHeuristicCost(heuristicCost);
    }

    public Integer GetHeuristicCost() {
        return cost.getHeuristicCost();
    }





    public Integer GetTotalCost() {

        return cost.getTotalCost();
    }
    public Direction getEnteredDirection() {
        return enteredDirection;
    }

    public void setEnteredDirection(Direction enteredDirection) {
        this.enteredDirection = enteredDirection;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }








    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarPoint starPoint = (StarPoint) o;
        return  Objects.equals(currentPoint, starPoint.currentPoint) && Objects.equals(parent, starPoint.parent) && Objects.equals(cost, starPoint.cost);
    }

    @Override
    public String toString() {
        return currentPoint.toString();
    }

    @Override
    public int compareTo(StarPoint o) {
        int totalCostComparison = Integer.compare(this.GetTotalCost(), o.GetTotalCost());
        if (totalCostComparison != 0) {
            return totalCostComparison;
        }
        return Integer.compare(this.GetHeuristicCost(), o.GetHeuristicCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPoint, this.step, enteredDirection);
    }
}
