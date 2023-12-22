package seven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seven {
    private List<Hand> handList=new ArrayList<>();
    public  Seven (String path)
    {
        try {
            initHandList(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void PrintHands()
    {
        for (Hand hand: handList) {
            System.out.println("Hand "+hand.getHand()+" "+hand.getHandType());
        }
    }
    public void setJokerConfiguration(boolean isWithJoker)
    {
        PokerConfiguration.SetIsWithJoker(isWithJoker);
        for (Hand hand:handList)
        {
            hand.identifyHandType();
        }

    }

    public long CalculateTotalMoney()
    {
        long sum=0;
        this.sortHandList();
        for (int i = 0; i <handList.size() ; i++) {
            Hand hand = handList.get(i);
            sum+=(long)hand.getBid()*(i+1);

        }
        return sum;
    }

    private void sortHandList() {
        Collections.sort(handList);
    }

    private void initHandList(String path) throws IOException {
        List<String> handsAndBid = Files.readAllLines(Paths.get(path));
        for (int i = 0; i <handsAndBid.size() ; i++) {
            String[] handAndBid = handsAndBid.get(i).split(" ");
            Hand hand=new Hand(handAndBid[0],Integer.parseInt(handAndBid[1]));
            handList.add(hand);
        }
    }
}
