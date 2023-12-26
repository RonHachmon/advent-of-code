package fifteen;

import fifteen.inputs.Sequence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Fifteen {


    private final List<Sequence> sequenceList=new ArrayList<>();
    private final List<LinkedList<Lens>> linkedListBucket = new ArrayList<>(256);
    public Fifteen(String path)
    {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            extractSequenceList(strings);
            initBucket();
            extractBucket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initBucket() {
        for (int i = 0; i < 256; i++) {
            linkedListBucket.add(new LinkedList<Lens>());
        }

    }

    private void extractSequenceList(List<String> strings) {
        for (String string : strings) {
            String[] splitSequence = string.split(",");
            for (String s : splitSequence) {
                sequenceList.add(new Sequence(s));
            }
        }
    }
    private void extractBucket() {
        for (Sequence sequence : this.sequenceList) {
            Lens currentLen = sequence.getLen();
            int lensHash = sequence.getLensHash();
            LinkedList<Lens> lens = this.linkedListBucket.get(lensHash);
            Optional<Lens> optionalLens = lens.stream().filter(requestedLen -> requestedLen.getLabel().equals(currentLen.getLabel())).findFirst();
            if(currentLen.getLenType()==LenType.FOCAL)
            {
                optionalLens.ifPresentOrElse(updateLen -> updateLen.setFocalLength(currentLen.getFocalLength()),()->lens.add(currentLen));
            }
            if(currentLen.getLenType()==LenType.REMOVAL)
            {
                optionalLens.ifPresent(removedLen->lens.remove(removedLen));
            }

        }
    }

    public int totalOfHash()
    {
        int total=0;
        for (Sequence sequence : sequenceList) {
            sequence.PrintSequence();
            int hashValue = sequence.getSequenceHash();
            System.out.println("hashValue = " + hashValue);
            total+=sequence.getSequenceHash();
            System.out.println("_____________________");
        }
        return total;
    }
    public int CalculateFocusingPower()
    {
        int total=0;
        int bucketIndex=1;
        int slotIndex;
        for (LinkedList<Lens> lens : linkedListBucket) {
            slotIndex=1;
            for (Lens len : lens) {
                total+=bucketIndex*slotIndex*len.getFocalLength();
                slotIndex++;
            }
            bucketIndex++;

        }
        return total;
    }
    public void PrintBucket()
    {
        int bucketIndex=1;
        for (LinkedList<Lens> lens : linkedListBucket) {
            System.out.print("Bucket :"+bucketIndex);
            for (Lens len : lens) {
                String format = String.format(" Label [ %s %d ]", len.getLabel(), len.getFocalLength());
                System.out.print(format+", ");
            }
            System.out.println();
            bucketIndex++;

        }
    }

}
