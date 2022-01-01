import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2565 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Wire[] wire = new Wire[n];

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            wire[i] = new Wire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(wire);
        int[] dp = new int[n];
        for (int i = 0 ; i < n ; i++) {
            dp[i] = 1; // LIS 구하기 위한 초기화

            for (int j = 0 ; j < i ; j++) {
                // b를 기준으로 LIS를 구한다 
                if (wire[i].b > wire[j].b) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }

        }
        int max = 0;
        for (int i = 0 ; i < n ; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(n - max);
        
    }
}
class Wire implements Comparable<Wire>{
    int a;
    int b;
    Wire(int a, int b) {
        this.a =a;
        this.b =b;
    }
    @Override
    public int compareTo(Wire o) {
        return this.a - o.a;
    }
}