package test;

public class MazePoint {
    private  int x;
    private  int y;
    private int value;
    private boolean isVisited=false;
    public MazePoint(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }


    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public boolean isVisited() {
        return isVisited;
    }
}
