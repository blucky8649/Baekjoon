import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11967 {
    static int N, M, ans = 1;
    static int map[][];
    static boolean isVisited[][];
    static HashMap<String, ArrayList<bulb>> hm = new HashMap<>();
    
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            if (!hm.containsKey(x1 + " " + y1)) {
                hm.put(x1 + " " + y1, new ArrayList<>());
            } 
            
            hm.get(x1 + " " + y1).add(new bulb(x2, y2, 0));
            
        }
        BFS(0, 0);
        System.out.println(ans);

    }
    static void BFS(int x, int y) {
        isVisited[y][x] = true;
        Queue<bulb> q = new LinkedList<>();
        q.offer(new bulb(x, y, 0));

        while (!q.isEmpty()) {
            bulb d = q.poll();
            if (hm.containsKey(d.x + " " + d.y)) {
                for (bulb d2 : hm.get(d.x + " " + d.y)) {
                    map[d2.y][d2.x] = 1;
                    ans++;
                }
            }

            for (int i = 0 ; i < 4; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !isVisited[ny][nx] && map[ny][nx] == 1) {
                    isVisited[ny][nx] = true;
                    q.offer(new bulb(nx, ny, d.cnt));
                }
            }
        }
    }
}
class bulb {
    int x;
    int y;
    int cnt; 
    bulb (int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}