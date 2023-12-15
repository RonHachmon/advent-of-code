package seven;

public class Hand implements Comparable<Hand> {
    public static String CARD_STRENGTH_ORDER="23456789TJQKA";
    public static String CARD_STRENGTH_ORDER_WITH_JOKER="J23456789TQKA";
    private static boolean staticWithJoker=true;
    private final String hand;
    private final int bid;
    private final HandType handType;


    public static void setWithJoker(boolean withJoker) {
        staticWithJoker = withJoker;
    }

    public Hand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
        this.handType = extractHandType();

    }

    private HandType extractHandType() {
        if(staticWithJoker)
        {
            return HandType.identifyHandTypeWithJasJoker(hand);
        }
        else
        {
            return HandType.identifyHandTypeWithOutJoker(hand);
        }


    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public HandType getHandType() {
        return handType;
    }

    @Override
    public int compareTo(Hand otherHand) {
        if(this.handType.handValue()>otherHand.handType.handValue())
        {
            return 1;
        }
        if(this.handType.handValue()<otherHand.handType.handValue())
        {
            return -1;
        }
        if(this.handType.handValue()==otherHand.handType.handValue())
        {
            for (int i = 0; i <otherHand.getHand().length() ; i++) {
                char otherHandChar = otherHand.getHand().charAt(i);
                char thisHandChar = this.getHand().charAt(i);
                if(otherHandChar!=thisHandChar)
                {
                    if(staticWithJoker)
                    {
                        return compareChars(otherHandChar, thisHandChar,CARD_STRENGTH_ORDER_WITH_JOKER);
                    }
                    else
                    {
                        return compareChars(otherHandChar, thisHandChar,CARD_STRENGTH_ORDER);
                    }

                }
            }
        }
        return 0;
    }
    private static int compareChars(char otherHandChar, char thisHandChar,String cardsOrder) {
        int otherCardStrength = cardsOrder.indexOf(otherHandChar);
        int thiCardStrength = cardsOrder.indexOf(thisHandChar);
        if(thiCardStrength>otherCardStrength)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
