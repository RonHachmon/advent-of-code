import four.Card;
import four.Four;
import one.One;
import three.Three;
import two.Two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        runFour();

//        System.out.println(Math.pow(2,-1));
//        System.out.println(Math.pow(2,3));
//        Card card=new Card("Card   1: 72 42 34  7 30  3 25 63 99 15 | 63 30 64 15 72 55 73 32 75 41 37 77 49 51 95 16 25  3 92 18 87  2 71 28 10");
//        System.out.println(card.getCardID());
//        String a="Card   1: 72 42 34  7 30  3 25 63 99 15 | 63 30 64 15 72 55 73 32 75 41 37 77 49 51 95 16 25  3 92 18 87  2 71 28 10";
//        String[] c = a.split(":");
//        String cardID=c[0].split("\\s+")[1];
//        System.out.println(cardID);

//        String [] b=a.split("\\s+");
//        for (String num:b
//
//             ) {
//            System.out.println(num);
//
//        }
        //System.out.println(b);
        //runThree();

        //runOne();
        //runTwo();
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