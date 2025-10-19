import java.util.Arrays;

public class Main
{
    public static void printMatrix(IntMatrix matrix)
    {
        for(int i = 0; i<=matrix.rows-1; i++)
        {
            System.out.println(Arrays.toString(matrix.array[i]));
        }
    }
    public static void main(String[] args)
    {
        IntMatrix matrix = new IntMatrix(new int[][]{ {1,2,3}, {5,4,2}, {0,7,6}, {0,7,6} });
        printMatrix(matrix);
        System.out.println("rows: " + matrix.getRows());
        System.out.println("columns: " + matrix.getColumns());
        matrix.setElement(0,0,0);
        printMatrix(matrix);
        System.out.println("element at (0,0): " + matrix.getElement(0,0));
        int[][] arr = { {1,2,3}, {5,4,2}, {0,7,6}, {0,7,6} };
        matrix = new IntMatrix(4, 3);
        printMatrix(matrix);
        matrix.addMatrix(arr);
        System.out.println("after addition:");
        printMatrix(matrix);
        matrix.transpose();
        System.out.println("transposed:");
        printMatrix(matrix);
        System.out.println("symmetric: " + matrix.symmetric());
        matrix = new IntMatrix(new int[][] { { 1, 2, 3 }, { 2, 1, 4 }, { 3, 4, 1 }});
        printMatrix(matrix);
        System.out.println("symmetric: " + matrix.symmetric());
    }
}
