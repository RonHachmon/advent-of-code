import one.One;
import three.Three;
import two.Two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        runThree();

        //runOne();
        //runTwo();
    }

    private static void runThree() {
        Three three=new Three("./src/three/inputs/input");
        System.out.println(three.FindNumbersCoordinates());
        System.out.println(three.getGearsPower());
    }

    private static void runOne() {
        One one = new One("./src/one/inputs/input");
        one.CalculateResult();
        System.out.println(one.GetRes());
    }

    private static void runTwo() {
        Two two=new Two("./src/two/inputs/input");

        System.out.println(two.calculateValidIDS());
        System.out.println(two.sumOfGamePowers());
    }
}