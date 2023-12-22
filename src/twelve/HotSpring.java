package twelve;


import java.util.*;

public class HotSpring {
    private String hotSpringMapTimesFive;
    private String hotSpringMap;
    private int[] mapSpringValue;
    private int[] mapSpringValueTimesFive;
    private Map<Integer, Integer> memorizeIndexToSum = new HashMap<>();
    private Map<Integer, Integer> memorizeIndexToSumTimes5 = new HashMap<>();
    private Map<Memorization, Long> memorizeMapToResult = new HashMap<>();

    public HotSpring(String hotSpringMap, int[] list) {
        this.hotSpringMap = hotSpringMap;
        mapSpringValue = list;
        generateTimesFiveInputs(hotSpringMap, list);

    }


    public long sum5() {
        return sumOfTotalPossibilitiesMemorized(new Memorization(hotSpringMapTimesFive, 0), true);

    }

    public long sum() {
        return sumOfTotalPossibilitiesFineTuned(hotSpringMap, 0, false);
    }

    //Expert approach
    //memorizing result with memorized class with Map and Index member, and colliding them to one hash code
    //therefore shortening  on already found result (dynamic programming)
    private long sumOfTotalPossibilitiesMemorized(Memorization inputAndIndex, boolean isTimesFive) {
        if (this.memorizeMapToResult.containsKey(inputAndIndex)) {
            return memorizeMapToResult.get(inputAndIndex);
        }
        int[] springValues = isTimesFive ? mapSpringValueTimesFive : mapSpringValue;
        String map = removeLeadingDots(inputAndIndex.getMap());
        Integer index = inputAndIndex.getIndex();
        if (!isSizeSufficient(map, inputAndIndex.getIndex(), isTimesFive)) return 0;


        String poolSequence = this.findPoolSequence(map);

        if (poolSequence != null) {
            if (poolSequence.length() == springValues[inputAndIndex.getIndex()]) {
                map = map.substring(poolSequence.length());
                if (inputAndIndex.getIndex() == springValues.length - 1) {
                    return map.contains("#") ? 0 : 1;
                }
                index++;
            } else {
                return 0;
            }

        }


        int nextQuestionMarkIndex = this.findNextQuestionMarkIndex(map);
        if (nextQuestionMarkIndex == -1) {
            return sumOfTotalPossibilitiesMemorized(new Memorization(map, index), isTimesFive);
        }
        Memorization replacedWithDot = new Memorization(map.replaceFirst("\\?", "."), index);
        Memorization replacedWithSpring = new Memorization(map.replaceFirst("\\?", "#"), index);

        long res = sumOfTotalPossibilitiesMemorized(replacedWithDot, isTimesFive) + sumOfTotalPossibilitiesMemorized(replacedWithSpring, isTimesFive);
        this.memorizeMapToResult.putIfAbsent(inputAndIndex, res);
        return res;


    }


    // intermediate approach
    //decreasing input size with each step
    // extracting one at the time current relevant pool and check its matching
    private long sumOfTotalPossibilitiesFineTuned(String map, int springIndex, boolean isTimesFive) {
        int[] springValues = isTimesFive ? mapSpringValueTimesFive : mapSpringValue;
        map = removeLeadingDots(map);
        //if (!isSizeSufficient(map, springIndex, isTimesFive)) return 0;


        String poolSequence = this.findPoolSequence(map);
        if (poolSequence != null) {
            if (poolSequence.length() == springValues[springIndex]) {
                map = map.substring(poolSequence.length());
                if (springIndex == springValues.length - 1) {
                    return map.contains("#") ? 0 : 1;
                }
                springIndex++;
            } else {
                return 0;
            }

        }

        int nextQuestionMarkIndex = this.findNextQuestionMarkIndex(map);
        if (nextQuestionMarkIndex == -1) {
            return sumOfTotalPossibilitiesFineTuned(map, springIndex, isTimesFive);
        }
        String replacedWithDot = map.replaceFirst("\\?", ".");
        String replacedWithSpring = map.replaceFirst("\\?", "#");

        return sumOfTotalPossibilitiesFineTuned(replacedWithDot, springIndex, isTimesFive) + sumOfTotalPossibilitiesFineTuned(replacedWithSpring, springIndex, isTimesFive);


    }

