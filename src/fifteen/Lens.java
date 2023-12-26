package fifteen;

public class Lens {
    private final String label;
    private  Integer focalLength;
    private LenType lenType;

    public LenType getLenType() {
        return lenType;
    }

    public Lens(String label, Integer focalLength, LenType lenType) {
        this.label = label;
        this.focalLength = focalLength;
        this.lenType = lenType;

    }

    public String getLabel() {
        return label;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }
}
