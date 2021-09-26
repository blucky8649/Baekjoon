import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3079 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[] times = new long[n];
        long high = 0;
        for(int i = 0 ; i < n ; i++){
            times[i] = Long.parseLong(br.readLine());
            if(times[i] > high){
                high = times[i];
            }
        }
        
        high *= m;
        long low = 0;
        long mid = 0;
        long answer = 0;
        while(low <= high){
            mid = (low + high) / 2;

            long sum  = 0;
            for(int i = 0 ; i < times.length ; i++){
                sum += mid / times[i];
            }

            if(sum < m){
                low = mid + 1;
            }else{
                high = mid - 1;
                answer = mid; 
            }
        }
        System.out.print(answer);
        
    }
    
}
