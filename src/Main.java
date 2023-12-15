import five.Five;
import four.Four;
import one.One;
import seven.Seven;
import six.Six;
import three.Three;
import two.Two;

public class Main {
    public static void main(String[] args)  {
        Seven seven=new Seven("./src/seven/inputs/input");
        //seven.PrintHands();
        System.out.println(seven.CalculateTotalMoney());
        //runSix();

        //runFive();

        //runFour();
        //runThree();
        //runOne();
        //runTwo();
    }

    private static void runSix() {
        Six six=new Six("./src/six/inputs/input");
        System.out.println(six.NumberOfWaysToWinBigRace());
        System.out.println(six.NumberOfWaysToWinForAllRacesMultiplied());
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