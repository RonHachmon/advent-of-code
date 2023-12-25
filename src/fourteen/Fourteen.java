package fourteen;

import eleven.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Fourteen {
    private final int satellite_width;
    private final int satellite_height;
    private final Rock[][] satelliteRocks;
    private final Rock[][] initialSatelliteRocks;



    public Fourteen(String path) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            satellite_height = strings.size();
            satellite_width = strings.get(0).length();
            satelliteRocks = new Rock[satellite_height][satellite_width];
            initialSatelliteRocks = new Rock[satellite_height][satellite_width];

            initSatelliteRocks(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // find a repetitive circle in the cycle
    // and jump forward to the nearest point near the amount of cycles
    public void cyclone(int amountOfCycles) {
        Integer cycleLength = null;  // Length of a repeating cycle, if any
        Rock[][] initialSatelliteState = new Rock[satellite_height][satellite_width];  // Snapshot of satellite after width amount of cycle
        int currentCycle = 0;

        while (currentCycle < amountOfCycles) {
            if (currentCycle == satellite_width) {
                copySatelliteRocks(initialSatelliteState);
            }

            if (cycleLength == null) {  // Check for repeating cycle
                if (currentCycle > satellite_width && equalMaze(initialSatelliteState)) {
                    cycleLength = currentCycle - satellite_width;
                    int maxReachableCycle = (int) (Math.floor((amountOfCycles - satellite_width) / cycleLength) - 1);
                    currentCycle = maxReachableCycle * cycleLength + satellite_width;
                }
            }

            satelliteCycle();
            currentCycle++;
        }
    }
    public void Reset()
    {

      for (int i = 0; i < satellite_height; i++) {
          for (int j = 0; j < satellite_width; j++) {
              satelliteRocks[i][j] = new Rock(new Point(i, j), initialSatelliteRocks[i][j].getRockType().getRockSymbol());
          }
      }

    }


    private void satelliteCycle() {
        this.moveSatelliteNorth();
        this.moveSatelliteWest();
        this.moveSatelliteSouth();
        this.moveSatelliteEast();
    }

    private boolean equalMaze(Rock[][] satelliteSnapShot) {
        for (int i = 0; i < satellite_height; i++) {
            for (int j = 0; j < satellite_width; j++) {
                if (satelliteRocks[i][j].getRockType() != satelliteSnapShot[i][j].getRockType())
                    return false;
            }

        }
        return true;

    }
    private void copySatelliteRocks(Rock[][] satelliteSnapShot) {
        for (int i = 0; i < satellite_height; i++) {
            for (int j = 0; j < satellite_width; j++) {
                satelliteSnapShot[i][j] = new Rock(new Point(i, j), this.satelliteRocks[i][j].getRockType().getRockSymbol());
            }
        }
    }


    private void initSatelliteRocks(List<String> strings) {
        for (int i = 0; i < satellite_height; i++) {
            String line = strings.get(i);
            for (int j = 0; j < satellite_width; j++) {
                satelliteRocks[i][j] = new Rock(new Point(i, j), line.charAt(j));
                initialSatelliteRocks[i][j] = new Rock(new Point(i, j), line.charAt(j));
            }
        }
    }

    public void moveSatelliteEast() {
        for (int i = 0; i < satellite_height; i++) {
            for (int j = satellite_width - 2; j >= 0; j--) {
                Rock currentRock = satelliteRocks[i][j];
                if (currentRock.getRockType() == RockType.ROUND) {
                    this.moveRockEast(satelliteRocks[i][j].getRockPoint());
                }

            }
        }
    }

    private void moveRockEast(Point rockPoint) {
        int nextColumn = rockPoint.column();
        for (int i = rockPoint.column() + 1; i < satellite_width; i++) {
            if (satelliteRocks[rockPoint.row()][i].getRockType() == RockType.DIRT) {
                nextColumn = i;
            } else {
                break;
            }

        }
        satelliteRocks[rockPoint.row()][rockPoint.column()].setRockType(RockType.DIRT);
        satelliteRocks[rockPoint.row()][nextColumn].setRockType(RockType.ROUND);

    }

    public void moveSatelliteWest() {
        for (int i = 0; i < satellite_height; i++) {
            for (int j = 1; j < satellite_width; j++) {
                Rock currentRock = satelliteRocks[i][j];
                if (currentRock.getRockType() == RockType.ROUND) {
                    this.moveRockWest(satelliteRocks[i][j].getRockPoint());
                }

            }
        }
    }

    private void moveRockWest(Point rockPoint) {
        int nextColumn = rockPoint.column();
        for (int i = rockPoint.column() - 1; i >= 0; i--) {
            if (satelliteRocks[rockPoint.row()][i].getRockType() == RockType.DIRT) {
                nextColumn = i;
            } else {
                break;
            }

        }
        satelliteRocks[rockPoint.row()][rockPoint.column()].setRockType(RockType.DIRT);
        satelliteRocks[rockPoint.row()][nextColumn].setRockType(RockType.ROUND);

    }


    public void moveSatelliteNorth() {
        for (int i = 1; i < satellite_height; i++) {
            for (int j = 0; j < satellite_width; j++) {
                Rock currentRock = satelliteRocks[i][j];
                if (currentRock.getRockType() == RockType.ROUND) {
                    this.moveRockNorth(satelliteRocks[i][j].getRockPoint());
                }

            }
        }
    }

    private void moveRockNorth(Point rockPoint) {
        int nextRow = rockPoint.row();
        for (int i = rockPoint.row() - 1; i >= 0; i--) {
            if (satelliteRocks[i][rockPoint.column()].getRockType() == RockType.DIRT) {
                nextRow = i;
            } else {
                break;
            }

        }
        satelliteRocks[rockPoint.row()][rockPoint.column()].setRockType(RockType.DIRT);
        satelliteRocks[nextRow][rockPoint.column()].setRockType(RockType.ROUND);

    }

    public void moveSatelliteSouth() {
        for (int i = satellite_height - 2; i >= 0; i--) {
            for (int j = 0; j < satellite_width; j++) {
                Rock currentRock = satelliteRocks[i][j];
                if (currentRock.getRockType() == RockType.ROUND) {
                    this.moveRockSouth(satelliteRocks[i][j].getRockPoint());
                }

            }
        }


    }

    private void moveRockSouth(Point rockPoint) {
        int nextRow = rockPoint.row();
        for (int i = rockPoint.row() + 1; i < satellite_height; i++) {
            if (satelliteRocks[i][rockPoint.column()].getRockType() == RockType.DIRT) {
                nextRow = i;
            } else {
                break;
            }

        }
        satelliteRocks[rockPoint.row()][rockPoint.column()].setRockType(RockType.DIRT);
        satelliteRocks[nextRow][rockPoint.column()].setRockType(RockType.ROUND);

    }

    public void printSatellite() {
        for (int i = 0; i < satellite_height; i++) {
            for (int j = 0; j < satellite_width; j++) {
                System.out.print(satelliteRocks[i][j].getRockType().getRockSymbol());
            }
            System.out.println();
        }
    }

    public int countSatelliteRocks() {
        int sum = 0;
        int circleInCurrentRow ;
        for (int i = 0; i < satellite_height; i++) {
            circleInCurrentRow = this.countCircleInRow(i);
            sum += circleInCurrentRow * (satellite_height - i);

        }
        return sum;
    }

    private int countCircleInRow(int row) {
        int count = 0;
        for (int j = 0; j < satellite_width; j++) {
            Rock rock = satelliteRocks[row][j];
            if (rock.getRockType() == RockType.ROUND) {
                count++;
            }
        }

        return count;
    }

}
