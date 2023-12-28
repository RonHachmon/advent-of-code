package one.tests;

import one.One;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneTest {

    @Test
    @DisplayName(" One Unit Test - Test File Path: ./src/one/inputs/test.txt - Expected Result: 281")
    void OneUnitTest() {
        One one = new One("./src/one/inputs/test");
        one.CalculateResult();
        assertEquals(281, one.GetRes());

    }

    @Test
    @DisplayName(" One Unit Test - Test File Path: ./src/one/inputs/input.txt - Expected Result: 54845")
    void OneUnitInput() {
        One one = new One("./src/one/inputs/input");
        one.CalculateResult();
        assertEquals(54845, one.GetRes());
    }
}