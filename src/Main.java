import eight.Eight;
import eleven.Eleven;
import five.Five;
import four.Four;
import nine.Nine;
import one.One;
import seven.Seven;
import six.Six;
import ten.Ten;
import three.Three;
import two.Two;

public class Main {

    public static final String INPUT_PATH = "./src/%s/inputs/input";

    public static void main(String[] args)  {

        runEleven();


        //runSix();
        //runTen();


        //runNine();

        //runEight();
        //runSeven();
        //runSix();

        //runFive();

        //runFour();
        //runThree();
        //runOne();
        //runTwo();
    }

    private static void runEleven() {
        String question="eleven";
        String path = String.format(INPUT_PATH, question);
        Eleven eleven = new Eleven(path);
        System.out.println(eleven.SumOfTotalShortestPath(1));
        System.out.println(eleven.SumOfTotalShortestPath(1000000));
    }

    private static void runTen() {
        String question="ten";
        String path = String.format(INPUT_PATH, question);
        Ten ten = new Ten(path);
        System.out.println(ten.FindCircle());
        System.out.println(ten.totalTrapInCircle());
    }

    private static void runNine() {
        String question="nine";
        String path = String.format(INPUT_PATH, question);
        Nine nine = new Nine(path);
        System.out.println(nine.CalculatePreviousSequenceSum());
        System.out.println(nine.CalculateNextSequenceSum());
    }

    private static void runEight() {
        String question="eight";
        String path = String.format(INPUT_PATH, question);

        Eight eight=new Eight(path);
        System.out.println(eight.totalPathToEndPathPartOne());
        System.out.println(eight.totalPathToEndPathPartTwo());
    }


    private static void runSeven() {
        String question="seven";

        String path = String.format(INPUT_PATH, question);
        Seven seven=new Seven(path);
        seven.isWithJoker(false);
        System.out.println(seven.CalculateTotalMoney());
        seven.isWithJoker(true);
        System.out.println(seven.CalculateTotalMoney());
    }

    private static void runSix() {
        String question="six";
        String path = String.format(INPUT_PATH, question);
        Six six=new Six(path);
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