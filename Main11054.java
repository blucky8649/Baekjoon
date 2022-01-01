import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11054 {
    static int arr[], l_dp[], r_dp[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        r_dp = new int[n];
        l_dp = new int[n];
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        calc();
        for (int i = 0 ; i < n ; i++) {
            max = Math.max(max, r_dp[i] + l_dp[i]);
        }
        System.out.println(max -1);
    }
    static void calc() {
        for (int i = 0 ; i < arr.length ; i++) {
            l_dp[i] = 1;
            for (int  j = 0 ; j < i ; j++) {
                if (arr[j] < arr[i]) {
                    l_dp[i] = Math.max(l_dp[i], l_dp[j] + 1);
                }
            }
        }

        for (int i = arr.length -1 ; i >= 0  ; i--) {
            r_dp[i] = 1;
            for (int  j = arr.length - 1 ; j > i ; j--) {
                if (arr[j] < arr[i]) {
                    r_dp[i] = Math.max(r_dp[i], r_dp[j] + 1);
                }
            }
        }
    }

}
