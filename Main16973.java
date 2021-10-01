import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16973 {
    static int n, m, H, W, sy, sx, ey, ex;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];

        for(int i = 0 ; i < n ; i++){
            st= new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken()) - 1;
        sx = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;
        ex = Integer.parseInt(st.nextToken()) - 1;
        
        System.out.println(BFS(sx, sy));
    }
    static int BFS(int x, int y){
        Queue<Rec> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.offer(new Rec(x, y, 0));

        while (!q.isEmpty()) {
            Rec d = q.poll();
            
            if (d.x == ex && d.y == ey) {
                return d.cnt;
            }

            
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || isVisited[ny][nx]) continue;
                if (!check(nx,ny)) continue;
                
                q.offer(new Rec(nx, ny, d.cnt + 1));
                isVisited[ny][nx] = true;
            }
        }
        return -1;
    }
    static boolean check(int x, int y) {
        for (int i = y ; i < y + H ; i++) {
            if (x + W - 1 < 0 || x + W - 1 >= m || i < 0 || i >= n || map[i][x] == 1 || map[i][x + W - 1] == 1) return false;
        }
        for (int i = x ; i < x + W ; i++) {
            if (y + H - 1 < 0 || y + H - 1 >= n ||i < 0 || i >= m || map[y][i] == 1 || map[y + H - 1][i] == 1) return false;
        }
        
        return true;
    }
}
class Rec {
    int x;
    int y;
    int cnt;
    Rec(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}