package three;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SchematicLine  {
    private final String line;
    private final int lineIndex;
    public SchematicLine(String line,int lineIndex )
    {
        this.lineIndex=lineIndex;
        this.line=line;
    }

    public List<Point> findSymbolInRow(int startIndex, int endIndex, Predicate<Character> symbolPredicate) {
        List<Point> points=new ArrayList<>();

        for (int i = startIndex; i <= endIndex; i++) {
            if (i > 0 && i <this.line.length() ) {
                char c = this.charAt(i);
                if (symbolPredicate.test(c)) {
                    Point point=new Point(lineIndex,i);
                    points.add(point);
                }
            }
        }
        return points;

    }

    public List<Point> findSymbolInSides(int startIndex, int endIndex, Predicate<Character> symbolPredicate) {
        List<Point> points=new ArrayList<>();

        if (startIndex != 0) {
            char c = this.charAt(startIndex - 1);
            if (symbolPredicate.test(c)) {
                Point point=new Point(lineIndex,startIndex - 1);
                points.add(point);

            }
        }
        if (endIndex + 1 < this.line.length()) {
            char c = this.charAt(endIndex + 1);
            if (symbolPredicate.test(c)) {
                Point point=new Point(lineIndex,endIndex + 1);
                points.add(point);
            }
        }
        return points;
    }


    public char charAt(int j) {

        return this.line.charAt(j);
    }

    public String substring(int startIndex, int endIndex) {
        return this.line.substring(startIndex,endIndex);
    }
}
