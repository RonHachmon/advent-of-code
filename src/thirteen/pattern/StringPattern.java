package thirteen.pattern;

import java.util.List;

public class StringPattern extends AbstractPattern<String> {


    public StringPattern(List<String> rows, List<String> columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int countDifferentBits(String num1, String num2, int count) {
        for (int i = 0; i < num1.length(); i++) {
            if (num1.charAt(i) != num2.charAt(i)) {
                count++;
                if (count > 1) {
                    return count;
                }
            }

        }

        return count;
    }

}
