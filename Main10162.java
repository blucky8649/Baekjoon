import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10162 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int a = 300;
        int b = 60;
        int c = 10;

        
        int ans_a = n / a;
        n %= a;
        int ans_b = n / b;
        n %= b;
        int ans_c = n / c;
        n %= c;
        
        System.out.println(n == 0 ? ans_a + " " + ans_b + " " + ans_c : -1);
    }
}
