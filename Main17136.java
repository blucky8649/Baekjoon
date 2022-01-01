import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17136 {
    static int ans = Integer.MAX_VALUE;
    static int attachable = 0;
    static int map[][] = new int[10][10];
    static boolean[][] isVisited = new boolean[10][10];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < 10 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < 10 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = 9;
                }
            }
        }
        
        int[] count = new int[6];
        DFS(0, 0, count, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);        
    }

    static void DFS(int x, int y, int[] count, int cnt) {
        if (x == 10 && y == 9) {
            ans = Math.min(ans, cnt);
            return;
        }
        if (x == 10) {
            DFS(0, y + 1, count, cnt);
            return;
        }

        if (cnt >= ans) return;
        if (map[y][x] == 9) {
            for (int a = 5 ; a >= 1 ; a--) {
                if (attach_paper(x, y, a) && count[a] < 5) {
                    attach_paper2(x, y, a);
                    count[a]++;
                    DFS(x + 1, y, count, cnt + 1);
                    count[a]--;
                    detach_paper2(x, y, a);
                } 
            }
        } else {
            DFS(x + 1, y, count, cnt);
        }
        
        
    }
    static boolean attach_paper(int x, int y, int a) {
        for (int i = y ; i < a + y ; i++) {
            for (int j = x ; j < a + x ; j++) {
                if (i < 0 || j < 0 || i >= 10 || j >= 10 || map[i][j] != 9) return false;
            }
        }
        return true;
    }
    static void attach_paper2(int x, int y, int a) {
        for (int i = y ; i < a + y ; i++) {
            for (int j = x ; j < a + x ; j++) {
                map[i][j] = a;
            }
        }
    }
    static void detach_paper2(int x, int y, int a) {
        for (int i = y ; i < a + y ; i++) {
            for (int j = x ; j < a + x ; j++) {
                map[i][j] = 9;
            }
        }
    }
}
