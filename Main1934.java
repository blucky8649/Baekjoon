import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1934 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCM(a, b)).append("\n");
        }
        
        System.out.print(sb.toString());
    }
    public static int GCD(int a, int b){
        int r= 0 ;
        while(b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static int LCM(int a, int b){
        return a * b / GCD(a, b);
    }
    
}