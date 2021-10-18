import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main21610 {
    static int n, m, cnt = 0;
    static int[][] map, info;
    static boolean[][] cloud;
    static Queue<Cloud> q = new LinkedList<>();

    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 구름 생성 및 이동
        // 2. 이동 후 구름이 있는 바구니에 물이 1만큼 증가
        // 3. 구름 제거
        // 4. 대각선 물복사 버그
        // 5. 구름 재생성 (바구니에 담긴 물이 2 이상인 곳이며, 전에 구름이 있던 자리는 안됨)


        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        

        for (int i = 0  ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0  ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        info = new int[m][2];
        // 구름 첫 시작 위치
        q.offer(new Cloud(0, n - 2));
        q.offer(new Cloud(0, n - 1));
        q.offer(new Cloud(1, n - 2));
        q.offer(new Cloud(1, n - 1));
        for (int i = 0  ; i < m ; i++) {
            
            
            st = new StringTokenizer(br.readLine(), " ");
            int dir = Integer.parseInt(st.nextToken());
            int move = Integer.parseInt(st.nextToken());
            info[i][0] = dir;
            info[i][1] = move;
            
            if (i > 0) q = make_cloud();

            move_cloud(i);
            
            

        }
        make_cloud();
        int ans = 0;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                ans += map[i][j];
            }
        }
        System.out.println(ans);

    }

    static Queue<Cloud> make_cloud() {
        Queue<Cloud> clone = new LinkedList<>();
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                // 기존에 구름이 있던 자리는 안됨.
                if (map[i][j] >= 2 && !cloud[i][j]) {
                    clone.offer(new Cloud(j, i));
                    map[i][j] -= 2;
                    if (map[i][j] < 0) map[i][j] = 0;
            
                }
            }
        }
        return clone;
    }
    static void move_cloud(int cnt) {
        cloud = new boolean[n][n];
        Queue<Cloud> q2 = new LinkedList<>();
        while (!q.isEmpty()) {
            Cloud d = q.poll();
            
            
            int nx = d.x + dx[info[cnt][0]] * info[cnt][1];
            int ny = d.y + dy[info[cnt][0]] * info[cnt][1];
            while (!(nx >= 0 && nx < n)) {
                if (nx < 0) {
                    nx += n;
                } else if (nx >= n) {
                    nx -= n;
                }
            }
            
            while (!(ny >= 0 && ny < n)) {
                if (ny < 0) {
                    ny += n;
                } else if (ny >= n) {
                    ny -= n;
                }
            }
            
           
            cloud[ny][nx] = true;
            map[ny][nx]++;
            q2.offer(new Cloud(nx, ny));
        }
        
        paste_water(q2); // 물복사 버그 진행
    }

    static void paste_water(Queue<Cloud> q) {
        // 대각선
        int[] dy2 = {0, -1, -1, 1, 1};
        int[] dx2 = {0, -1, 1, 1, -1};
        
        while (!q.isEmpty()) {
            Cloud d = q.poll();
            for (int a =  1 ; a <= 4 ; a++) {
                int nx = d.x + dx2[a];
                int ny = d.y + dy2[a];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[ny][nx] > 0) {
                    map[d.y][d.x]++;
                }
            }
        }        
                
    }
    
}
class Cloud {
    int x;
    int y;
    Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }
}