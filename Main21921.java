import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main21921 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] visitor = new int[n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0 ; i < n ; i++){
            visitor[i] = Integer.parseInt(st.nextToken());
        }

        int head = 0;
        int tail = 0;
        int sum = 0;
        int max = 0;
        int cnt = 0;
        while(true){
            if(sum > max){
                cnt = 1;
                max = sum;
            }else if (sum == max){
                cnt++;
            }
            if(tail - head < m){
                sum += visitor[tail++];
                
            }
            else if(tail == n) break;
            else {
                sum -= visitor[head++];
            }
        }
        if(sum == 0){
            System.out.println("SAD");
        }else{
            System.out.print(max + "\n" + cnt);
        }
        
    }
    
}
