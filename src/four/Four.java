package four;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Four {
    private List<Card> cards = new ArrayList<>();
    private int [] totalCardInstances ;
    private int totalPoints = 0;
    private int totalCards = 0;

    public Four(String path) {
        try {
            initCards(path);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing matrix: " + e.getMessage());
        }
    }

    private void initCards(String path) throws IOException {

        List<String> allLines = Files.readAllLines(Paths.get(path));
        totalCardInstances=new int [allLines.size()];
        for (int i = 0; i < allLines.size(); i++) {
            totalCardInstances[i]=1;
            Card card = new Card(allLines.get(i));
            cards.add(card);
        }
    }
    public int CalculateTotalCards()
    {
        for (Card card:cards) {
            int totalMatches = card.getTotalMatches();
            for (int i = 0; i <totalMatches ; i++) {
                int totalCardInstance = totalCardInstances[card.getCardID() - 1];
                totalCardInstances[i+card.getCardID()]+=totalCardInstance;
            }
            this.totalCards+=totalCardInstances[card.getCardID()-1];
        }
        return this.totalCards;
    }
    public int CalculatePoints() {
        int points = 0;
        for (Card card : cards) {
            points += card.getCardPoints();
        }
        this.totalPoints = points;
        return this.totalPoints;
    }



}
