import PackageMath.factorialPower;

public class functionApproximation2
{
    double x = Math.PI/2;
    int n = 0;
    factorialPower fp = new factorialPower();

    public functionApproximation2() { }

    public functionApproximation2(double x, int n)
    {
        this.x = x;
        this.n = n;
    }

    public double sin()
    {
        double sin = 0;
        for(int i = 0; i < n; i++)
        {
            int denominator = 2 * i + 1;
            sin += (fp.power(x, (2 * i + 1)) * fp.power(-1, i) / fp.factorial(denominator));
        }
        return sin;
    }

    public double cos()
    {
        double cos = 0.0;
        for(int i = 0; i < n; i++)
        {
            int denominator = 2 * i;
            cos += (fp.power(x, (2 * i)) * Math.pow(-1.0, i)) / fp.factorial(denominator);
        }
        return cos;
    }

    public double tan()
    {
        return sin()/cos();
    }

    public double sec()
    {
        return fp.power(cos(), -1);
    }

    public double arcsin()
    {
        double arcsin = 0.0;
        for(int i = 0; i < n; i++)
        {
            arcsin += (fp.factorial(2 * i) * fp.power(x, (2 * i + 1))) / (fp.power(4.0, i) * fp.power(fp.factorial(i), 2) * (2 * i + 1));
        }
        return arcsin;
    }

    public double arccos()
    {
        return (Math.PI/2) - arcsin();
    }

    public double arctan()
    {
        double arctan = 0.0;
        for(int i = 0; i < n; i++)
        {
            int denominator = 2 * i + 1;
            arctan += (fp.power(x, (2 * i + 1)) * fp.power(-1, i)) / denominator;
        }
        return arctan;
    }

}
