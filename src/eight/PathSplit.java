package eight;

public class PathSplit {
    private final String leftPath;
    private final String rightPath;

    public String getLeftPath() {
        return leftPath;
    }

    public String getRightPath() {
        return rightPath;
    }

    public PathSplit(String leftPath, String rightPath) {
        this.leftPath = leftPath;
        this.rightPath = rightPath;
    }
}
