import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11047 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        for(int i = 0 ; i < 10 ; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for(int i = n -1 ; i >= 0 ; i--){
            if(coin[i] < K){
                cnt += K / coin[i];
                K  %= coin[i];
                
            }
        }
        System.out.println(cnt);
    }
    
}
