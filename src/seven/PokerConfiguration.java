package seven;

public class PokerConfiguration {
    public final static String CARD_STRENGTH_ORDER="23456789TJQKA";
    public final static String CARD_STRENGTH_ORDER_WITH_JOKER="J23456789TQKA";
    private static boolean withJoker =false;

    public static boolean IsWithJoker() {
        return withJoker;
    }
    public static void SetIsWithJoker(boolean isWithJoker) {
        withJoker=isWithJoker;
    }
}
