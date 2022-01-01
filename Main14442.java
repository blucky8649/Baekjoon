import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14442 {
    static int N, M, K;
    static char[][] map;
    static boolean[][][] isVisited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        isVisited = new boolean[K + 1][N][M];
        for (int i = 0 ; i < N ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(BFS(0, 0));
    }
    static int BFS(int x, int y) {
        Queue<D14442> q = new LinkedList<>();
        isVisited[0][y][x] = true;
        q.offer(new D14442(x, y, 0, 0));

        while (!q.isEmpty()) {
            D14442 d = q.poll();
            if (d.x == M - 1 && d.y == N - 1) {
                return d.cnt + 1;
            }
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !isVisited[d.skill][ny][nx]) {
                    if (map[ny][nx] == '1') {
                        if (d.skill < K && !isVisited[d.skill + 1][ny][nx]) {
                            isVisited[d.skill + 1][ny][nx] = true;
                            q.offer(new D14442(nx, ny, d.cnt + 1, d.skill + 1));
                        } 
                    } else {
                        isVisited[d.skill][ny][nx] = true;
                        q.offer(new D14442(nx, ny, d.cnt + 1, d.skill));
                    }
                }
            }
        }
        return -1;
    }
}
class D14442 {
    int x;
    int y;
    int cnt;
    int skill;
    D14442 (int x, int y, int cnt, int skill) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.skill = skill;
    }
}