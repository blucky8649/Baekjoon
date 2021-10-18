import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5597 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] std = new int[31];
        
        for (int i = 0 ; i < 28 ; i++) {
            int n = Integer.parseInt(br.readLine());
            std[n] ++;
        }

        for (int i = 1 ; i <= 30 ; i++) {
            if (std[i] == 0) {
                System.out.println(i);
            }
        }
    }
    
}