    //Basic approach
    // creating all variation and matching the map
    private int sumOfTotalPossibilities(String map) {

        int nextQuestionMarkIndex = this.findNextQuestionMarkIndex(map);


        if (nextQuestionMarkIndex == -1) {
            return damagedGroupsSatisfied(map) ? 1 : 0;
        }
        if (!damagedGroupsUntilIndexSatisfied(map.substring(0, nextQuestionMarkIndex))) {
            return 0;
        }


        String replacedWithDot = map.replaceFirst("\\?", ".");
        String replacedWithSpring = map.replaceFirst("\\?", "#");
        return sumOfTotalPossibilities(replacedWithDot) + sumOfTotalPossibilities(replacedWithSpring);

    }

    private String findPoolSequence(String mapWithoutLeadingDots) {
        int index = 0;
        while (index < mapWithoutLeadingDots.length() && mapWithoutLeadingDots.charAt(index) == '#') {
            index++;
        }
        if (index == mapWithoutLeadingDots.length() || mapWithoutLeadingDots.charAt(index) == '.') {
            return mapWithoutLeadingDots.substring(0, index);
        }
        return null;
    }

    private String removeLeadingDots(String input) {
        int index = 0;
        while (index < input.length() && input.charAt(index) == '.') {
            index++;
        }
        return input.substring(index);
    }

    private boolean isSizeSufficient(String map, int index, boolean isFiveBigger) {

        int[] arr;
        Map<Integer, Integer> indexToSumMemorize;
        if (isFiveBigger) {
            arr = this.mapSpringValueTimesFive;
            indexToSumMemorize = this.memorizeIndexToSumTimes5;
        } else {
            indexToSumMemorize = this.memorizeIndexToSum;
            arr = this.mapSpringValue;
        }
        Integer sum = indexToSumMemorize.get(index);
        if (sum != null) {
            return sum <= map.length();
        }

        indexToSumMemorize.computeIfAbsent(index, s -> calculateSum(index, arr));
        return indexToSumMemorize.get(index) <= map.length();

    }

    private static Integer calculateSum(int index, int[] arr) {
        Integer sum = 0;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];

        }
        sum += arr.length - index - 1;
        return sum;
    }


    private boolean damagedGroupsSatisfied(String map) {
        List<String> hashSequences = hashStrings(map);

        if (hashSequences.size() != mapSpringValue.length) {
            return false;
        }

        for (int i = 0; i < hashSequences.size(); i++) {
            if (hashSequences.get(i).length() != mapSpringValue[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean damagedGroupsUntilIndexSatisfied(String map) {
        List<String> hashSequences = hashStrings(map);
        if (hashSequences.size() > mapSpringValue.length) {
            return false;
        }


        for (int i = 0; i < hashSequences.size(); i++) {
            if (hashSequences.get(i).length() > mapSpringValue[i]) {
                return false;
            }
        }

        return true;
    }


    private static List<String> hashStrings(String map) {
        String[] segments = map.split("\\.+");
        List<String> stringStream = Arrays.stream(segments).filter(s -> !s.isEmpty()).toList();
        return stringStream;
    }


    private int findNextQuestionMarkIndex(String string) {
        return string.indexOf("?");
    }

    private void generateTimesFiveInputs(String hotSpringMap, int[] list) {
        this.hotSpringMapTimesFive = hotSpringMap;
        for (int i = 0; i < 4; i++) {
            this.hotSpringMapTimesFive += "?" + hotSpringMap;

        }

        mapSpringValueTimesFive = new int[list.length * 5];
        for (int i = 0; i < mapSpringValueTimesFive.length; i++) {
            mapSpringValueTimesFive[i] = list[i % list.length];

        }
    }


}
