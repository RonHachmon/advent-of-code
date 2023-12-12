package one;

public class NumberNames {
    private static final Numbers[] THREE_LENGTH_NAMES = {Numbers.ONE, Numbers.TWO, Numbers.SIX};
    private static final Numbers[] FOUR_LENGTH_NAMES = {Numbers.FOUR, Numbers.FIVE, Numbers.NINE};
    private static final Numbers[] FIVE_LENGTH_NAMES = {Numbers.SEVEN, Numbers.EIGHT, Numbers.THREE};

    public static int stringIsNumber(String str) {
        int res = -1;
        if (str.length() == 3) {
            return numberOfLengthThree(str);
        } else if (str.length() == 4) {
            return numberOfLengthFour(str);
        } else if (str.length() == 5) {
            return numberOfLengthFive(str);
        }
        return res;
    }

    private static int numberOfLengthThree(String str) {
        for (Numbers num : THREE_LENGTH_NAMES) {
            if (str.equals(num.stringValue())) {
                return num.numericValue();
            }
        }
        return -1;
    }

    private static int numberOfLengthFour(String str) {
        for (Numbers num : FOUR_LENGTH_NAMES) {
            if (str.equals(num.stringValue())) {
                return num.numericValue();
            }
        }
        return -1;
    }

    private static int numberOfLengthFive(String str) {
        for (Numbers num : FIVE_LENGTH_NAMES) {
            if (str.equals(num.stringValue())) {
                return num.numericValue();
            }
        }
        return -1;
    }

}
