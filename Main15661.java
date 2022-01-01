import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15661 {
    static int n, answer = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<Integer> player = new ArrayList<>();
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        isVisited = new boolean[n];
        for (int i = 0; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0, 0);
        System.out.println(answer);
    }
    static void DFS(int start, int k) {
        if (k > 1) {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0 ; i < isVisited.length - 1 ; i++) {
                for (int j = i + 1 ; j < isVisited.length ; j++) {
                    if (isVisited[i] && isVisited[j]) {
                        sum1 += map[i][j] + map[j][i];
                    } else if (!isVisited[i] && !isVisited[j]) {
                        sum2 += map[i][j] + map[j][i];
                    }

                }
            }
            answer = Math.min(answer, Math.abs(sum1 - sum2));
            if (answer == 0) {
                System.out.println(0);
                System.exit(0);
            }
        
            if (k == n / 2) return;
        }
        for (int i = start ; i < n ; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                DFS(i + 1, k + 1);
                isVisited[i] = false;
            }
        }
    }
}
