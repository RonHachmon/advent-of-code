package three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Three {
    public static final int NOT_FOUND = -1;
    private final HashMap<Point,List<Integer>> astrixPointToAdjacentNumbers= new HashMap<>();
    private SchematicLine[] matrix;
    private int width;
    private int length;
    private int sum = 0;

    public Three(String path) {
        try {
            initMatrix(path);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing matrix: " + e.getMessage());
        }
    }

    private void initMatrix(String path) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get(path));
        matrix = new SchematicLine[allLines.size()];
        length = allLines.size();
        width = allLines.get(0).length();
        for (int i = 0; i < allLines.size(); i++) {
            matrix[i] = new SchematicLine(allLines.get(i),i);
        }
    }
    public long getGearsPower()
    {
        long sum=0;
        for (Map.Entry<Point, List<Integer>> entry : astrixPointToAdjacentNumbers.entrySet()) {
            List<Integer> numbers = entry.getValue();
            if(numbers.size()==2)
            {
                sum+=(long)numbers.get(0)*numbers.get(1);
            }
        }
        return sum;

    }
    public void printHashMap() {
        for (Map.Entry<Point, List<Integer>> entry : astrixPointToAdjacentNumbers.entrySet()) {
            Point point = entry.getKey();
            List<Integer> numbers = entry.getValue();
            if(numbers.size()==2) {
                System.out.println("Point: row: " + point.getRow() + " column: " + point.getColumn());
                System.out.println("Adjacent Numbers: " + numbers);
                System.out.println("------------------------------");
            }
        }
    }

    public int FindNumbersCoordinates() {
        for (int row = 0; row < length; row++) {
            findNumbersInRow(row);
        }
        return this.sum;

    }

    private void findNumbersInRow(int row) {
        int endOfNumberInRow;
        int startOfNumberInRow=NOT_FOUND;
        for (int j = 0; j < width; j++) {
            if (Character.isDigit(matrix[row].charAt(j))) {
                if (startOfNumberInRow == NOT_FOUND) {
                    startOfNumberInRow = j;
                }
            } else {
                if (startOfNumberInRow != NOT_FOUND) {
                    endOfNumberInRow = j-1;
                    if (isNumberAnEngine(startOfNumberInRow, endOfNumberInRow, row)) {
                        handleEngineNumber(startOfNumberInRow, endOfNumberInRow, row);
                    }
                    startOfNumberInRow = NOT_FOUND;
                }
            }

        }
        if (startOfNumberInRow != NOT_FOUND) {
            if (isNumberAnEngine(startOfNumberInRow, width - 1, row)) {
                handleEngineNumber(startOfNumberInRow, width - 1, row);
            }
        }

    }

    private void handleEngineNumber(int startIndex, int endIndex, int row) {
        String stringNum = matrix[row].substring(startIndex, endIndex +1);
        int num = Integer.parseInt(stringNum);
        addNumToSum(num);
        List<Point> adjacentAstrix = extractNumIfAdjacentToAstrix(startIndex, endIndex, row);
        addAdjacentNumsToAstrix(num, adjacentAstrix);
    }

    private void addAdjacentNumsToAstrix(int num, List<Point> adjacentAstrix) {
        for (Point astrixPoint : adjacentAstrix) {
            this.astrixPointToAdjacentNumbers.computeIfAbsent(astrixPoint, key -> new ArrayList<>());
            List<Integer> list = this.astrixPointToAdjacentNumbers.get(astrixPoint);
            list.add(num);
        }
    }




    private List<Point> extractNumIfAdjacentToAstrix(int startIndex, int endIndex, int row) {
        Predicate<Character> isAstrix= character -> character == '*';
        List<Point> points=new ArrayList<>();
        points.addAll(scanTopRow(row, startIndex - 1, endIndex + 1,isAstrix));
        points.addAll(scanSides(row, startIndex, endIndex,isAstrix));
        points.addAll(scanBottomRow(row , startIndex - 1, endIndex + 1,isAstrix));
        return points;
    }
    private boolean isNumberAnEngine(int startIndex, int endIndex, int row) {
        Predicate<Character> isSymbol= character -> !Character.isDigit(character) && character != '.';
        return  scanTopRow(row, startIndex - 1, endIndex + 1,isSymbol).size()>0 ||
                scanSides(row, startIndex, endIndex,isSymbol).size()>0 ||
                scanBottomRow(row , startIndex - 1, endIndex + 1,isSymbol).size()>0;
    }

    private List<Point> scanBottomRow(int row, int startIndex, int endIndex, Predicate<Character> predicate) {
        List<Point> points=new ArrayList<>();
        if (row+1 >= length) {
            return points;
        }

        return matrix[row+1].findSymbolInRow(startIndex,endIndex,predicate);
    }

    private List<Point> scanSides(int row, int startIndex, int endIndex, Predicate<Character> predicate) {
        return matrix[row].findSymbolInSides(startIndex,endIndex,predicate);
    }

    private List<Point> scanTopRow(int row, int startIndex, int endIndex, Predicate<Character> predicate) {
        List<Point> points=new ArrayList<>();
        if (row-1 < 0) {
            return points;
        }
        return matrix[row-1].findSymbolInRow(startIndex,endIndex,predicate);
    }
    private void addNumToSum(int num) {

        this.sum += num;
    }
}
