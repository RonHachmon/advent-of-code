package six;

public class Race {
    private final long time;
    private final long distance;


    public long getTime() {
        return time;
    }

    public long getDistance() {
        return distance;
    }

    public Race(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

    public int NumberOfWaysToWin() {

        boolean isTimeEvenNumber = getTime() % 2 == 0;
        int sum = 0;
        for (long holdCarTime = time / 2; holdCarTime > 0; holdCarTime--) {
            if (isCarSpeedPassDistance(holdCarTime)) {
                if (isTimeEvenNumber && holdCarTime == time / 2) {
                    sum += 1;
                } else {
                    sum += 2;
                }
            } else {
                break;
            }
        }
        return sum;

    }

    private boolean isCarSpeedPassDistance(long holdCarTime) {
        return holdCarTime * (getTime() - holdCarTime) > getDistance();
    }
}
