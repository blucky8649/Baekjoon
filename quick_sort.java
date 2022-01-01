import java.io.IOException;
import java.util.Arrays;

public class quick_sort {
    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    static void quickSort(int[] arr, int start, int end) {
        int center = partition(arr, start, end);
        if (start < center - 1) {
            quickSort(arr, start, center - 1);
        }
        if (end > center) {
            quickSort(arr, center, end);
        }
    }@Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
    static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start + end) / 2];
        while (start <= end) {
            while (arr[start] < pivot) start++;
            while (arr[end] > pivot) end--;

            if (start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }
    static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }
    public static void main(String[] args) throws IOException{
        int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2, 5453 , 545, 5, 87, 54, 45, 587, 12, 3};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
}
