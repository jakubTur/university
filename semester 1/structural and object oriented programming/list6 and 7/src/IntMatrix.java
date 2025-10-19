import java.util.Arrays;

public class IntMatrix
{
    int[][] array;
    int rows;
    int columns;

    public IntMatrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        array = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            Arrays.fill(array[i], 0);
        }
    }
    public IntMatrix(int[][] matrix)
    {
        rows = matrix.length;
        columns = matrix[0].length;
        array = matrix;
    }
    public int getRows()
    {
    return rows;
    }
    public int getColumns()
    {
        return columns;
    }
    public int getElement(int column, int row)
    {
        return array[row][column];
    }
    public void setElement(int row, int column, int value)
    {
        array[row][column] = value;
    }
    public int[][] addMatrix(int[][] matrix)
    {
        IntMatrix temp = new IntMatrix(matrix.length, matrix[0].length);
        if(temp.rows != this.rows || temp.columns != this.columns)
        {
            throw new IllegalArgumentException("matrices are not the same size");
        }
        else
        {
            for(int i = 0; i<=rows-1; i++)
            {
                for(int j = 0; j<=columns-1; j++)
                {
                    array[i][j] = array[i][j] + matrix[i][j];
                }
            }
            return array;
        }
    }
    public void transpose()
    {
        array = getTransposed(array);
        if(rows != columns)
        {
            int temp = rows;
            rows = columns;
            columns = temp;
        }
    }

    public int[][] getTransposed(int[][] array)
    {
        int[][] transposed = new int[columns][rows];
        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                transposed[i][j] = array[j][i];
            }
        }
        return transposed;
    }

    public int[][] getInverse()
    {
        if(columns != rows)
        {
            throw new IllegalArgumentException("matrix is not square");
        }

    }

    public Boolean symmetric()
    {
        if(columns == rows)
        {
            for(int i = 0; i < columns; i++)
            {
                for(int j = i; j < rows; j++)
                {
                    if(array[i][j] != array[j][i])
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            return false;
        }
        return true;
    }
}
