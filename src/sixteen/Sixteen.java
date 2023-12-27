package sixteen;

import eleven.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Sixteen {
    private final List<List<ContraptionPoint>> contraption = new ArrayList<>();
    private Stack<ContraptionPoint> contraptionPointStack = new Stack<>();


    public Sixteen(String path) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            extractContraptionList(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractContraptionList(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            contraption.add(new ArrayList<>());
            List<ContraptionPoint> contraptionPoints = contraption.get(i);
            for (int j = 0; j < s.length(); j++) {
                contraptionPoints.add(new ContraptionPoint(new Point(i, j), s.charAt(j)));
            }
        }
    }

    public int MaxEnergized() {
        int max = 0;
        for (int i = -1; i < contraption.size() + 1; i++) {
            for (int j = -1; j < contraption.get(0).size() + 1; j++) {
                Point enterPoint = new Point(i, j);
                List<ContraptionPoint> validContraptionPoints = getAdjacentValidContraptionPoints(enterPoint);
                for (ContraptionPoint contraptionPoint : validContraptionPoints) {
                    int totalEnergized = getTotalEnergized(enterPoint, contraptionPoint);
                    max = Math.max(max, totalEnergized);
                    this.reset();
                }
            }

        }
        return max;

    }
    public int energizedTopLeft()
    {
        Point enterPoint = new Point(0, -1);
        ContraptionPoint contraptionPoint = this.contraption.get(0).get(0);
        int totalEnergized = getTotalEnergized(enterPoint, contraptionPoint);
        this.reset();
        return totalEnergized;

    }

    private List<ContraptionPoint> getAdjacentValidContraptionPoints(Point enterPoint) {
        List<ContraptionPoint> validContraptionPoints = new ArrayList<>();
        Point bottom = new Point(enterPoint.row() + 1, enterPoint.column());
        Point right = new Point(enterPoint.row(), enterPoint.column() + 1);
        Point left = new Point(enterPoint.row(), enterPoint.column() - 1);
        Point top = new Point(enterPoint.row() - 1, enterPoint.column());
        if (isInContraptionBounds(bottom)) {
            validContraptionPoints.add(this.contraption.get(bottom.row()).get(bottom.column()));
        }
        if (isInContraptionBounds(right)) {
            validContraptionPoints.add(this.contraption.get(right.row()).get(right.column()));
        }
        if (isInContraptionBounds(left)) {
            validContraptionPoints.add(this.contraption.get(left.row()).get(left.column()));
        }
        if (isInContraptionBounds(top)) {
            validContraptionPoints.add(this.contraption.get(top.row()).get(top.column()));
        }
        return validContraptionPoints;
    }

    private void reset() {
        for (List<ContraptionPoint> contraptionPoints : contraption) {
            for (ContraptionPoint contraptionPoint : contraptionPoints) {
                contraptionPoint.Reset();
            }
        }
    }

    private int getTotalEnergized(Point enterPoint, ContraptionPoint startingPoint) {
        int count = 0;
        this.crossBeamPath(enterPoint, startingPoint);
        for (List<ContraptionPoint> contraptionPoints : contraption) {
            for (ContraptionPoint contraptionPoint : contraptionPoints) {
                if (contraptionPoint.isEnergized()) {
                    count++;
                }
            }
        }
        return count;
    }

    private void crossBeamPath(Point enterPoint, ContraptionPoint startingPoint) {

        addInitialStartingPoint(enterPoint, startingPoint);
        while (!contraptionPointStack.empty()) {
            ContraptionPoint current = contraptionPointStack.pop();
            extractExitPointsFromCurrentPoint(current);
            current.setEnergized(true);
        }

    }

    private void extractExitPointsFromCurrentPoint(ContraptionPoint current) {
        Point currentPoint = current.getCurrentPoint();
        List<Point> exitPoints = current.getExitPoints();
        for (Point exitPoint : exitPoints) {
            if (isInContraptionBounds(exitPoint)) {

                ContraptionPoint addedContraption = contraption.get(exitPoint.row()).get(exitPoint.column());
                addedContraption.setEnterPoints(currentPoint);
                contraptionPointStack.add(addedContraption);
            }
        }
    }

    private void addInitialStartingPoint(Point enterPoint, ContraptionPoint startingPoint) {

        startingPoint.setEnterPoints(enterPoint);
        contraptionPointStack.add(startingPoint);
    }


    public void PrintContraption() {
        for (List<ContraptionPoint> contraptionPoints : contraption) {
            for (ContraptionPoint contraptionPoint : contraptionPoints) {
                if (contraptionPoint.isEnergized()) {
                    System.out.print("#");
                } else {
                    System.out.print(contraptionPoint.getContraptionType().getContraptionType());
                }

            }
            System.out.println();
        }

    }

    private boolean isInContraptionBounds(Point exitPoint) {
        return exitPoint.row() >= 0 && exitPoint.row() < contraption.size() && exitPoint.column() >= 0 && exitPoint.column() < contraption.get(0).size();
    }
}
