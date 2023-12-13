package three;

public class Point {
    public static final int NOT_FOUND = -1;
    private int row=NOT_FOUND;
    private int column=NOT_FOUND;

    public Point() {

    }
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }



    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Point otherPoint = (Point) obj;
        return row == otherPoint.row && column == otherPoint.column;
    }

    @Override
    public int hashCode() {
        int result = 17; // Initial value
        result = 31 * result + row;
        result = 31 * result + column;
        return result;
    }
}
