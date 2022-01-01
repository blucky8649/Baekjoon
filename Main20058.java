import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main20058 {
    static int n, q, map[][], mapSize, sum = 0, max = 0;
    static boolean[][] isVisited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1}; 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); 
        q = Integer.parseInt(st.nextToken()); // 시전하는 파이어스톰 갯수

        mapSize = (int)Math.pow(2, n);
        isVisited = new boolean[mapSize][mapSize];
        map = new int[mapSize][mapSize];
        for (int i = 0 ; i < mapSize ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < mapSize ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < q ; i++) {
            int L = Integer.parseInt(st.nextToken());
            fire_storm(L);
            melt_ice();
            
        }
        solve();
        System.out.println(sum + "\n" + max);
        
        
    }
    static void fire_storm(int L) {
        int area = (int) Math.pow(2, L);
        for (int i = 0  ; i < mapSize ; i += area) {
            for (int j = 0 ; j < mapSize ; j+=area) {
                rotate(j, i, area - 1);
            }
        }
    }

    static void rotate(int sx, int sy, int n) {

        int[][] sub = new int[n + 1][n + 1];
        for (int i = sy ; i <= sy + n ; i++) {
            for (int j = sx ; j <= sx + n ; j++) {
                sub[i - sy][j - sx] =map[i][j];
            }
        }
        // 회전 방법
        // 1. 가운데를 기준으로 상하 반전 시킨다.
        //System.out.println(sx + " " + sy + " " + ex + " " + ey);
        for (int i = 0 ; i < sub.length / 2; i++) {
            for (int j = 0 ; j < sub.length ; j++) {
                int tmp = sub[i][j];
                sub[i][j] = sub[sub.length - i - 1][j];
                sub[sub.length - i - 1][j] = tmp;
            }
        }
        
        // 2. 대각선을 기준으로 반전 시킨다.
        for (int i = 0 ; i < sub.length ; i++) {
            for (int j = i ; j < sub.length ; j++) {
                int tmp = sub[i][j];
                sub[i][j] = sub[j][i];
                sub[j][i] = tmp;
            }
        }
        

        for (int i = 0 ; i < sub.length ; i++) {
            for (int j = 0 ; j < sub.length ; j++) {
                map[i + sy][j + sx] = sub[i][j];
            }
        }
    }

    static void melt_ice() {
        Queue<IceSet> q = new LinkedList<>();
        for (int i = 0 ;i < map.length ; i++) {
            for (int j = 0 ; j < map.length ;j++) {
                if (map[i][j] == 0) continue;
                int cnt = 0;
                for (int a = 0 ; a < 4 ; a++) {
                    int nx = j + dx[a];
                    int ny = i + dy[a];
                    if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize && map[ny][nx] > 0) {
                        cnt++;
                    }
                }

                if (cnt < 3) {
                    q.offer(new IceSet(j, i));
                }
            }
        }

        while (!q.isEmpty()) {
            IceSet d = q.poll();
            map[d.y][d.x]--;
            sum--;
        }
    }
    static void solve() {
        for (int i = 0 ; i < mapSize ; i++) {
            for (int j = 0 ; j < mapSize ; j++) {
                if (!isVisited[i][j] && map[i][j] > 0) {
                    max = Math.max(count_iceset(j, i), max);
                }
            }
        }
    }
    static int count_iceset(int x, int y) {
        int cnt = 0;
        Queue<IceSet> q = new LinkedList<>();
        q.offer(new IceSet(x, y));

        while (!q.isEmpty()) {
            IceSet d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                
                if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize && map[ny][nx] > 0 && !isVisited[ny][nx]) {
                    cnt++;
                    isVisited[ny][nx] = true;
                    q.offer(new IceSet(nx, ny));

                }
            }
        }
        return cnt;
    }
}
class IceSet {
    int x;
    int y;
    IceSet (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
