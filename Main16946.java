import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class WallWall {
    int x;
    int y;
    WallWall(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main16946 {
    static int R, C;
    static int[][] map;
    static int[][] count;
    static int[][] groupset;
    static boolean[][] isVisited;
    static Queue<WallWall> wall = new LinkedList<>();

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        count = new int[R][C];
        groupset = new int[R][C];
        for (int i = 0 ; i < R ; i++) {
            String str = br.readLine();

            for (int j = 0 ; j < C ; j++) {
                if (str.charAt(j) -'0'== 0 ) continue;
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 1) {
                    wall.offer(new WallWall(j, i));
                }
            }
        }
        int group = 1;
        isVisited = new boolean[R][C];
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (!isVisited[i][j] && map[i][j] == 0) {
                    BFS(j, i, group++);
                }
            }
        }
        while (!wall.isEmpty()) {
            String group2 = "";
            WallWall d = wall.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < C && ny < R && count[ny][nx] > 0 && !group2.contains(String.valueOf(groupset[ny][nx]))) {
                    group2 += groupset[ny][nx];
                    map[d.y][d.x] += count[ny][nx];
                    map[d.y][d.x] %= 10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        
    }
    static void BFS(int x, int y, int group) {
        Queue<WallWall> q = new LinkedList<>();
        ArrayList<WallWall> arr = new ArrayList<>();
        isVisited[y][x] = true;
        q.offer(new WallWall(x, y));
        int cnt = 1;
        groupset[y][x] = group;
        while (!q.isEmpty()) {
            WallWall d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < C && ny < R && !isVisited[ny][nx] && map[ny][nx] == 0) {
                    cnt ++;
                    groupset[ny][nx] = group;
                    isVisited[ny][nx] = true;
                    arr.add(new WallWall(nx, ny));
                    q.offer(new WallWall(nx, ny));
                }
            }
        }
        count[y][x] = cnt;
        for (WallWall d : arr) {
            count[d.y][d.x] = cnt;
        }
    }
}
