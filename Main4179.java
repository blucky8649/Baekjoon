import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4179 {
    static int n, m;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static char[][] map;
    static Queue<Jihun> fire = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        Jihun jihun = new Jihun(0, 0, 0);
        for (int i = 0 ; i < n ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'F') {
                    fire.offer(new Jihun(j, i, 0));
                }
                if (map[i][j] == 'J') {
                    jihun = new Jihun(j, i, 0);
                }
            }
        }
        int ans = go(jihun.x, jihun.y);
        System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
    }
    static int go(int x, int y) {
        Queue<Jihun> q = new LinkedList<>();
        q.offer(new Jihun(x, y, 0));

        while (!q.isEmpty()) {
            int size = fire.size();
            for (int a= 0 ; a < size ; a++) {
                Jihun d = fire.poll(); {
                    for (int i = 0 ; i < 4 ; i++) {
                        int nx = d.x + dx[i];
                        int ny = d.y + dy[i];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[ny][nx] != '#' && map[ny][nx] != 'F') {
                            fire.offer(new Jihun(nx, ny, d.cnt + 1));
                            map[ny][nx] = 'F';
                        }
                    }
                }
            }
            size = q.size();
            for (int a = 0 ; a < size ; a++) {
                Jihun d = q.poll();
                for (int i = 0 ; i < 4 ; i++) {
                    int nx = d.x + dx[i];
                    int ny = d.y + dy[i];
                    if (nx < 0 || nx >= m || ny <0 || ny >= n) {
                        return d.cnt + 1;
                    }
                    if (map[ny][nx] != '#' && map[ny][nx] != 'F' && map[ny][nx] != 'J') {
                        q.offer(new Jihun(nx, ny, d.cnt + 1));
                        map[ny][nx] = 'J';
                    }
                }
            }
        }
        return -1;
    }
    static void spread_fire() {
        
    }
}

class Jihun {
    int x;
    int y;
    int cnt;
    Jihun(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}