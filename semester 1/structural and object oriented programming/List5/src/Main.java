public class Main {
    public static int determinantMatrixSarrusMethod (int[][] matrix) //task1
    {
        if(matrix.length == 3 && matrix[0].length == 3 && matrix[1].length == 3 && matrix[2].length == 3)
        {
            int[][] sarrus = { { matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][0], matrix[0][1] },
                    { matrix[1][0], matrix[1][1], matrix[1][2], matrix[1][0], matrix[1][1] },
                    { matrix[2][0], matrix[2][1], matrix[2][2], matrix[2][0], matrix[2][1] } };
            return (sarrus[0][0] * sarrus[1][1] * sarrus[2][2]) + (sarrus[0][1] * sarrus[1][2] * sarrus[2][3]) +
                        (sarrus[0][2] * sarrus[1][3] * sarrus[2][4]) - (sarrus[2][0] * sarrus[1][1] * sarrus[0][2]) -
                        (sarrus[2][1] * sarrus[1][2] * sarrus[0][3]) - (sarrus[2][2] * sarrus[1][3] * sarrus[0][4]);
            }
        else
        {
            throw new IllegalArgumentException("not a 3x3 matrix");
        }
    }
    public static int determinantOfMatrix4by4 (int[][] matrix) //task2
    {
        if(matrix.length == 4 && matrix[0].length == 4 && matrix[1].length == 4 && matrix[2].length == 4 && matrix[3].length == 4)
        {
            int determinant=0;
            int[][] minor = { {0,0,0}, {0,0,0}, {0,0,0} };
            for(int i = 0; i <= 3; i++)
            {
                int y = 0;
                int j = 0;
                for (int n = 0; n <= 3; n++)
                {
                    int z = 0;
                    for (int m = 1; m <= 3; m++)
                    {
                        if (n != i)
                        {
                            minor[y][z] = matrix[n][m];
                            z++;
                        }
                    }
                    if(n != i) { y++; }
                }
                int threeByThree = determinantMatrixSarrusMethod(minor);
                determinant = (int) (determinant + matrix[i][0]*Math.pow(-1, i + j) * threeByThree);
            }
            return determinant;
        }
        else
        {
            throw new IllegalArgumentException("not a 4x4 matrix");
        }
    }

    public static int determinantOfAnyMatrix(int[][] matrix) //task3
    {
        if(matrix.length == 3 && matrix[0].length == 3 && matrix[1].length == 3 && matrix[2].length == 3)
        {
            return determinantMatrixSarrusMethod(matrix);
        }
        else if(matrix.length == 4 && matrix[0].length == 4 && matrix[1].length == 4 && matrix[2].length == 4 && matrix[3].length == 4)
        {
            return determinantOfMatrix4by4(matrix);
        }
        else
        {
            throw new IllegalArgumentException("matrix neither 3x3 nor 4x4");
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { {2, 1, 3}, {1, -1, 0}, {-2,4,1} };
        int[][] matrix4x4 = { {2, 1, 3, 0}, {-1, 1, 0, 3}, {54, 1, 8, 67}, {-8, 9, 65, 12} };
        int[][] wrong = { {1,2}, {0} };

        try
        {
            //1
            System.out.println(determinantMatrixSarrusMethod(matrix));
            System.out.println(determinantMatrixSarrusMethod(wrong));
        }
        catch (IllegalArgumentException e)
        {
            System.err.println(e.getMessage());
        }

        try
        {
            //2
            System.out.println(determinantOfMatrix4by4(matrix4x4));
            System.out.println(determinantOfMatrix4by4(wrong));
        }
        catch (IllegalArgumentException e)
        {
            System.err.println(e.getMessage());
        }

        try
        {
            //3
            System.out.println(determinantOfAnyMatrix(matrix));
            System.out.println(determinantOfAnyMatrix(matrix4x4));
            System.out.println(determinantOfAnyMatrix(wrong));
        }
        catch (IllegalArgumentException e)
        {
            System.err.println(e.getMessage());
        }
    }
}




