package eight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class Eight {
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';
    private String instruction;
    Map<String, PathSplit> pointToPathSplitMap = new HashMap<>();
    private static String START_POINT = "AAA";
    private static String END_POINT = "ZZZ";

    public Eight(String path) {
        try {
            List<String> stringList = Files.readAllLines(Path.of(path));
            instruction = stringList.get(0);
            this.extractPointToPathMap(stringList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long totalPathToEndPathPartOne() {
        Predicate<String> reachedEndGoal = s -> s.equals(END_POINT);
        return totalPathToEndPath(START_POINT, reachedEndGoal);
    }

    public long totalPathToEndPathPartTwo() {
        List<Long> timeToEnd = new ArrayList<>();
        Predicate<String> reachedEndGoal = s -> s.endsWith("Z");
        List<String> staringPath = new ArrayList<>();
        this.getAllStaringPoint(staringPath);
        findTimeToEndForEachStartPath(timeToEnd, reachedEndGoal, staringPath);

        long total=timeToEnd.get(0);
        for (int i = 1; i <timeToEnd.size() ; i++) {
            total=lcd(total,timeToEnd.get(i));
        }

        return total;
    }

    private void findTimeToEndForEachStartPath(List<Long> timeToEnd, Predicate<String> reachedEndGoal, List<String> staringPath) {
        for (int i = 0; i < staringPath.size() ; i++) {
            timeToEnd.add(totalPathToEndPath(staringPath.get(i), reachedEndGoal));

        }
    }



    private long totalPathToEndPath(String startPoint, Predicate<String> reachedEndGoal) {
        int count = 0;
        int directionIndex = 0;
        String currentPath = startPoint;
        while (!reachedEndGoal.test(currentPath)) {
            directionIndex = directionIndex % instruction.length();
            currentPath = moveToNextPath(directionIndex, currentPath);
            directionIndex++;
            count++;
        }
        return count;
    }


    private void getAllStaringPoint(List<String> staringPath) {
        for (Map.Entry<String, PathSplit> entry : pointToPathSplitMap.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("A")) {
                staringPath.add(key);
            }
        }
    }



    private String moveToNextPath(int directionIndex, String currentPathAtIndex) {
        PathSplit pathSplit = pointToPathSplitMap.get(currentPathAtIndex);
        if (instruction.charAt(directionIndex) == LEFT) {
            currentPathAtIndex = pathSplit.getLeftPath();
        }
        if (instruction.charAt(directionIndex) == RIGHT) {
            currentPathAtIndex = pathSplit.getRightPath();
        }
        return currentPathAtIndex;
    }


    private void extractPointToPathMap(List<String> stringList) {
        for (int i = 2; i < stringList.size(); i++) {
            String pointAndSplitPath = stringList.get(i);
            String[] pointAndSplitPathSplit = pointAndSplitPath.split(" = ");
            String path = pointAndSplitPathSplit[0];
            PathSplit pathSplit = extractPathSplit(pointAndSplitPathSplit[1]);
            pointToPathSplitMap.computeIfAbsent(pointAndSplitPathSplit[0], map -> pathSplit);
        }
    }

    private PathSplit extractPathSplit(String pathSplit) {
        int commaIndex = pathSplit.indexOf(',');
        String leftPath = pathSplit.substring(1, commaIndex);
        String rightPath = pathSplit.substring(commaIndex + 1, pathSplit.length() - 1).trim();
        return new PathSplit(leftPath, rightPath);
    }

    public static long lcd(long a, long b)
    {
        return (a*b)/gcd(a,b);
    }
    public static long gcd(long a, long b)
    {
        if (a == 0)
            return b;

        return gcd(b % a, a);
    }
}
