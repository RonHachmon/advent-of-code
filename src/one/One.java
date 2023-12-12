package one;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class One {
    private List<String> input = new ArrayList<>();

    private int res=0;



    public One(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String result = reader.readLine();
            while (result != null) {
                input.add(result);
                result = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void CalculateResult() {
        this.setRes(0);
        for (String line : input) {
            int firstDigit = this.extractFirstDigit(line);
            int lastDigit = this.extractLastDigit(line);
            int temp= firstDigit * 10 + lastDigit;
            System.out.println(line+" "+ temp);
            this.setRes(this.GetRes()+firstDigit * 10 + lastDigit);
        }
    }

    private int extractLastDigit(String str) {
        int result = -1;
        for (int i = str.length() - 1; i >= 0; i--) {
            Character currentChar = str.charAt(i);
            if (Character.isDigit(currentChar)) {
                result = Character.getNumericValue(currentChar);
                break;
            }
            if (Character.isAlphabetic(currentChar)) {
                result=this.extractNumberFromEndOfString(str, i + 1);
                if (result!=-1)
                {
                    break;
                }
            }
        }
        return result;

    }

    private int extractNumberFromEndOfString(String str, int endIndex) {
        int res = -1;
        if (endIndex > str.length()) {
            return res;
        }
        int maxNumberLength = endIndex;
        if (maxNumberLength >= 5) {
            res = NumberNames.stringIsNumber(str.substring(endIndex - 5, endIndex));
        }

        if (res == -1) {
            if (maxNumberLength >= 4) {
                res = NumberNames.stringIsNumber(str.substring(endIndex - 4, endIndex));

            }
        }
        if (res == -1) {
            if (maxNumberLength >= 3) {
                res = NumberNames.stringIsNumber(str.substring(endIndex - 3, endIndex));
            }
        }
        return res;

    }

    private int extractFirstDigit(String str) {
        int result = -1;
        for (int i = 0; i < str.length(); i++) {
            Character currentChar = str.charAt(i);
            if (Character.isDigit(currentChar)) {
                result = Character.getNumericValue(currentChar);
                break;
            }
            if (Character.isAlphabetic(currentChar)) {
                result = this.extractNumberFromStartOfString(str, i);
                if (result != -1) {
                    break;
                }
            }
        }
        return result;

    }


    private int extractNumberFromStartOfString(String str, int startIndex) {
        int res = -1;
        if (startIndex < 0) {
            return res;
        }
        int maxNumberLength = str.length() - startIndex;
        if (maxNumberLength >= 5) {
            res = NumberNames.stringIsNumber(str.substring(startIndex, startIndex + 5));

        }

        if (res == -1) {
            if (maxNumberLength >= 4) {
                res = NumberNames.stringIsNumber(str.substring(startIndex, startIndex + 4));

            }
        }
        if (res == -1) {
            if (maxNumberLength >= 3) {
                String substring = str.substring(startIndex, startIndex + 3);
                res = NumberNames.stringIsNumber(substring);
            }

        }
        return res;

    }

    public int GetRes() {
        return res;
    }
    public void setRes(int res) {
        this.res = res;
    }

    public void PrintLines() {
        for (String line : input) {
            System.out.println(line);

        }
    }
}
