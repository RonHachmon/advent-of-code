package thirteen.pattern;

import java.util.List;

public class StringPattern implements MirrorPatternInterface {
    private List<String> rows;
    private List<String> columns;
    private static int totalMismatch;




    public StringPattern(List<String> rows, List<String> columns) {
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
        currentCount = this.countDifferentChars(columns.get(leftMirror), columns.get(rightMirror), currentCount);
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
        currentCount = this.countDifferentChars(rows.get(topMirror), rows.get(bottomMirror), currentCount);
        if (currentCount <= totalMismatch) {
            return isRowStartingMirror(topMirror - 1, bottomMirror + 1, currentCount);
        } else {
            return false;
        }
    }

    public int countDifferentChars(String str1, String str2, int count) {
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
                if (count > 1) {
                    return count;
                }
            }

        }

        return count;
    }
    public static void setTotalMismatch(int totalMismatch) {
        StringPattern.totalMismatch = totalMismatch;
    }



    public void PrintPattern() {
        for (String row : rows) {
            System.out.println(row);
        }
        System.out.println();
    }

    public List<String> getRows() {
        return rows;
    }

    public List<String> getColumns() {
        return columns;
    }
}
