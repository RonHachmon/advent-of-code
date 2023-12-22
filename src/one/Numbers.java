package one;

enum Numbers {
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine");

    private final int numericValue;
    private final String stringValue;

    Numbers(int numericValue, String stringValue) {
        this.numericValue = numericValue;
        this.stringValue = stringValue;
    }

    int numericValue() {
        return numericValue;
    }

    String stringValue() {
        return stringValue;
    }
}
