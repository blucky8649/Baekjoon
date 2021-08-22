import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11052 {
    public static int[] card = new int[1001];
    public static int[] cash = new int[1001];
    public static int pi_max = 10001;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1 ; i <= n ; i++){
            card[i] = cash[i] = Integer.parseInt(st.nextToken());
            
        }
        
        DP(n);
        
        System.out.println(cash[n]);

    }


    public static void DP(int n){
        cash[0] = 0;


        for(int i = 2 ; i <= n ;i++){
            for(int j = 1 ; j<= i ; j++){
                cash[i] = Math.min(cash[i], cash[i-j] + card[j]);
                
            }
        }
    }   
    
    
}
