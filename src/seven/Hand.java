package seven;

import static seven.PokerConfiguration.CARD_STRENGTH_ORDER;
import static seven.PokerConfiguration.CARD_STRENGTH_ORDER_WITH_JOKER;

public class Hand implements Comparable<Hand> {

    private final String hand;
    private final int bid;
    private  HandType handType;

    public Hand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
        this.identifyHandType();

    }

    public void identifyHandType() {
        if(PokerConfiguration.IsWithJoker())
        {
            this.handType = HandType.identifyHandTypeWithJasJoker(hand);
        }
        else
        {
            this.handType =  HandType.identifyHandTypeWithOutJoker(hand);
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
                    if(PokerConfiguration.IsWithJoker())
                    {
                        return compareCardStrength(otherHandChar, thisHandChar,CARD_STRENGTH_ORDER_WITH_JOKER);
                    }
                    else
                    {
                        return compareCardStrength(otherHandChar, thisHandChar,CARD_STRENGTH_ORDER);
                    }

                }
            }
        }
        return 0;
    }
    private int compareCardStrength(char otherHandChar, char thisHandChar, String cardsOrder) {
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
