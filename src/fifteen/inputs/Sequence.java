package fifteen.inputs;


import fifteen.LenType;
import fifteen.Lens;

public class Sequence {
    private String sequence;
    private Lens len;



    public Sequence(String sequence) {
        this.sequence = sequence;
        this.extractLen(sequence);
    }

    private void extractLen(String sequence) {
        if(sequence.contains("="))
        {
            String[] sequenceSplit = sequence.split("=");
            this.len=new Lens(sequenceSplit[0], Integer.parseInt(sequenceSplit[1]), LenType.FOCAL);
        }
        else
        {
            String[] sequenceSplit = sequence.split("-");
            this.len=new Lens(sequenceSplit[0], null, LenType.REMOVAL);
        }

    }

    public Lens getLen() {
        return this.len;
    }

    public int getLensHash() {
        return hashValueOfLabel(this.len.getLabel());
    }
    public int getSequenceHash() {
        return hashValueOfLabel(this.sequence);
    }

    private int hashValueOfLabel(String label) {
        int startValue = 0;
        for (Character c : label.toCharArray()) {
            startValue = calculateHashValueForChar(startValue, c);

        }
        return startValue;
    }

    private int calculateHashValueForChar(int startValue, Character currentChar) {
        int charValue = currentChar.charValue();
        startValue += charValue;
        startValue *= 17;
        startValue = startValue % 256;
        return startValue;
    }

    public void PrintSequence() {
        System.out.println(sequence);
    }
}
