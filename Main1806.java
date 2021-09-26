import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1806 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] num = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        int head = 0;
        int tail = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while(true){
           
            if(sum >= m){
                sum -= num[head++];
                ans = Math.min(ans, tail - head + 1);
            }
            else if(tail == n) break;
            else{
                sum += num[tail++];
            }
    
        }

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }
    
}
