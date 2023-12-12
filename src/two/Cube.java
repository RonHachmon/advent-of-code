package two;

public class Cube implements CubeInterface {
    private CubeColor cubeColor;
    private int amountOfCubes=0;

    @Override
    public void setCubeColor(CubeColor cubeColor) {
        this.cubeColor = cubeColor;

    }

    @Override
    public void setAmountOfCubes(int i) {
        amountOfCubes = i > amountOfCubes ? i : amountOfCubes;

    }


    public CubeColor getCubeColor() {
        return cubeColor;
    }

    public int getAmountOfCubes() {
        return amountOfCubes;
    }
}
