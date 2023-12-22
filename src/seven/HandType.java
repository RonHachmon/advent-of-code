package seven;

import java.util.HashMap;
import java.util.Map;

public enum HandType {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int handValue;

     HandType(int handValue) {
        this.handValue = handValue;
    }

    int handValue() {
        return handValue;
    }

    public static HandType identifyHandTypeWithJasJoker(String hand) {
        if (hand.length() != 5) {
            throw new IllegalArgumentException("Invalid hand size");
        }

        Map<Character, Integer> characterToOccurrenceMap = new HashMap<>();
        int numberOfJokers = 0;
        for (char c : hand.toCharArray()) {
            if (c == 'J') {
                numberOfJokers++;
            } else {
                characterToOccurrenceMap.merge(c, 1, Integer::sum);
            }
        }
        switch (numberOfJokers)
        {
            case 5, 4:
                return HandType.FIVE_OF_A_KIND;
            case 3:
                return determineHandTypeWithThreeJokers(characterToOccurrenceMap);
            case 2:
                return determineHandTypeWithTwoJokers(characterToOccurrenceMap);
            case 1:
                return determineHandTypeWithOneJokers(characterToOccurrenceMap);
            case 0:
                return identifyHandTypeWithOutJoker(hand);
        }
        return HIGH_CARD;
    }

    private static HandType determineHandTypeWithOneJokers(Map<Character, Integer> characterToOccurrenceMap) {
        if (characterToOccurrenceMap.size() == 1) {
            return HandType.FIVE_OF_A_KIND;
        }
        if (characterToOccurrenceMap.size() == 2) {
            for (Map.Entry<Character, Integer> entry : characterToOccurrenceMap.entrySet()) {
                if (entry.getValue() == 3 || entry.getValue() == 1) {
                    return HandType.FOUR_OF_A_KIND;
                } else {
                    return FULL_HOUSE;
                }
            }
            return HandType.FOUR_OF_A_KIND;
        }
        if (characterToOccurrenceMap.size() == 3) {
            return HandType.THREE_OF_A_KIND;
        }
        else
        {
            return HandType.ONE_PAIR;
        }
    }

    private static HandType determineHandTypeWithTwoJokers(Map<Character, Integer> characterToOccurrenceMap) {
        if (characterToOccurrenceMap.size() == 1) {
            return HandType.FIVE_OF_A_KIND;
        }
        if (characterToOccurrenceMap.size() == 2) {
            return HandType.FOUR_OF_A_KIND;
        }
        else
        {
            return HandType.THREE_OF_A_KIND;
        }
    }

    private static HandType determineHandTypeWithThreeJokers(Map<Character, Integer> characterToOccurrenceMap) {
        if (characterToOccurrenceMap.size() == 1) {
            return HandType.FIVE_OF_A_KIND;
        } else {
            return HandType.FOUR_OF_A_KIND;
        }
    }

    public static HandType identifyHandTypeWithOutJoker(String hand) {
        if (hand.length() != 5) {
            throw new IllegalArgumentException("Invalid hand size");
        }

        Map<Character, Integer> characterToOccurrenceMap = new HashMap<>();
        for (char c : hand.toCharArray()) {
            characterToOccurrenceMap.merge(c, 1, Integer::sum);
        }
        if (characterToOccurrenceMap.size() == 1) {
            return HandType.FIVE_OF_A_KIND;
        }
        if (characterToOccurrenceMap.size() == 2) {

            for (Map.Entry<Character, Integer> entry : characterToOccurrenceMap.entrySet()) {
                if (entry.getValue() == 3 || entry.getValue() == 2) {
                    return HandType.FULL_HOUSE;
                } else {
                    return FOUR_OF_A_KIND;
                }
            }
        }
        if (characterToOccurrenceMap.size() == 3) {
            for (Map.Entry<Character, Integer> entry : characterToOccurrenceMap.entrySet()) {
                if (entry.getValue() == 3) {
                    return HandType.THREE_OF_A_KIND;
                }
                if (entry.getValue() == 2) {
                    return HandType.TWO_PAIR;
                }
            }

        }
        if (characterToOccurrenceMap.size() == 4) {
            return ONE_PAIR;
        }
        return HIGH_CARD;

    }


}


