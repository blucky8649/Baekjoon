import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1507 {
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dst = new int[n][n];
        int[][] arr = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                dst[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = dst[i][j];
            }
        }

        for (int k = 0 ; k < n ; k++) {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < n ; j++) {
                    if (i == k || i == j || j == k) continue;

                    if (dst[i][j] > dst[i][k] + dst[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                    // k를 거쳐가는 곳은 지워야함
                    if (dst[i][j] == dst[i][k] + dst[k][j]) {
                        arr[i][j] = INF;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0 ; i < n ; i++) {
            for (int j = i + 1 ; j < n ; j++) {
                ans += arr[i][j] == INF ? 0 : arr[i][j];
            }
        }
        System.out.println(ans);
        
    }
}
