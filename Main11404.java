import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11404 {
    static int n, m;
    static int[][] map;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(map[i], INF);
        }
        for (int i = 0 ; i < n ; i++) {
            map[i][i] = 0;
        }
        for (int i = 0 ; i < m ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            if (map[start][end] > weight) {
                map[start][end] = weight;
            }
        }
        F_W();

    }
    static void F_W() {
        int[][] d = new int[n][n];
        for (int i = 0 ; i < n ; i++) {
            d[i] = map[i].clone();
        }

        for (int k = 0 ; k < n ; k++) {
            for (int start = 0 ; start < n ; start++) {
                for (int end = 0  ; end < n ; end++) {
                    d[start][end] = Math.min(d[start][k] + d[k][end], d[start][end]) ;
                }
            }
        }
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                System.out.print(d[i][j] == INF ? 0 + " " : d[i][j] + " ");
            }
            System.out.println();
        }
    }
}
