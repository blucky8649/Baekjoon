import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main9465 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        
        for (int i = 0 ; i < t ; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr= new int[3][n + 1];

            int[] dp1 = new int[n + 1];
            int[] dp2 = new int[n + 1]; 
            for (int j = 1 ; j <= 2 ; j ++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int z = 1 ; z <= n ; z++) {
                    arr[j][z] = Integer.parseInt(st.nextToken());
                }
            }
            dp1[1] = arr[1][1];
            dp2[1] = arr[2][1];

            for (int j = 2 ; j <= n ; j++) {
                dp1[j] = Math.max(dp2[j - 1],  dp2[j - 2]) + arr[1][j];
                dp2[j] = Math.max(dp1[j - 1],  dp1[j - 2]) + arr[2][j];
            }
            System.out.println(Math.max(dp1[n], dp2[n]));
        }
        


    }
    
}
