package thirteen.pattern;

import java.util.List;

public class BitPattern extends AbstractPattern<Integer> {


    public BitPattern(List<Integer> rows, List<Integer> columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int countDifferentBits(Integer num1, Integer num2, int count) {
        int xorResult = num1 ^ num2;
        while (xorResult != 0) {
            xorResult &= (xorResult - 1);
            count++;
            if (count > AbstractPattern.getTotalMismatch()) {
                return count;
            }
        }
        return count;
    }


//    public int countDifferentBits(int num1, int num2, int count) {
//        int xorResult = num1 ^ num2;
//        while (xorResult != 0) {
//            xorResult &= (xorResult - 1);
//            count++;
//            if (count > BitPattern.totalMismatch) {
//                return count;
//            }
//        }
//        return count;
//
//    }

}
