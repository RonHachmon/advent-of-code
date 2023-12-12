package two;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Two {
    private List<Game> gameList = new ArrayList<>();
    private final int TOTAL_RED = 12;
    private final int TOTAL_GREEN = 13;
    private final int TOTAL_BLUE = 14;
    private int sumOfValidGameID = 0;

    public Two(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String gameDetails = reader.readLine();
            while (gameDetails != null) {
                Game game = new Game(gameDetails);
                gameList.add(game);
                gameDetails = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int sumOfGamePowers() {
        int sum=0;
        for (Game game : this.gameList) {
            sum+=game.getGamePower();
        }
        return sum;
    }

    public int calculateValidIDS() {
        for (Game game : this.gameList) {
            if (isValidGame(game)) {
                sumOfValidGameID += game.getGameId();
            }
        }
        return sumOfValidGameID;
    }

    private boolean isValidGame(Game game) {

        return game.getCubeByColor(CubeColor.BLUE).getAmountOfCubes() <= TOTAL_BLUE &&
                game.getCubeByColor(CubeColor.GREEN).getAmountOfCubes() <= TOTAL_GREEN &&
                game.getCubeByColor(CubeColor.RED).getAmountOfCubes() <= TOTAL_RED;
    }
}
