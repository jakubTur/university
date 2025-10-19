public class gameoflife
{
    public static void main(String[] args) {
        int suma = 0;
        int[][] grid = {
                {1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0}};
        int[][] zywosc = {
                {1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0}};

        for (int e = 0; e <= 5; e++) {
            for (int f = 0; f <= 5; f++) {
                System.out.print(grid[e][f]+" ");
            }
            System.out.println();
        }
        for(int i=0; i<=20;i++)
        {
            for (int a = 0; a <= 5; a++) {
                for (int b = 0; b <= 5; b++)
                {
                    if (a != 0 && b != 0 && a != 5 && b != 5) {
                        suma = grid[a - 1][b - 1] + grid[a][b - 1] + grid[a - 1][b] + grid[a + 1][b - 1] + grid[a - 1][b + 1] + grid[a + 1][b + 1] + grid[a][b + 1] + grid[a + 1][b];

                    }
                    if (a == 0 && b == 0) {
                        suma = grid[a + 1][b + 1] + grid[a][b + 1] + grid[a + 1][b];
                    }
                    if (a == 5 && b == 5) {
                        suma = grid[a - 1][b - 1] + grid[a][b - 1] + grid[a - 1][b];
                    }
                    if (a == 0 && b == 5) {
                        suma = grid[a][b - 1] + grid[a + 1][b - 1] + grid[a + 1][b];
                    }
                    if (a == 5 && b == 0) {
                        suma = grid[a - 1][b] + grid[a - 1][b + 1] + grid[a][b + 1];
                    }
                    if (a == 0 && b != 0 && b != 5) {
                        suma = grid[a][b - 1] + grid[a + 1][b - 1] + grid[a + 1][b + 1] + grid[a][b + 1] + grid[a + 1][b];
                    }

                    if (a == 5 && b != 0 && b != 5) {
                        suma = grid[a - 1][b - 1] + grid[a][b - 1] + grid[a - 1][b] + grid[a - 1][b + 1] + grid[a][b + 1];
                    }
                    if (b == 0 && a != 0 && a != 5) {
                        suma = grid[a - 1][b] + grid[a - 1][b + 1] + grid[a + 1][b + 1] + grid[a][b + 1] + grid[a + 1][b];
                    }

                    if (b == 5 && a != 0 && a != 5) {
                        suma = grid[a - 1][b - 1] + grid[a][b - 1] + grid[a - 1][b] + grid[a + 1][b - 1] + grid[a + 1][b];
                    }

                    if (grid[a][b] == 0)
                    {
                        if (suma == 3)
                        {
                            zywosc[a][b] = 1;
                        }
                        else {zywosc[a][b]=0;}
                    }

                    if (suma > 3 || suma < 2)
                    {
                        zywosc[a][b] = 0;
                    }
                }

            }
            System.out.println("game of life happens");
            for (int c = 0; c <= 5; c++) {
                for (int d = 0; d <= 5; d++) {
                    if (zywosc[c][d] == 0) {
                        System.out.print("0 ");
                        grid[c][d]=0;
                    } else {
                        System.out.print("1 ");
                        grid[c][d]=1;
                    }
                }
                System.out.println();
            }
        }
    }
}