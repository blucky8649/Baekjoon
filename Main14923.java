import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14923 {
    static int N, M;
    static int map[][];
    static boolean[][][] isVisited;

    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[2][N][M];

        st = new StringTokenizer(br.readLine(), " ");
        int Sy = Integer.parseInt(st.nextToken()) - 1;
        int Sx = Integer.parseInt(st.nextToken())- 1;
        
        st = new StringTokenizer(br.readLine(), " ");
        int Ey = Integer.parseInt(st.nextToken()) - 1;
        int Ex = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(BFS(Sx, Sy, Ex, Ey));
    }
    static int BFS(int x, int y, int ex, int ey) {
        Queue<D10039> q = new LinkedList<>();
        q.offer(new D10039(x, y, 0, 0));
        isVisited[0][y][x] = true;
        
        while (!q.isEmpty()) {
            D10039 d = q.poll();
            if (d.x == ex && d.y == ey) {
                return d.cnt;
            }
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < M && ny >= 0 && ny < N ) {
                    if (map[ny][nx] == 1 && !isVisited[d.skill][ny][nx] && d.skill == 0) {
                        q.offer(new D10039(nx, ny, d.cnt + 1, 1));
                        isVisited[1][ny][nx] = true;
                        continue;
                    }
                    if (map[ny][nx] == 0 && !isVisited[d.skill][ny][nx]) {
                        q.offer(new D10039(nx, ny, d.cnt + 1, d.skill));
                        isVisited[d.skill][ny][nx] = true;
                    }
                }
            }
        }
        return -1;
    }
}
class D10039 {
    int x;
    int y;
    int cnt;
    int skill;
    D10039(int x, int y, int cnt, int skill) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.skill = skill;
    }
}