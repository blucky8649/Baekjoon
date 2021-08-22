import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1920 {
    static int arr[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i =0 ;  i < n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; i < m ; i ++){
            System.out.println(BinarySearch(Integer.parseInt(st.nextToken()), 0, n - 1));
        }


    }
    static int BinarySearch(int num, int low, int high){
        while(low <= high){
            int mid = (low + high) / 2;

            if(num == arr[mid]) return 1;
            else if(num < arr[mid]) high = mid -1;
            else low = mid + 1; 
        }
        

        
        return 0;
    }
    
}
