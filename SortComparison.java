// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of numbers
 * using different sort algorithms.
 *
 * @author Lewis Kelly
 * @version HT 2020
 */

class SortComparison
{

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     */
    static double[] insertionSort(double[] a)
    {
        if (a == null)
        {
            return null;
        }

        int n = a.length;
        for (int i = 1; i < n; ++i)
        {
            double key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key)
            {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;
        }
        return a;
    }//end insertionSort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] selectionSort(double[] a)
    {
        if (a == null)
        {
            return null;
        }

        int n = a.length;

        for (int i = 0; i < n - 1; i++)
        {
            int mindex = i;
            for (int j = i + 1; j < n; j++)
            {
                if (a[j] < a[mindex])
                {
                    mindex = j;
                }
            }
            double tmp = a[mindex];
            a[mindex] = a[i];
            a[i] = tmp;
        }
        return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] quickSort(double[] a)
    {
        if (a == null)
        {
            return null;
        }

        int high = a.length - 1;
        int low = 0;
        quickRec(a, low, high);
        return a;
    }//end quicksort

    static void quickRec(double[] a, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(a, low, high);

            quickRec(a, low, pi - 1);
            quickRec(a, pi + 1, high);
        }
    }

    static void swap(double[] a, int i, int j)
    {
        double tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static int partition(double[] a, int low, int high)
    {
        double pivot = a[high];

        int i = low - 1;

        for (int j = low; j <= high; j++)
        {
            if (a[j] < pivot)
            {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return (i + 1);
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *------------------------------------------------------------------------------------------------------------------
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative(double a[])
    {
        if (a == null)
        {
            return null;
        }

        int n = a.length;
        int curr_size;
        int left_start;
        for (curr_size = 1; curr_size <= n - 1;
             curr_size = 2 * curr_size)
        {
            for (left_start = 0; left_start < n - 1;
                 left_start += 2 * curr_size)
            {
                int mid = Math.min(left_start + curr_size - 1, n - 1);

                int right_end = Math.min(left_start
                        + 2 * curr_size - 1, n - 1);
                merge(a, left_start, mid, right_end);
            }
        }
        return a;
    }//end mergesortIterative

    static void merge(double a[], int l, int m, int r)
    {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        double L[] = new double[n1];
        double R[] = new double[n2];

        for (i = 0; i < n1; i++)
            L[i] = a[l + i];
        for (j = 0; j < n2; j++)
            R[j] = a[m + 1 + j];

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                a[k] = L[i];
                i++;
            } else
            {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            a[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive(double a[])
    {
        if (a == null)
        {
            return null;
        }

        if(a.length > 1)
        {
            int mid = a.length / 2;

            double[] left = new double[mid];
            for(int i = 0; i < mid; i++)
            {
                left[i] = a[i];
            }

            double[] right = new double[a.length - mid];
            for(int i = mid; i < a.length; i++)
            {
                right[i - mid] = a[i];
            }
            mergeSortRecursive(left);
            mergeSortRecursive(right);

            int i = 0;
            int j = 0;
            int k = 0;

            while(i < left.length && j < right.length)
            {
                if(left[i] < right[j])
                {
                    a[k] = left[i];
                    i++;
                }
                else
                {
                    a[k] = right[j];
                    j++;
                }
                k++;
            }

            while(i < left.length)
            {
                a[k] = left[i];
                i++;
                k++;
            }
            while(j < right.length)
            {
                a[k] = right[j];
                j++;
                k++;
            }
        }
        return a;
    }//end mergeSortRecursive
}//end class

