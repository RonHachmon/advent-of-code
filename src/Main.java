import one.One;

public class Main {
    public static void main(String[] args) {
        //One one = new One("./src/one/test.txt");
        One one = new One("./src/one/input.txt");
        //one.PrintLines();
        one.CalculateResult();
        System.out.println(one.GetRes());
    }
}