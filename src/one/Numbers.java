package one;

enum Numbers {
    ONE {
        @Override
        int numericValue() {
            return 1;
        }

        @Override
        String stringValue() {
            return "one";
        }
    },
    TWO {
        @Override
        int numericValue() {
            return 2;
        }

        @Override
        String stringValue() {
            return "two";
        }
    },
    THREE {
        @Override
        int numericValue() {
            return 3;
        }

        @Override
        String stringValue() {
            return "three";
        }
    },
    FOUR {
        @Override
        int numericValue() {
            return 4;
        }

        @Override
        String stringValue() {
            return "four";
        }
    },
    FIVE {
        @Override
        int numericValue() {
            return 5;
        }

        @Override
        String stringValue() {
            return "five";
        }
    },
    SIX {
        @Override
        int numericValue() {
            return 6;
        }

        @Override
        String stringValue() {
            return "six";
        }
    },
    SEVEN {
        @Override
        int numericValue() {
            return 7;
        }

        @Override
        String stringValue() {
            return "seven";
        }
    },
    EIGHT {
        @Override
        int numericValue() {
            return 8;
        }

        @Override
        String stringValue() {
            return "eight";
        }
    },
    NINE {
        @Override
        int numericValue() {
            return 9;
        }

        @Override
        String stringValue() {
            return "nine";
        }
    };

    abstract int numericValue();
    abstract String stringValue();
}

