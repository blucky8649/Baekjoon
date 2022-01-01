import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14925 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine() , " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m + 1];
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1 ; j <= m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        int max = 0;
        
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1 ; j <= m ; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = Math.min(map[i - 1][j], Math.min(map[i][j - 1], map[i - 1][j - 1])) + 1;
                    max = Math.max(max, map[i][j]);
                }
                
            }
        }
        System.out.println(max);
    }
}
