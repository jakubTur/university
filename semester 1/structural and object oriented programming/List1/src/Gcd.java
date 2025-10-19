public class Gcd {
    public static void main(String[] args) {
        int a = 666;
        int b = 111;
        int smaller;
        if(a > b)
        {
            smaller = b;
        }
        else
        {
            smaller = a;
        }
        if(a != 0 && b != 0)
        {
            for (int i = smaller; i > 0; i--)
            {
                if (a % i == 0 && b % i == 0)
                {
                    System.out.print(i);
                    break;
                }
            }
        }
    }
}