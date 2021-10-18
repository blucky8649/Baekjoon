import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2411 {
    static int n, m, item, x;
    static int[][] map;
    static boolean[][][] isVisited;

    // 이동 방향
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        item = Integer.parseInt(st.nextToken()); // 아이템
        x = Integer.parseInt(st.nextToken());// 장애물

        map = new int[n][m];
        isVisited = new boolean [n][m][0]; //[y][x][먹은 아이템 수]


        for (int i = 0 ; i < item ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) -1;
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 1;
        }

        for (int i = 0 ; i < x ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) -1;
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 9;
        }

        for (int i = 0 ; i < n ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static int BFS(int x, int y){
        Queue<Player> q = new LinkedList<>();
        isVisited[y][x][0] = true;
        q.offer(new Player(x, y, 0, 0));

        while (!q.isEmpty()) {
            Player d = q.poll();

            if (map[d.y][d.x] == 1) {
                d.status ++;
            }

            if (d.status == item) {
                return d.cnt;
            }
            

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.x + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny <= n && isVisited[y][x][d.status] && map[ny][nx] != 9) {
                    isVisited[y][x][d.status] = true;
                    q.offer(new Player(nx, ny, d.cnt + 1, d.status));
                }
            }
        }
        return 0;
    }
}

class Player {
    int x;
    int y;
    int cnt;
    int status;
    Player(int x, int y, int cnt, int status) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.status = status;
    }
}

class Item {
    int x;
    int y;
    Item(int x, int y) {
        this.x = x;
        this.y = y;
    }
}