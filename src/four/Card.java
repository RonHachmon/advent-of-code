package four;

import java.util.HashSet;
import java.util.Set;

public class Card {
    public static final String WHITE_SPACE_REGEX = "\\s+";
    private int cardID;
    private Set<Integer> cardNumbers;
    private Set<Integer> winningNumbers;
    private int cardPoints = 0;
    private int totalMatches=0;


    public Card(String cardInfo) {
        String[] cardIdAndNumbers = cardInfo.split(":");
        this.extractCardID(cardIdAndNumbers[0]);

        String[] NumbersAndWinningNumber = cardIdAndNumbers[1].split("\\|");
        this.extractCardNumbers(NumbersAndWinningNumber[0]);
        this.extractWinningNumbers(NumbersAndWinningNumber[1]);
        this.CalculateCardPoint();
    }

    private void extractWinningNumbers(String split) {
        split = split.trim();
        String[] stringNumber = split.split(WHITE_SPACE_REGEX);
        winningNumbers = new HashSet<>();
        for (String number : stringNumber) {
            winningNumbers.add(Integer.parseInt(number));
        }
    }

    private void CalculateCardPoint() {
        int countPoint = -1;
        for (Integer num : cardNumbers) {
            if (winningNumbers.contains(num)) {
                countPoint++;
                this.totalMatches++;
            }
        }
        if (countPoint >= 0) {
            this.cardPoints = (int) Math.pow(2, countPoint);
        }
    }

    private void extractCardNumbers(String split) {
        split = split.trim();
        String[] stringNumber = split.split(WHITE_SPACE_REGEX);
        cardNumbers = new HashSet<>();
        for (String number : stringNumber) {
            cardNumbers.add(Integer.parseInt(number));
        }
    }

    private void extractCardID(String s) {
        String s1 = s.split(WHITE_SPACE_REGEX)[1];
        cardID = Integer.parseInt(s1);
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public Set<Integer> getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(Set<Integer> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(Set<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int getCardPoints() {
        return cardPoints;
    }

    public int getTotalMatches() {
        return totalMatches;
    }
}
