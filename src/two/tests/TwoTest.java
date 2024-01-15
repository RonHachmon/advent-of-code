package two.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import two.Two;

import static org.junit.jupiter.api.Assertions.*;

class TwoTest {
    @Test
    @DisplayName("CalculateValidID unit testing for test file and input file")
    void TwoCalculateValidIDSUnitTest() {
        Two two = new Two("./src/two/inputs/test");
        assertEquals(8, two.calculateValidIDS());
        two = new Two("./src/two/inputs/input");
        assertEquals(2317, two.calculateValidIDS());


    }

    @Test
    @DisplayName("SumOfGamePower unit testing for test file and input file")
    void TwoSumOfGamePowersUnitTest() {
        Two two = new Two("./src/two/inputs/test");
        assertEquals(2286, two.sumOfGamePowers());
        two = new Two("./src/two/inputs/input");
        assertEquals(74804, two.sumOfGamePowers());


    }

}