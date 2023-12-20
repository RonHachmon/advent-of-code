package eleven;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Eleven {
    public static final char GALAXIES = '#';
    public final int WIDTH;
    public final int LENGTH;
    private List<Point> galaxiesList = new ArrayList<>();

    private List<Integer> emptyColumnIndexes = new ArrayList<>();
    private List<Integer> emptyRowsIndexes = new ArrayList<>();
    private int totalDuplicate = 0;

    public Eleven(String path) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            LENGTH = allLines.size();
            WIDTH = allLines.get(0).length();
            this.countEmptyColumn(allLines);
            this.countEmptyRows(allLines);
            this.extractGalaxies(allLines);

        } catch (IOException e) {
            throw new RuntimeException("Error initializing matrix: " + e.getMessage());
        }
    }



    public BigInteger SumOfTotalShortestPath(int duplicateValue) {
        long total = 0;
        for (int i = 0; i < galaxiesList.size(); i++) {
            Point currentGalaxiePoint = galaxiesList.get(i);
            for (int j = i + 1; j < this.galaxiesList.size(); j++) {
                Point nextGalaxiePoint = galaxiesList.get(j);
                long shortestPath = this.getShortestPath(currentGalaxiePoint, nextGalaxiePoint, duplicateValue);
                total += shortestPath;
            }

        }
        if (duplicateValue > 1) {
            duplicateValue--;
        }
        String stringDuplicateValue = Integer.toString(duplicateValue);
        BigInteger normalPath=BigInteger.valueOf(total);
        BigInteger totalDuplicate=BigInteger.valueOf(this.totalDuplicate);
        return totalDuplicate.multiply(new BigInteger(stringDuplicateValue)).add(normalPath);
        //return String.format("%d + (%d * %d)", total, this.totalDuplicate, duplicateValue);

    }

    private long getShortestPath(Point currentGalaxiePoint, Point nextGalaxiePoint, long duplicateValue) {

        long rowSteps = totalRowStep(currentGalaxiePoint, nextGalaxiePoint);
        long columnSteps = totalColumnStep(currentGalaxiePoint, nextGalaxiePoint);

        int totalDuplicateRows = this.countDuplicateRowsBetweenRows(currentGalaxiePoint.row(), nextGalaxiePoint.row());
        int totalDuplicateColumn = this.countDuplicateColumnBetweenColumn(currentGalaxiePoint.column(), nextGalaxiePoint.column());
        this.totalDuplicate += totalDuplicateRows + totalDuplicateColumn;

        return (rowSteps + columnSteps);
    }

    private long totalColumnStep(Point currentGalaxiePoint, Point nextGalaxiePoint) {
        int currentGalaxyColumn = currentGalaxiePoint.column();
        int nextGalaxyColumn = nextGalaxiePoint.column();
        return Math.abs(currentGalaxyColumn - nextGalaxyColumn);
    }

    private long totalRowStep(Point currentGalaxiePoint, Point nextGalaxiePoint) {
        int currentGalaxyRow = currentGalaxiePoint.row();
        int nextGalaxyRow = nextGalaxiePoint.row();
        return Math.abs(nextGalaxyRow - currentGalaxyRow);
    }

    private int countDuplicateColumnBetweenColumn(int currentGalaxyColumn, int nextGalaxyColumn) {
        int total = 0;
        int start = Math.min(currentGalaxyColumn, nextGalaxyColumn);
        int end = Math.max(currentGalaxyColumn, nextGalaxyColumn);
        for (int i = start + 1; i < end; i++) {
            if (this.emptyColumnIndexes.contains(i)) {
                total++;
            }
        }
        return total;
    }

    private int countDuplicateRowsBetweenRows(int currentGalaxyRow, int nextGalaxyRow) {
        int total = 0;
        int start = Math.min(currentGalaxyRow, nextGalaxyRow);
        int end = Math.max(currentGalaxyRow, nextGalaxyRow);
        for (int i = start + 1; i < end; i++) {
            if (this.emptyRowsIndexes.contains(i)) {
                total++;
            }

        }
        return total;
    }


    private void countEmptyRows(List<String> allLines) {
        for (int row = 0; row < LENGTH; row++) {
            String currentRow = allLines.get(row);
            if (isEmptyRow(currentRow)) {
                this.emptyRowsIndexes.add(row);
            }
        }
    }

    private void extractGalaxies(List<String> allLines) {
        for (int row = 0; row < LENGTH; row++) {
            String currentRow = allLines.get(row);
            for (int column = 0; column < WIDTH; column++) {
                if (currentRow.charAt(column) == GALAXIES) {
                    this.galaxiesList.add(new Point(row, column));
                }
            }

        }
    }

    private boolean isEmptyRow(String currentRow) {
        for (int column = 0; column < WIDTH; column++) {
            if (currentRow.charAt(column) == GALAXIES) {
                return false;
            }
        }
        return true;
    }

    private void countEmptyColumn(List<String> allLines) {
        for (int column = 0; column < WIDTH; column++) {
            if (isEmptyColumn(column, allLines)) {
                this.emptyColumnIndexes.add(column);
            }
        }
    }

    private boolean isEmptyColumn(int column, List<String> allLines) {
        for (int row = 0; row < LENGTH; row++) {
            String currentRow = allLines.get(row);
            char chatAtColumn = currentRow.charAt(column);
            if (chatAtColumn == GALAXIES) {
                return false;
            }
        }
        return true;
    }

}
