package five;

public class ResourceDetail {
    private long resourceSpreadPoint;
    private long sourceTakenSpreadPoint;
    private long jump;

    public long getResourceSpreadPoint() {
        return resourceSpreadPoint;
    }

    public void setResourceSpreadPoint(long resourceSpreadPoint) {
        this.resourceSpreadPoint = resourceSpreadPoint;
    }

    public long getSourceTakenSpreadPoint() {
        return sourceTakenSpreadPoint;
    }

    public void setSourceTakenSpreadPoint(long sourceTakenSpreadPoint) {
        this.sourceTakenSpreadPoint = sourceTakenSpreadPoint;
    }

    public long getJump() {
        return jump;
    }

    public void setJump(long jump) {
        this.jump = jump;
    }

    public boolean InRangeOfLocation(long currentSourceLocation) {
        if (currentSourceLocation >= this.sourceTakenSpreadPoint && currentSourceLocation <= this.sourceTakenSpreadPoint+jump-1) {
            return true;
        }
        return false;
    }

    public long GetNextResourceLocation(long currentSourceLocation) {
        long gap=currentSourceLocation-this.sourceTakenSpreadPoint;
        return this.resourceSpreadPoint+gap;

    }
}
