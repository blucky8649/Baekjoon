import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");
        long[] dst = new long[n];
   
        for(int i = 0 ; i < n-1 ; i++){
            int dst_1 = Integer.parseInt(st.nextToken());

            dst[i] = dst_1;
        }
        long sum2 = 0;
    
        long[] cost = new long[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i< n-1 ; i++){
            cost[i] = Integer.parseInt(st.nextToken());
            if(i > 0){
                if(cost[i-1] < cost[i] && i != 0){
                    cost[i] = cost[i-1];
                    sum2 += cost[i] * dst[i];
                }else{
                    sum2 += cost[i] * dst[i];
                }
            }else{
                sum2 += cost[i] * dst[i];
            }
            
        }
        
        //안녕
        System.out.println("냐하");
        System.out.println(sum2);

    }
    
}
