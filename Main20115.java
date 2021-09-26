import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main20115 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] drinks = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; i < n ; i++){
            drinks[i] =  Long.parseLong(st.nextToken());
        }
        Arrays.sort(drinks);
        double ans = drinks[n-1];
        n--;

        for(int i = 0 ; i < n ; i++){
            ans+= (double) (drinks[i] / 2.0);
        }
        if(ans % 1 == 0){
            System.out.println((long)ans);
        }else{
            System.out.println(ans);
        }
        
    }
    
}
