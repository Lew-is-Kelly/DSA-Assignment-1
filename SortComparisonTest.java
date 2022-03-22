import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//-------------------------------------------------------------------------

/**                     - insert     - selection  - quick     - merge rec - merge iter
 * 1000 random          - 1535033ns  - 1810066ns  - 281400ns  - 259300ns  - 1026600ns
 * 1000 few unique      - 140000ns   - 312066ns   - 35866ns   - 157933ns  - 143566ns
 * 1000 nearly ordered  - 37366ns    - 262833ns   - 37366ns   - 116200ns  - 121366ns
 * 1000 reverse order   - 183533ns   - 410900ns   - 383466ns  - 201933ns  - 104233ns
 * 1000 sorted          - 1500ns     - 251433ns   - 972166ns  - 104800ns  - 294566ns
 * 10000 random         - 21793566ns - 41505700ns - 1438000ns - 2286666ns - 2270500ns
 *
 * a. Which of these sorting algorithms does the order of input have an impact on? Why?
 *      A large impact can be seen on insertion sort and quick sort as when the list is: nearly ordered insertion sort
 *      takes 37366ns on average, reverse order it takes 183533ns and sorted takes 1500ns. This is because insertion
 *      and quick sort go through the array and will need to restart in order to sort. This makes them much faster for
 *      smaller arrays and a lot longer for larger arrays.
 * b. Which algorithm has the biggest difference between the best and worst performance, based
 * on the type of input, for the input of size 1000? Why?
 *      Insertion sort has a difference of (1535033ns - 1500ns) 1533533ns for the size of 1000. This is because it must
 *      restart from the beginning to sort the array.
 * c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 * based on the input size? Please consider only input files with random order for this answer.
 *      Selection Sort is the worst for scalability as it has the largest difference in performance from 1000 to 10000.
 *      The best was merge sort iterative as it has the smallest increase of time between the files.
 * d. Did you observe any difference between iterative and recursive implementations of merge
 * sort?
 *      Iterative merge sort was faster except for when the list was already sorted.
 * e. Which algorithm is the fastest for each of the 7 input files?
 *      Quick Sort is fastest.
 *
 * /

//-------------------------------------------------------------------------

/**
 * Test class for SortComparison.java
 *
 * @author Lewis Kelly
 * @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------

    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        double[] emptyArray = {};
        double[] testArray = {};

        assertArrayEquals("insertionSort on empty array failed.", SortComparison.insertionSort(testArray), emptyArray, 0);
        testArray = new double[] {};

        assertArrayEquals("selectionSort on empty array failed.", SortComparison.selectionSort(testArray), emptyArray, 0);
        testArray = new double[] {};

        assertArrayEquals("quickSort on empty array failed.", SortComparison.quickSort(testArray), emptyArray, 0);
        testArray = new double[] {};

        assertArrayEquals("mergeSortIterative on empty array failed.", SortComparison.mergeSortIterative(testArray), emptyArray, 0);
        testArray = new double[] {};

        assertArrayEquals("mergeSortRecursive on empty array failed.", SortComparison.mergeSortRecursive(testArray), emptyArray, 0);
    }

    @Test
    public void testSingleElement()
    {
        double[] singleElementArray = {1};
        double[] testArray = {1};

        assertArrayEquals("insertionSort on array with single element failed.", SortComparison.insertionSort(testArray), singleElementArray, 0);
        testArray = new double[] {1};

        assertArrayEquals("selectionSort on array with single element failed.", SortComparison.selectionSort(testArray), singleElementArray, 0);
        testArray = new double[] {1};

        assertArrayEquals("quickSort on array with single element failed.", SortComparison.quickSort(testArray), singleElementArray, 0);
        testArray = new double[] {1};

        assertArrayEquals("mergeSortIterative on array with single element failed.", SortComparison.mergeSortIterative(testArray), singleElementArray, 0);
        testArray = new double[] {1};

        assertArrayEquals("mergeSortRecursive on array single element failed.", SortComparison.mergeSortRecursive(testArray), singleElementArray, 0);
    }

    @Test
    public void testTwoElements()
    {
        double[] twoElementArray = {1, 2};
        double[] testArray = {2, 1};

        assertArrayEquals("insertionSort on array with two elements failed.", SortComparison.insertionSort(testArray), twoElementArray, 0);
        testArray = new double[] {2, 1};

        assertArrayEquals("quickSort on array with two elements failed.", SortComparison.selectionSort(testArray), twoElementArray, 0);
        testArray = new double[] {2, 1};

        assertArrayEquals("quickSort on array with two elements failed.", SortComparison.quickSort(testArray), twoElementArray, 0);
        testArray = new double[] {2, 1};

        assertArrayEquals("mergeSortIterative on array with two elements failed.", SortComparison.mergeSortIterative(testArray), twoElementArray, 0);
        testArray = new double[] {2, 1};

        assertArrayEquals("mergeSortRecursive on array with two elements failed.", SortComparison.mergeSortRecursive(testArray), twoElementArray, 0);
    }

    @Test
    public void testFull()
    {
        double[] testArray = {5, 4, 3, 3.5, 2, 1};
        double[] fullArray = {1, 2, 3, 3.5, 4, 5};

        assertArrayEquals("insertionSort on full array failed.", SortComparison.insertionSort(testArray), fullArray, 0);
        testArray = new double[] {5, 4, 3, 3.5, 2, 1};

        assertArrayEquals("selectionSort on full array failed.", SortComparison.selectionSort(testArray), fullArray, 0);
        testArray = new double[] {5, 4, 3, 3.5, 2, 1};

        assertArrayEquals("quickSort on full array failed.", SortComparison.quickSort(testArray), fullArray, 0);
        testArray = new double[] {5, 4, 3, 3.5, 2, 1};

        assertArrayEquals("mergeSortIterative on full array failed.", SortComparison.mergeSortIterative(testArray), fullArray, 0);
        testArray = new double[] {5, 4, 3, 3.5, 2, 1};

        assertArrayEquals("mergeSortRecursive on full array failed.", SortComparison.mergeSortRecursive(testArray), fullArray, 0);
    }

    @Test
    public void testNull()
    {
        double[] testArray = null;

        assertArrayEquals("insertionSort on null array failed.", SortComparison.insertionSort(testArray), null, 0);
        testArray = null;

        assertArrayEquals("selectionSort on null array failed.", SortComparison.selectionSort(testArray), null, 0);
        testArray = null;

        assertArrayEquals("quickSort on null array failed.", SortComparison.quickSort(testArray), null, 0);
        testArray = null;

        assertArrayEquals("mergeSortIterative on null array failed.", SortComparison.mergeSortIterative(testArray), null, 0);
        testArray = null;

        assertArrayEquals("mergeSortRecursive on null array failed.", SortComparison.mergeSortRecursive(testArray), null, 0);
    }

    @Test
    public void testSorted()
    {
        double[] testArray  = new double[] {1, 2, 3, 4, 5, 6};
        double[] sortedArray = new double[] {1, 2, 3, 4, 5, 6};

        assertArrayEquals("insertionSort on sorted array failed.", SortComparison.insertionSort(testArray), sortedArray, 0);
        testArray = new double[] {1, 2, 3, 4, 5, 6};

        assertArrayEquals("selectionSort on sorted array failed.", SortComparison.selectionSort(testArray), sortedArray, 0);
        testArray = new double[] {1, 2, 3, 4, 5, 6};

        assertArrayEquals("quickSort on sorted array failed.", SortComparison.quickSort(testArray), sortedArray, 0);
        testArray = new double[] {1, 2, 3, 4, 5, 6};

        assertArrayEquals("mergeSortIterative on sorted array failed.", SortComparison.mergeSortIterative(testArray), sortedArray, 0);
        testArray = new double[] {1, 2, 3, 4, 5, 6};

        assertArrayEquals("mergeSortRecursive on sorted array failed.", SortComparison.mergeSortRecursive(testArray), sortedArray, 0);
    }

    // ----------------------------------------------------------

    /**
     * Main Method.
     * Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the number (integer) of times you want to test the algorithms: ");
        int reps = scanner.nextInt();

        double[] arr = getArrayFromFile("numbers1000.txt", 1000);
        experiment(arr, "numbers1000.txt", reps);

        arr = getArrayFromFile("numbers10000.txt", 10000);
        experiment(arr, "numbers10000.txt", reps);

        arr = getArrayFromFile("numbersSorted1000.txt", 1000);
        experiment(arr, "numbersSorted1000.txt", reps);

        arr = getArrayFromFile("numbersReverse1000.txt", 1000);
        experiment(arr, "numbersReverse1000.txt", reps);

        arr = getArrayFromFile("numbersNearlyOrdered1000.txt", 1000);
        experiment(arr, "numbersNearlyOrdered1000.txt", reps);

        arr = getArrayFromFile("numbers1000Duplicates.txt", 1000);
        experiment(arr, "numbers1000Duplicates.txt", 3);
    }

    private static double[] getArrayFromFile(String fileName, int size) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(fileName));
        double[] arr = new double[size];
        int i = 0;
        while(scanner.hasNextDouble())
        {
            arr[i++] = scanner.nextDouble();
        }
        return arr;
    }

    private static void experiment(double[] arr, String fileName, int reps)
    {
        System.out.println("\nNow testing the average over "+ reps + " times of" + fileName + ":");

        double[] a;
        long[] times = new long[reps];

        for (int i = 0; i < reps; i++)
        {
            a = Arrays.copyOf(arr, arr.length);

            final long startTime = System.nanoTime();
            SortComparison.insertionSort(a);
            times[i] = System.nanoTime() - startTime;
        }

        long totalTime = 0;
        for (long time : times)
        {
            totalTime += time;
        }

        System.out.println("Average insertionSort time of " + fileName + " is: " + (totalTime / reps) + "ns");

        for (int i = 0; i < reps; i++)
        {
            a = Arrays.copyOf(arr, arr.length);

            final long startTime = System.nanoTime();
            SortComparison.selectionSort(a);
            times[i] = System.nanoTime() - startTime;
        }

        totalTime = 0;
        for (long time : times)
        {
            totalTime += time;
        }

        System.out.println("Average selectionSort time of " + fileName + " is: " + (totalTime / reps) + "ns");

        for (int i = 0; i < reps; i++)
        {
            a = Arrays.copyOf(arr, arr.length);

            final long startTime = System.nanoTime();
            SortComparison.quickSort(a);
            times[i] = System.nanoTime() - startTime;
        }

        totalTime = 0;
        for (long time : times)
        {
            totalTime += time;
        }

        System.out.println("Average quickSort time of " + fileName + " is: " + (totalTime / reps) + "ns");

        for (int i = 0; i < reps; i++)
        {
            a = Arrays.copyOf(arr, arr.length);

            final long startTime = System.nanoTime();
            SortComparison.mergeSortIterative(a);
            times[i] = System.nanoTime() - startTime;
        }

        totalTime = 0;
        for (long time : times)
        {
            totalTime += time;
        }

        System.out.println("Average mergeSortIterative time of " + fileName + " is: " + (totalTime / reps) + "ns");

        for (int i = 0; i < reps; i++)
        {
            a = Arrays.copyOf(arr, arr.length);

            final long startTime = System.nanoTime();
            SortComparison.mergeSortRecursive(a);
            times[i] = System.nanoTime() - startTime;
        }

        totalTime = 0;
        for (long time : times)
        {
            totalTime += time;
        }

        System.out.println("Average mergeSortRecursive time of " + fileName + " is: " + (totalTime / reps) + "ns");
    }
}

