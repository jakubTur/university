package PackageMath;
public class factorialPower
{
    public double power (double number, int power)
    {
        return Math.pow(number, power);
    }
    public double factorial (int factorial)
    {
        double result = 1.0;
        if(factorial == 0 || factorial == 1)
        {
            return result;
        }
        for(int y = 2; y <= factorial; y++)
        {
            result = result * y;
        }
        return result;
    }
}