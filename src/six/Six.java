package six;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Six {
    public static final String WHITE_SPACE_REGEX = "\\s+";
    private final List<Race> raceList=new ArrayList<>();
    private  Race singleRace;

    public Six(String path) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            String[] time = allLines.get(0).split(WHITE_SPACE_REGEX);
            String[] distance =allLines.get(1).split(WHITE_SPACE_REGEX);
            initRaces(time,distance);
            initRace(time,distance);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing matrix: " + e.getMessage());
        }
    }

    private void initRace(String[] time, String[] distance) {
        String totalTime=new String();
        String totalDistance=new String();
        for (int i = 1; i <time.length ; i++) {
            totalTime+=time[i];
            totalDistance+=distance[i];
        }
        singleRace=new Race(Long.parseLong(totalTime),Long.parseLong(totalDistance));


    }

    private void initRaces(String[] time, String[] distance) {
        for (int i = 1; i <time.length ; i++) {
            Race race=new Race(Integer.parseInt(time[i]),Integer.parseInt(distance[i]));
            this.raceList.add(race);
        }
    }
    public int NumberOfWaysToWinBigRace()
    {
        return singleRace.NumberOfWaysToWin();

    }
    public int NumberOfWaysToWinForAllRacesMultiplied()
    {
        int sum=1;
        for (int i = 0; i <raceList.size() ; i++) {
            Race race = raceList.get(i);
            int numberOfWaysToWin = race.NumberOfWaysToWin();
            System.out.println("Race "+ i+ " ways to win "+numberOfWaysToWin);
            sum*= numberOfWaysToWin;
        }
        return sum;
    }
    public void PrintRaces()
    {
        for (int i = 0; i <raceList.size() ; i++) {
            Race race = raceList.get(i);
            System.out.println("Race "+ i+ " time "+race.getTime()+" distance "+race.getDistance());
        }
    }

}
