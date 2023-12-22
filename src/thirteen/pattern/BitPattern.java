package thirteen.pattern;

import java.util.List;

public class BitPattern implements MirrorPatternInterface {
    private List<Integer> rows;
    private List<Integer> columns;
    private static int totalMismatch;




    public BitPattern(List<Integer> rows, List<Integer> columns) {
        this.rows = rows;
        this.columns = columns;
    }
    @Override
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
    @Override
    public int FindRowMirror() {
        for (int i = 0; i < rows.size(); i++) {
            if (i + 1 < rows.size() && isRowStartingMirror(i, i + 1, 0)) {
                return i + 1;
            }
        }
        return -1;
    }




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

    public int countDifferentBits(int num1, int num2, int count) {
        int xorResult = num1 ^ num2;
        while (xorResult != 0) {
            xorResult &= (xorResult - 1);
            count++;
            if (count > BitPattern.totalMismatch) {
                return count;
            }
        }
        return count;

    }
    public static void setTotalMismatch(int totalMismatch) {
        BitPattern.totalMismatch = totalMismatch;
    }
    public void PrintPattern() {
        for (int row : rows) {
            System.out.println(row);
        }
        System.out.println();
    }
}
