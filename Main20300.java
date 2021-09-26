import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main20300 {
    public static void main(String[] args) throws IOException, NumberFormatException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        long[] num = new long[n];
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n ; i++){
            num[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(num);

        if(num.length % 2 == 1) n -= 1;
        long max = 0;
        for(int i = 0 ; i < n-i ; i++){
            max = Math.max(max, num[i] + num[n - 1 - i]);
        }
        max = Math.max(max, num[num.length -1]);
        System.out.println(max);
    }
    
}
