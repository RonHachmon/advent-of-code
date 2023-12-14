package five;

public class Seed {
    private final long seedStartLocation;
    private final long seedRange;




    public Seed(long seedStartLocation, long seedRange) {
        this.seedStartLocation = seedStartLocation;
        this.seedRange = seedRange;
    }
    public long getSeedStartLocation() {
        return seedStartLocation;
    }


    public long getSeedRange() {
        return seedRange;
    }
}
