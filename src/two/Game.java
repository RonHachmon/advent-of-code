package two;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int gameId=0;
    List<Cube>cubes=new ArrayList<>();
    public Game(String gameInfo)
    {
        initializeCubeArray();

        String[] splitGameInfo = gameInfo.split(":");
        extractGameId(splitGameInfo[0]);

        String[] gameSession = splitGameInfo[1].split(";");
        for (String session: gameSession) {
            this.extractCubesValues(session);
        }
    }
    public Cube getCubeByColor(CubeColor cubeColor)
    {
        for (int i = 0; i <cubes.size() ; i++) {
            if(cubes.get(i).getCubeColor()==cubeColor)
            {
                return cubes.get(i);
            }
        }
        return null;

    }

    private void initializeCubeArray() {
        for (CubeColor cubeColors : CubeColor.values()) {
            Cube cube=new Cube();
            cube.setCubeColor(cubeColors);
            cube.setAmountOfCubes(0);
            cubes.add(cube);
        }
    }

    private void extractCubesValues(String session) {
        String[] colorsAndValues = session.split(",");
        for (String colorsAndValue: colorsAndValues) {
            String[] s = colorsAndValue.split(" ");
            int value = Integer.parseInt(s[1]);
            CubeColor color = CubeColor.valueOf(s[2].toUpperCase());
            this.setColor(color,value);
        }
    }

    private void setColor(CubeColor color,int value) {
        for (int i = 0; i <cubes.size() ; i++) {
            if(cubes.get(i).getCubeColor()==color)
            {
                cubes.get(i).setAmountOfCubes(value);
            }

        }
    }

    private void extractGameId(String gameID)
    {
        String s = gameID.split(" ")[1];
        int id = Integer.parseInt(s);
        setGameId(id);

    }
    public int getGamePower()
    {
        int gamePower=1;
        for (int i = 0; i <cubes.size() ; i++) {
            gamePower*=cubes.get(i).getAmountOfCubes();

        }
        return gamePower;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
