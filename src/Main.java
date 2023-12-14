import five.Five;
import four.Four;
import one.One;
import six.Six;
import three.Three;
import two.Two;

public class Main {
    public static void main(String[] args)  {
        Six six=new Six("./src/six/inputs/input");
        System.out.println(six.NumberOfWaysToWinBigRace());
        //six.PrintRaces();
        System.out.println(six.NumberOfWaysToWinForAllRacesMultiplied());

        //runFive();

        //runFour();
        //runThree();
        //runOne();
        //runTwo();
    }

    private static void runFive() {
        Five five =new Five("./src/five/inputs/input");
        long nearestLocation1 = five.GetNearestLocation();
        long nearestLocation2 = five.getNearestLocationPartTwo();
        System.out.println(nearestLocation2);
    }

    private static void runFour() {
        Four four=new Four("./src/four/inputs/input");
        System.out.println(four.CalculatePoints());
        System.out.println(four.CalculateTotalCards());
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