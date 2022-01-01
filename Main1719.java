import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1719 {
    static int n, m;
    static int[][] d, ans;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = new int[n + 1][n + 1];
        for (int i = 0 ; i <= n ; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
        ans = new int[n + 1][n + 1];
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            d[start][end] = weight;
            ans[start][end] = end;
            d[end][start] = weight;
            ans[end][start] = start;
        }

        F_W();
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= n ; j++) {
                System.out.print((ans[i][j] == 0 ? "-" : ans[i][j]) + " ");
            }
            System.out.println();
        }
    }
    static void F_W() {
        for (int k = 1 ; k <= n ; k++) {
            for (int start = 1; start <= n ; start++) {
                for (int end = 1 ; end <= n ; end++) {
                    if (d[start][end] > d[start][k] + d[k][end]) {
                        d[start][end] = d[start][k] + d[k][end];
                        ans[start][end] = ans[start][k];
                    }
                }
            }
        }
        
    }
}
