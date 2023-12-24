package thirteen;

import thirteen.pattern.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Thirteen {
    public static final int NOT_FOUND = -1;
    List<StringPattern> stringPatternList = new ArrayList<>();
    List<BitPattern> bitPatternList = new ArrayList<>();


    public Thirteen(String path) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            int startRow = 0;
            for (int i = 0; i < allLines.size(); i++) {
                String row = allLines.get(i);
                if (row.isEmpty()) {
                    this.extractPattern(startRow, i, allLines);
                    startRow = i + 1;
                }

            }
            this.extractPattern(startRow, allLines.size(), allLines);
            this.extractBitPattern();

        } catch (IOException e) {
            throw new RuntimeException("Error initializing matrix: " + e.getMessage());
        }
    }

    private void extractBitPattern() {

        for (StringPattern pattern : stringPatternList)
        {
            List<Integer> intRows = new ArrayList<>();
            List<Integer> intColumns = new ArrayList<>();
            List<String> columns = pattern.getColumns();
            List<String> rows = pattern.getRows();
            for (String column:columns)
            {
                intColumns.add(this.stringMazeToBinaryInt(column));
            }
            for (String row:rows)
            {
                intRows.add(this.stringMazeToBinaryInt(row));
            }
            this.bitPatternList.add(new BitPattern(intRows,intColumns));
        }
    }



    public int StringPatternFindMirror(int totalMismatch)
    {
        StringPattern.setTotalMismatch(totalMismatch);

        return findAllMirror(this.stringPatternList);
    }
    public int BitPatternFindMirror(int totalMismatch)
    {
        BitPattern.setTotalMismatch(totalMismatch);


        return findAllMirror(this.bitPatternList);
    }
    private int findAllMirror(List<? extends AbstractPattern> mirrorPattern) {
        int sum=0;
        for (AbstractPattern pattern : mirrorPattern) {
            int columnMirror = pattern.FindColumnMirror();
            if(columnMirror!= NOT_FOUND)
            {
                sum+=columnMirror;
            }

            int rowMirror = pattern.FindRowMirror();
            if(rowMirror!=NOT_FOUND)
            {
                sum+=rowMirror*100;

            }
        }

        return sum;
    }




    private void extractPattern(int startRow, int endRow, List<String> allLines) {
        List<String> rows = allLines.subList(startRow, endRow);
        List<String> columns = new ArrayList<>();
        String s = allLines.get(startRow);
        for (int i = 0; i < s.length(); i++) {
            columns.add(new String());

        }
        for (String row : rows) {
            for (int i = 0; i < row.length(); i++) {
                columns.set(i, columns.get(i) + row.charAt(i));
            }
        }

        stringPatternList.add(new StringPattern(rows, columns));
    }

    public void PrintAllStringPatterns() {
        for (StringPattern pattern : stringPatternList) {
            pattern.PrintPattern();
        }
    }
    public void PrintAllBitPatterns() {
        for (BitPattern pattern : bitPatternList) {
            pattern.PrintPattern();
        }
    }
    private int stringMazeToBinaryInt(String mazeString) {
        int num = 0;
        for (char c : mazeString.toCharArray()) {
            num <<= 1;  // Shift left to make space for the new bit
            if (c == '#') {
                num |= 1;  // Set the rightmost bit to 1 if '#' is encountered
            }
        }
        return num;
    }
}
