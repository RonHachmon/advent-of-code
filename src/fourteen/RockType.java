package fourteen;

public enum RockType {
    ROUND('O'), SQUARE('#'), DIRT('.');


    private final char rockSymbol;


    RockType(char o) {
        this.rockSymbol=o;
    }
    public char getRockSymbol() {
        return rockSymbol;
    }

    public static RockType determineShape(char symbol) {
        return switch (symbol) {
            case '#' -> RockType.SQUARE;
            case 'O' -> RockType.ROUND;
            case '.' -> RockType.DIRT;
            default -> null; // Handle invalid symbols
        };
    }
}


