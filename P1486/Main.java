package P1486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T, D;
    static int[][] map;
    static boolean[][][] isVisited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0 ; i < N ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
                    map[i][j] -= 'A';
                } else {
                    map[i][j] = map[i][j] - 'a' + 26;
                }
                
            }
        }
        for (int i = 0 ; i < N ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println(BFS());
    }
    static int BFS() {
        int ans = 0;
        isVisited = new boolean[N][M][4];
        Queue<Sejun> q = new LinkedList<>();
        isVisited[0][0][0] = true;
        isVisited[0][0][1] = true;
        isVisited[0][0][2] = true;
        isVisited[0][0][3] = true;
        q.offer(new Sejun(0, 0, 0));

        while (!q.isEmpty()) {
            Sejun d = q.poll();
            ans = Math.max(ans, map[d.y][d.x]);
            for (int i = 0 ; i < 4; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                int time = map[d.y][d.x] >= map[ny][nx] ? 1 : (int)Math.pow(map[d.y][d.x] - map[ny][nx], 2);
                if (!isVisited[ny][nx][i] && d.cnt + time <= D && Math.abs(map[ny][nx] - map[d.y][d.x]) <= T) {
                    isVisited[ny][nx][i] = true;
                    q.offer(new Sejun(nx, ny, d.cnt + time));
                    System.out.println(nx + " " + ny + " " + (d.cnt + time) + " " + map[ny][nx]);
                }
            }
        }
        return ans;
    }
}
class Sejun {
    int x;
    int y;
    int cnt;
    Sejun(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

}