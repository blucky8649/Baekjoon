import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3980 {
    static int ans;
    static int[][] player = new int[11][11];
    static boolean[] isPositioned = new boolean[11];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0 ; tc < t ; tc++) {
            ans = 0;
            for (int i = 0 ; i < 11 ; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0 ; j < 11 ; j++) {
                    player[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            DFS(0, 0);
            System.out.println(ans);
        }
    }
    static void DFS(int k, int sum) {
        if (k == 11) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0 ; i < 11 ; i++) {
            if (player[k][i] == 0) continue;
            if (!isPositioned[i]) {
                isPositioned[i] = true;
                DFS(k + 1, sum + player[k][i]);
                isPositioned[i] = false;
            }
        }
    }
}
