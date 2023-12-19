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
        double a=-1;
        double b=time;
        double c=-distance-1;
        double[] doubles = calculateQuadraticRoots(a, b, c);
        double floor = Math.floor(doubles[1]);
        double ceil = Math.ceil(doubles[0]);
        return (int) (floor -ceil+1);

    }

    //the problem can be seen a math function of
    // holdingTime *(time - holdingTime ) > distance. =>
    //  -holdingTime^2 +time * holdingTime - distance > 0
    // since we know the variable time and distance we can solve the equation and find for which X
    // the result is above zero
    private static double[] calculateQuadraticRoots(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
        if(Math.max(root1,root2)==root1)
        {
            return new double[]{root2, root1};
        }
        else
        {
            return new double[]{root1, root2};
        }

    }

    public int NumberOfWaysToWin2() {

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
