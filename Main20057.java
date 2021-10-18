import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main20057 {
    static int N, ans;
    static int[][] map, dust;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[][] wind_y; 
    static int[][] wind_x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dust = new int[N][N];
        make_snail();

        wind_y = new int[10][2]; //[퍼지는 영역 수][위치, 가중치]
        wind_x = new int[10][2];
        

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for (int j = 0 ; j < N ; j++) {
                dust[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Move2 s = new Move2(N / 2, N / 2, -1); // 바람 시작 지점

        move_tonado(s.x, s.y);
        System.out.println(ans);

    }
    static void move_tonado(int x, int y) {
        Queue<Move2> q = new LinkedList<>();
        q.offer(new Move2(x, y, -1));

        while (!q.isEmpty()) {
            Move2 d = q.poll();

            if (dust[d.y][d.x] > 0) {
                spread_dust(d.x, d.y, d.dir);
            }
            if (map[d.y][d.x] == N*N) break;

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[ny][nx] == map[d.y][d.x] + 1) {
                    q.offer(new Move2(nx, ny, i));
                }
            }
        }
        
    }
    static void spread_dust(int x, int y, int dir) {
        int[][] dsx = {{-1, 1, -2, -1, 1, 2, -1, 1, 0}, {-1, -1, 0, 0, 0, 0, 1, 1, 2},
                        {1, -1, 2, 1, -1, -2, 1, -1, 0}, {1, 1, 0, 0, 0, 0, -1, -1, -2}};
        int[][] dsy = {{1, 1, 0, 0, 0, 0, -1, -1, -2}, {-1, 1, -2, -1, 1, 2, -1, 1, 0},
                        {-1, -1, 0, 0, 0, 0, 1, 1, 2}, {1, -1, 2, 1, -1, -2, 1, -1, 0}};                        
        int[] ratio = {1, 1, 2, 7, 7, 2, 10, 10, 5};
        // 북 동 남 서
        int d = dust[y][x]; // 해당 칸에 있는 먼지
        int restd = d;
        dust[y][x] = 0;
        for (int i = 0 ; i < 9 ; i++) {
            int nx = x + dsx[dir][i];
            int ny = y + dsy[dir][i];
            int calc = (d * ratio[i]) / 100;
            // 모래가 격자 밖으로 나갈 때
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                ans += calc; 
                restd -= calc;
                continue;
            }
            // 해당 칸으로 모래이동
            dust[ny][nx] += calc;
            restd -= calc;
        }
        // 알파 칸
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            ans+= restd;
        } else {
            dust[ny][nx] += restd;
        }
        

    }
    static void make_snail() {
        Queue<Move2> q = new LinkedList<>();
        q.offer(new Move2(0, 0, 1));
        map[0][0] = N * N;
        int load = N * N - 1;
        while (!q.isEmpty()) {
            Move2 d = q.poll();

            int nx = d.x + dx[d.dir];
            int ny = d.y + dy[d.dir];

            if (nx < 0 || nx >= N || ny < 0  || ny >= N  || map[ny][nx] > 0 ) {
                d.dir = (d.dir + 1) % 4;
                q.offer(d);
                continue;
            }

            d.x = nx;
            d.y = ny;
            q.offer(d);
            map[ny][nx] = load--;
            if (load == 0) break;
        }
    }
}
class Move2 {
    int x;
    int y;
    int dir;
    Move2(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}