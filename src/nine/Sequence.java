package nine;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private List<List<Integer>> list=new ArrayList<>();
    public Sequence(String sequence)
    {
        List<Integer> firstList=new ArrayList<>();
        String[] nums = sequence.split(" ");
        for (String num: nums) {
            firstList.add(Integer.parseInt(num));
        }
        list.add(firstList);
        this.calculateRestOfLists();
    }

    private void calculateRestOfLists() {
        boolean allZero=true;
        int gap=0;
        List<Integer> lastList = list.get(list.size() - 1);
        List<Integer> newList=new ArrayList<>();
        for (int i = 0; i < lastList.size()-1; i++) {
            gap=lastList.get(i+1)-lastList.get(i);
            newList.add(gap);
            if(gap!=0)
            {
                allZero=false;
            }
        }
        this.list.add(newList);
        if(allZero)
        {
            return;
        }
        else
        {
            calculateRestOfLists();
        }

    }
    public int CalculatePreviousSequence() {
        List<Integer> firstList = list.get(0);
        List<Integer> lastList = list.get(list.size()-1);
        lastList.add(0,0);
        for (int i = list.size()-2; i >= 0; i--) {
            List<Integer> currentList = list.get(i);
            List<Integer> topperList = list.get(i+1);
            Integer firstNumOfCurrentList = currentList.get(0);
            Integer firstNumOfTopperList = topperList.get(0);
            currentList.add(0,firstNumOfCurrentList-firstNumOfTopperList);

        }

        return firstList.get(0);
    }

    public int CalculateNextSequence() {
        List<Integer> firstList = list.get(0);
        List<Integer> lastList = list.get(list.size()-1);
        lastList.add(0);
        for (int i = list.size()-2; i >= 0; i--) {
            List<Integer> currentList = list.get(i);
            List<Integer> topperList = list.get(i+1);
            Integer lastNumOfCurrentList = currentList.get(currentList.size() - 1);
            Integer lastNumOfTopperList = topperList.get(topperList.size() - 1);
            currentList.add(lastNumOfCurrentList+lastNumOfTopperList);

        }

        return firstList.get(firstList.size()-1);
    }
}
