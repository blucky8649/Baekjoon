import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1613 {
    static int n, m;
    static int[][] d;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = new int[n][n];
        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            d[start][end] = 1;
        }

        for (int k = 0 ; k < n ; k++) {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < n ; j++) {
                    if (d[i][k] == 1 && d[k][j] == 1) {
                        d[i][j] = 1;
                    }
                }
            }
        }
        int s = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < s ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            if (d[start][end] == 1) System.out.println(-1);
            else if (d[end][start] == 1) System.out.println(1);
            else System.out.println(0);
        }

    }
    
}
