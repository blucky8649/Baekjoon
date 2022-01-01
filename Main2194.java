import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2194 {
    static int[][] map;
    static boolean[][] isVisited;
    static int n, m, a, b, k;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m]; 
        for (int i = 0 ; i < k ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = -1;
        }

        st = new StringTokenizer(br.readLine(), " ");
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int sx = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine(), " ");
        int ey = Integer.parseInt(st.nextToken()) - 1;
        int ex = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(BFS(sy, sx, ey, ex));
    }
    static int BFS(int sy, int sx, int ey, int ex) {
        Queue<M2194> q = new LinkedList<>();
        q.offer(new M2194(sx, sy, 0));
        isVisited[sy][sx] = true;

        while (!q.isEmpty()) {
            M2194 d = q.poll();
            if (d.x == ex && d.y == ey) {
                return d.cnt;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !isVisited[ny][nx] && check(nx, ny)) {
                    q.offer(new M2194(nx, ny, d.cnt + 1));
                    isVisited[ny][nx] = true;
                }
            }
        }
        return -1;
    }
    static boolean check(int x, int y) {
        for (int i = y ; i < y + a ; i++) {
            for (int j = x ; j < x + b ; j++) {
                if (i < 0 || i >= n || j < 0 || j >= m || map[i][j] == -1) return false; 
            }
        }
        return true;
    }
}
class M2194 {
    int x;
    int y;
    int cnt;
    M2194(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}