package thirteen.pattern;

import java.util.List;

public abstract class AbstractPattern<T> {

    protected List<T> rows;
    protected List<T> columns;
    private static int totalMismatch;
    public int FindColumnMirror() {
        for (int i = 0; i < columns.size(); i++) {

            if (i + 1 < columns.size() && isColumnStartingMirror(i, i + 1, 0)) {
                return i + 1;
            }

        }
        return -1;
    }

    private boolean isColumnStartingMirror(int leftMirror, int rightMirror, int currentCount) {
        if (leftMirror < 0 || rightMirror >= columns.size()) {
            return currentCount == totalMismatch;

        }
        currentCount = this.countDifferentBits(columns.get(leftMirror), columns.get(rightMirror), currentCount);
        if (currentCount <= totalMismatch) {
            return isColumnStartingMirror(leftMirror - 1, rightMirror + 1, currentCount);
        } else {
            return false;
        }
    }

    public int FindRowMirror() {
        for (int i = 0; i < rows.size(); i++) {
            if (i + 1 < rows.size() && isRowStartingMirror(i, i + 1, 0)) {
                return i + 1;
            }
        }
        return -1;
    }
    public abstract int countDifferentBits(T num1, T num2, int count) ;


    private boolean isRowStartingMirror(int topMirror, int bottomMirror, int currentCount) {
        if (topMirror < 0 || bottomMirror >= rows.size()) {
            return currentCount == totalMismatch;

        }
        currentCount = this.countDifferentBits(rows.get(topMirror), rows.get(bottomMirror), currentCount);
        if (currentCount <= totalMismatch) {
            return isRowStartingMirror(topMirror - 1, bottomMirror + 1, currentCount);
        } else {
            return false;
        }
    }
    public static void setTotalMismatch(int totalMismatch) {
        AbstractPattern.totalMismatch = totalMismatch;
    }
    public static int getTotalMismatch() {
        return totalMismatch;
    }
    public void PrintPattern()
    {
        for (T pattern : rows)
        {
            System.out.println(pattern);
        }
    }

    public List<T> getRows() {
        return rows;
    }

    public List<T> getColumns() {
        return columns;
    }


}
