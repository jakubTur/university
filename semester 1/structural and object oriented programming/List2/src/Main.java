import java.util.Arrays;
import java.util.Random;

public class Main
{
    public static double standardDeviation(int[] array)
    {
        int sum = 0;
        for (int j : array)
        {
            sum += j;
        }
        double averageConstant = (double) sum / array.length;
        double sum2 = 0;
        for (int j : array) {
            sum2 += Math.pow((j - averageConstant), 2);
        }
        return Math.sqrt(sum2/array.length);
    }
    public static int[] bubbleSort(int[] array)
    {
        boolean sorted = false;
        while(!sorted)
        {
            sorted = true;
            for(int i = 0; i<=array.length-2; i++)
            {
                if(array[i]>array[i+1])
                {
                    sorted = false;
                    int temp = array[i];
                    array[i]=array[i+1];
                    array[i+1]=temp;
                }
            }
        }
        return array;
    }
    public static int dominant(int[] array)
    {
        int[] counter = new int[array.length];
        Arrays.fill(counter, 0);
        for(int i = 0; i < array.length; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(array[i]==array[j])
                {
                    counter[i]++;
                }
            }
        }
        int maxIndex=0;
        for(int i = 0; i<counter.length; i++)
        {
            if(counter[i]>maxIndex)
            {
                maxIndex=i;
            }
        }
        return array[maxIndex];
    }
    public static void fillArray(int[] array)
    {
        if(array.length==100)
        {
            for(int i = 0; i<100; i++)
            {
                array[i]=i+1;
            }
        }
    }
    public static void printArray(int[] array)
    {
        for(int i = 0; i<array.length-1; i++)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }

    public static int findMax(int[] array)
    {
        int max = 0;
        for (int j : array)
        {
            if (j > max)
            {
                max = j;
            }
        }
        return max;
    }

    public static void main(String[] args)
    {
        int[] array = new int[100];
        fillArray(array);
        System.out.print("Filled array: ");
        printArray(array);
        System.out.println("The max value is: " + findMax(array));
        Random r = new Random();
        for(int i = 0; i<100; i++) //shuffling the array to have something to sort
        {
            int randomIndex = r.nextInt(array.length-1);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
        System.out.print("Shuffled array: ");
        printArray(array);
        System.out.print("Sorted array: ");
        bubbleSort(array);
        printArray(array);
        int val = r.nextInt(array.length-1);
        array[r.nextInt(array.length-1)] = val;
        array[r.nextInt(array.length-1)] = val;
        array[r.nextInt(array.length-1)] = val;
        System.out.println("The dominant number in the array should be: " + val);
        System.out.println("The dominant number in the array is: " + dominant((array)));
        System.out.println("The standrd deviation of is: " + standardDeviation((array)));
    }

}
