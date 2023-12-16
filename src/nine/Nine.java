package nine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Nine {
    private List<Sequence> sequenceList=new ArrayList<>();
    public Nine(String path)
    {
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(path));
            for (String sequence:fileLines) {
                sequenceList.add(new Sequence(sequence));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public long CalculateNextSequenceSum()
    {
        long sum=0;
        for (Sequence sequence:sequenceList) {
            sum+=sequence.CalculateNextSequence();
        }
        return sum;

    }
    public long CalculatePreviousSequenceSum()
    {
        long sum=0;
        for (Sequence sequence:sequenceList) {
            sum+=sequence.CalculatePreviousSequence();
        }
        return sum;

    }
}
