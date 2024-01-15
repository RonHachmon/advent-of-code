public class Playground {
    public static boolean  isPrime(int num)
    {
        for(int j=2;j<num;j++)
        {
            if(num%j==0)
            {
                return false;
            }
        }
        return true;


    }
}
