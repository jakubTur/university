public class functionApproximation {
    double x = 1.5707963267948966;
    int n = 100;

    public functionApproximation(double x, int n) {
        this.x = x;
        this.n = n;
    }

    public functionApproximation() {
    }

    public double sin()
    {
        double sin = 0.0;
        for(int i = 0; i < n; i++)
        {
            double denominator = 2 * i + 1;
            for(int j = (int) denominator-1; j > 1; j--)
            {
                denominator = denominator * j;
            }
            sin += (Math.pow(x, (2 * i + 1)) * Math.pow(-1, i) / denominator);
        }
        return sin;
    }

    public double cos()
    {
        double cos = 0.0;
        for(int i = 1; i < n; i++)
        {
            double denominator = 2 * i;
            for(int j = (int) denominator-1; j > 1; j--)
            {
                denominator = denominator * j;
            }
            cos += (Math.pow(x, (2 * i)) * Math.pow(-1, i) / denominator);
        }
        return cos + 1; //for i==0
    }

    public double tan()
    {
        return sin()/cos();
    }

    public double sec()
    {
        return Math.pow(cos(), -1);
    }

    public double arcsin()
    {
        double arcsin = 0.0;
        for(int i = 1; i < n; i++)
        {
            double numerator = 2 * i;
            double denominator = i;
            for(int j = (int) numerator; j > 1; j--)
            {
                numerator = numerator * j;
            }
            for(int j = (int) denominator; j > 1; j--)
            {
                denominator = denominator * j;
            }
            arcsin += (numerator * Math.pow(x, (2 * i + 1))) / (Math.pow(4.0, i) * Math.pow(denominator, 2.0) * (2 * i + 1));
        }
        return arcsin+x;
    }

    public double arccos()
    {
        return 1.5707963267948966 - arcsin();
    }

    public double arctan()
    {
        double arctan = 0.0;
        for(int i = 0; i < n; i++)
        {
            int denominator = 2 * i +1;
            arctan += (Math.pow(x, (2 * i + 1)) * Math.pow(-1, i)) / denominator;
        }
        return arctan;
    }
}
