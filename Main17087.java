import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17087 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long max = 1000000001;
        int n = Integer.parseInt(st.nextToken());
        long[] arr= new long[n];
        long s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n ; i++){
            long a = Integer.parseInt(st.nextToken());
            arr[i] = Math.abs(s - a);
            
        }
      
            max = arr[0];
       
            for(int i = 0 ; i <arr.length ; i++){
                max = gcd(max, arr[i]);
                
            }
        
        
        
        System.out.println(max);
        
    }
    public static long gcd(long a, long b){
        if (b == 0) return a;
	    else return gcd(b, a % b);
    }
    
}
