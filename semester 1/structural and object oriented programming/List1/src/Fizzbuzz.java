public class Fizzbuzz
{
    public static void main(String[] args)
    {

        int a = 8;
        for (int i = 1; i <= a; i++)
        {
            if ((i % 3) == 0)
            {
                if ((i % 5) == 0)
                {
                    System.out.print("fizzbuzz,");
                }
                else
                {
                    System.out.print("fizz,");
                }
            }
            else
            {
                if ((i % 5) == 0)
                {
                    System.out.print("buzz,");
                }
                else
                {
                    System.out.print("-,");
                }
            }

        }
    }
}