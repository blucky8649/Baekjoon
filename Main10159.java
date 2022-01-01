import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main10159 {
    static final int INF = 1_000_000_000;
    static int[][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        for (int i = 0 ; i < m ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            
            if (map[start][end] > 1) {
                map[start][end] = 1;
            }
        }


        for (int k = 0 ; k < n ; k++) {
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < n ; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        for(int i = 0 ; i < n ; i++) {
            int ans = 0;
            for (int j = 0 ; j < n ; j++) {
                if (map[i][j] == INF && map[j][i] == INF) {
                    ans ++;
                }
            }
            System.out.println(ans);
        }

    }

}
