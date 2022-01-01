import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main5022 {
    static Cable a1, a2, b1, b2;
    static int R, C, ans = 1_000_000_000;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        st = new StringTokenizer(br.readLine(), " ");
        a1 = new Cable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, new ArrayList<>(), new ArrayList<>());
        st = new StringTokenizer(br.readLine(), " ");
        a2 = new Cable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, new ArrayList<>(), new ArrayList<>());
        st = new StringTokenizer(br.readLine(), " ");
        b1 = new Cable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, new ArrayList<>(), new ArrayList<>());
        st = new StringTokenizer(br.readLine(), " ");
        b2 = new Cable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, new ArrayList<>(), new ArrayList<>());
        isVisited = new boolean[R + 1][C + 1];
        int op1 = BFS(a1, a2, 1);
        int op2 = BFS(b1, b2, 2);
        if (op1 + op2 > 0) {
            ans = Math.min(ans, op1 + op2);
        }
        
        isVisited = new boolean[R + 1][C + 1];
        int op3 = BFS(b1, b2, 2);
        int op4 = BFS(a1, a2, 1);
        if (op3 + op4 > 0) {
            ans = Math.min(ans, op3 + op4);
        }

        System.out.println(ans < 1_000_000_000 ? ans : "IMPOSSIBLE");
        

        
    }
    static int BFS(Cable s, Cable e, int num) {
        Queue<Cable> q = new LinkedList<>();
        isVisited[s.y][s.x] = true;
        s.route_x.add(s.x);
        s.route_y.add(s.y);
        q.offer(s);
        
        
        while (!q.isEmpty()) {
            Cable d = q.poll();
            if (d.x == e.x && d.y == e.y) {
                isVisited = new boolean[R+1][C+1];
                for (int i = 0 ; i < d.route_x.size() ; i++) {
                    isVisited[d.route_y.get(i)][d.route_x.get(i)] = true;
                }
                return d.cnt;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx < 0 || nx > C || ny < 0 || ny > R || isVisited[ny][nx]) continue;
                if (num == 1 && ((nx == b1.x && ny == b1.y) || (nx == b2.x && ny == b2.y))) continue;
                if (num == 2 && ((nx == a1.x && ny == a1.y) || (nx == a2.x && ny == a2.y))) continue;
                Cable n = new Cable(nx, ny, d.cnt + 1, new ArrayList<>(d.route_x), new ArrayList<>(d.route_y));
                n.route_x.add(nx);
                n.route_y.add(ny);
                q.offer(n);
                isVisited[ny][nx] = true;
                
            }
        }
        return -1_000_000_000;
    }
}
class Cable {
    int x;
    int y;
    int cnt;
    ArrayList<Integer> route_x;
    ArrayList<Integer> route_y;
    Cable(int x, int y, int cnt, ArrayList<Integer> route_x, ArrayList<Integer> route_y) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.route_x = route_x;
        this.route_y = route_y;
    }
}
class Cable2 {
    int x;
    int y;
    Cable2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}