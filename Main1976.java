import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1976 {
    static int N, M;
    static int[][] map;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 도시의 수
        M = Integer.parseInt(br.readLine()); // 가려는 도시의 수
        map = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0; 
        }

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < N ; j++) {
                int connect = Integer.parseInt(st.nextToken());

                if (connect == 1) {
                    map[i][j] = 1;
                }
            }
        }

        for (int k = 0 ; k < N ; k++) {
            for (int start = 0 ; start < N ; start ++) {
                for (int end = 0 ; end < N ; end ++) {
                    if (map[start][end] > map[start][k] + map[k][end]) {
                        map[start][end] = map[start][k] + map[k][end];
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] routes = new int[M];
        for (int i = 0 ; i < M ; i++) {
            routes[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        boolean go = true;
        for (int i = 0 ; i < M - 1; i++) {
            if (map[routes[i]][routes[i + 1]] == INF) {
                go = false;
            }
        }
        System.out.println(go ? "YES" : "NO");
    }
}
