import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2217 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int max = 0;
        
        int[] weight = new int[n];
        for(int i = 0 ; i < n ; i++){
            int m = Integer.parseInt(br.readLine());
            weight[i] = m;
        }
        Arrays.sort(weight);
        for(int i = 0 ; i < n ; i++){
            max=  Math.max(max, weight[i] * (n-i));
            
        }

        
        
        System.out.println(max);

    }
    
}
