import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main21278 {
    static int N, M;
    static final int INF = 1_000_000_000;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            map[start][end] = 1;
            map[end][start] = 1;
        }
        FW();
    }
    static void FW() {
        for (int k = 0 ; k < N ; k++) {
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j]; 
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int c1 = 0;
        int c2 = 0;
        for (int i = 0 ; i < N - 1 ; i++) {
            for (int j = i + 1 ; j < N ; j++) {
                int dst = 0;
                for (int d = 0 ; d < N ; d++) {
                    dst += Math.min(map[d][i] + map[i][d], map[d][j]+map[j][d]);
                }
                if (min > dst) {
                    c1 = i;
                    c2 = j;
                    min = dst;
                }
            }
        }
        System.out.println((c1+1) + " " + (c2+1) + " " + min);
    }
}
