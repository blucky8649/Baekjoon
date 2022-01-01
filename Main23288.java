import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main23288 {

    static int n, m, k, ans = 0;
    static int[][] map;
    static boolean[][] isVisited;
    static int[][] dice = {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}}; // 초기의 주사위
    static int dice_dir = 1; // 초기 이동방향은 동쪽이다.
    static Dice pos = new Dice(0, 0); // 초기 Dice 위치는 (0, 0)
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0 ; i < k ; i++) {
            // 1. 처음에 이동방향으로 한 칸 굴러감
            go();
            // 2. 주사위가 도착한 칸(x, y)에 대한 점수를 획득
            ans += get_score();
            // 3. 주사위에 있는 아랫면에 따라 이동방향을 결정
            designate_dir();
        }
        System.out.println(ans);
    }
    static void designate_dir() {
        if (map[pos.y][pos.x] < dice[3][1]) {
            // 주사위에 있는 수가 더 크면 90도 시계방향으로 회전시킴
            dice_dir = (dice_dir + 1) % 4;
        } else if (map[pos.y][pos.x] > dice[3][1]) {
            dice_dir = (dice_dir + 3) % 4;
        } 
    }
    static int get_score() {
        int cnt = 1;
        isVisited = new boolean[n][m];
        Queue<Dice> q = new LinkedList<>();
        q.offer(new Dice(pos.x, pos.y));
        isVisited[pos.y][pos.x] = true;
        while (!q.isEmpty()) {
            Dice d = q.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !isVisited[ny][nx] && map[ny][nx] == map[pos.y][pos.x]) {
                    isVisited[ny][nx] = true;
                    q.offer(new Dice(nx, ny));
                    cnt++;
                }
            }
        }
        cnt *= map[pos.y][pos.x];
        return cnt;
    }
    static int change_dir(int dir) {
        switch (dir) {
            case 0 : return 2;
            case 1 : return 3;
            case 2 : return 0;
            case 3 : return 1;
        }
        return -1;
    }
    static void go() {
        int[][] tmp = new int[4][3];
        int nx = pos.x + dx[dice_dir];
        int ny = pos.y + dy[dice_dir];
        if (!(nx >= 0 && nx < m && ny >= 0 && ny < n)) {
            dice_dir = change_dir(dice_dir);
            nx = pos.x + dx[dice_dir];
            ny = pos.y + dy[dice_dir];
        }
        pos.x = nx;
        pos.y = ny;
        switch (dice_dir) {
            case 0 :
                tmp[1][1] = dice[2][1];
                tmp[0][1] = dice[1][1];
                tmp[2][1] = dice[3][1];
                tmp[3][1] = dice[0][1];
                // 그대로
                tmp[1][0] = dice[1][0];
                tmp[1][2] = dice[1][2];
                break;
            case 1 :
                // 동쪽 
                tmp[1][1] = dice[1][0];
                tmp[1][0] = dice[3][1];
                tmp[3][1] = dice[1][2];
                tmp[1][2] = dice[1][1];

                tmp[0][1] = dice[0][1];
                tmp[2][1] = dice[2][1];
                break;
            case 2 : 
                // 남쪽
                tmp[0][1] = dice[3][1];
                tmp[3][1] = dice[2][1];
                tmp[2][1] = dice[1][1];
                tmp[1][1] = dice[0][1];

                tmp[1][0] = dice[1][0];
                tmp[1][2] = dice[1][2];
                break;
            case 3 : 
                // 서쪽
                tmp[1][0] = dice[1][1];
                tmp[1][1] = dice[1][2];
                tmp[1][2] = dice[3][1];
                tmp[3][1] = dice[1][0];

                tmp[0][1] = dice[0][1];
                tmp[2][1] = dice[2][1];
                break;
        }
        for (int i = 0 ; i < dice.length ; i++) {
            dice[i] = tmp[i].clone();
        }
    }
}
class Dice {
    int x;
    int y;
    Dice(int x, int y) {
        this.x = x;
        this.y = y;
    }
}