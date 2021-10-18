import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class quick_sort {
    public static void main(String[] args) throws IOException{

        int[] num = {45, 14, 58, 07, 02, 19, 100, 1, 54, 87, 105};

        int start = 0;
        int end = num.length;
        while (start < end) {
            int pivot = num[start];
            int high = 0;
            int low = 0;

            for (int j = start + 1 ; j < end ; j++) {
                if (pivot < num[j]) {
                    high = j;
                    break;
                }
            }
            for (int j = end - 1 ; j > start ; j-- ) {
                if (pivot > num[j]) {
                    low = j;
                    break;
                }
            }System.out.println(start + " start" + end + " end");
            System.out.println(high + " " + low + " " + pivot);
            if (high < low) {
                int tmp = num[high];
                num[high] = num[low];
                num[low] = tmp;
            } else {
                int tmp = num[start];
                num[start] = num[low];
                num[low] = tmp;

                start ++;
                end = low;
            }
            System.out.println(Arrays.toString(num));
        }
        
    }
    
}
