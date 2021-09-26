import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2110 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[] num = new long[n];
        

        for(int i = 0 ; i < n ; i++){
            num[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(num);
        long high = num[n - 1] - num[0];
        long low = 1;
        long mid = 0;
        long answer = 0;
        while(low <= high){
            mid = (low + high) / 2;
            long left = num[0];
            int count = 1;
            for(int i = 0 ; i < n ; i++){
                 if(num[i] - left >= mid){
                     count ++;
                     left = num[i];
                 } 
            }

            if(count < m){
                high = mid - 1;
            }else{
                low = mid + 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }
    
}
