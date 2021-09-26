import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2512 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int high = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] num = new int[n];
        int m = Integer.parseInt(br.readLine());
        int answer = 0;
        for(int i = 0 ; i < n ; i++){
            num[i] = Integer.parseInt(st.nextToken());

            if(num[i] > high){
                high = num[i];
            }
        }
        int low = 0;
        int mid = 0;
        while(low <= high){
            mid = (high + low) / 2;
            int sum = 0;
            for(int i = 0 ; i < n ; i++){
                sum += num[i] >= mid ? mid : num[i];
            }

            if(sum > m){
                high = mid - 1;
            }else{
                low = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        System.out.println(answer);
    }
    
}
