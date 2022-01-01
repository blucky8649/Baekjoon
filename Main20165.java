import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main20165 {
    static int N, M, R, score = 0, m = 0;
    static int[][] board;
    static char[][] result;
    static int[] dy = {0, 0, 1, -1}; // E, W, S, N
    static int[] dx = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        result = new char[N][M];
        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(result[i], 'S');
        }
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < M ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < R ; i++) {
            m = 1;
            st = new StringTokenizer(br.readLine(), " ");
            int ay = Integer.parseInt(st.nextToken()) - 1; // 공격 y좌표
            int ax = Integer.parseInt(st.nextToken()) - 1;// 공격 x좌표
            String acmd = st.nextToken(); // 공격 방향
            attak(ax, ay, acmd);
            st = new StringTokenizer(br.readLine(), " ");
            int dy = Integer.parseInt(st.nextToken()) - 1; // 방어 y좌표
            int dx = Integer.parseInt(st.nextToken()) - 1; // 방어 x좌표
            result[dy][dx] = 'S';
            score += m;
        }
        System.out.println(score );
        print_result();
    }
    static void print_result() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void attak(int x, int y, String cmd) {
        int dir = -1;
        switch (cmd) {
            case "E" : dir = 0; break;
            case "W" : dir = 1; break;
            case "S" : dir = 2; break;
            case "N" : dir = 3; break;
        }

        go(x, y, dir, 1);
        x = x + dx[dir];
        y = y + dy[dir];



    }
    static void go(int x, int y, int dir, int s) {
        int num = board[y][x];
        result[y][x] = 'F';
        for (int i = 1 ; i < num ; i++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (nx >= 0 && ny >= 0 && nx < M && ny < N  ) {
                if (result[ny][nx] == 'S') {
                    m++;
                    go(nx, ny, dir, s + 1);
                }
                result[ny][nx] = 'F';
                y = ny;
                x = nx;
            } else {
                break;
            }
            
        }
    } 
}
