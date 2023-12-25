package fourteen;

import eleven.Point;

public class Rock {
    public void setRockPoint(Point rockPoint) {
        this.rockPoint = rockPoint;
    }

    public void setRockType(RockType rockType) {
        this.rockType = rockType;
    }

    private Point rockPoint;
    private RockType rockType;

    public Rock(Point rockPoint, char rockType) {
        this.rockType=RockType.determineShape(rockType);
        this.rockPoint=rockPoint;
    }


    public Point getRockPoint() {
        return rockPoint;
    }

    public RockType getRockType() {
        return rockType;
    }


}
