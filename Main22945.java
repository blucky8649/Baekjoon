import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main22945 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        

        int head = 0;
        int tail = n - 1;
        int max = 0;
        while(head <= tail){

            int status = (tail - head - 1) * Math.min(num[head], num[tail]);
            max = Math.max(max,status);
            if(num[head] < num[tail]){
                head++;
            }else{
                tail--;
            }
        }

        System.out.println(max);
        
    }
    
}
