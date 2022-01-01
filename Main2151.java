import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main2151 {
    static int N;
    static char[][] map;
    static boolean[][][] isVisited;
    static Mirror[] door = new Mirror[2];
    static boolean[] check;
    static ArrayList<Mirror> mirror = new ArrayList<>();
    static ArrayList<Mirror> selected_mirror = new ArrayList<>();
    
    // 대각선 이동만 가능
    static int dy[] = {-1, 0, 1, 0}; // 북동남서 
    static int dx[] = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int door_idx = 0;
        for (int i = 0 ; i < N ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < N ; j++) {
                char cr = str.charAt(j);
                map[i][j] = cr;
                if (map[i][j] == '#') {
                    door[door_idx++] = new Mirror(j, i, 0, 0);
                }
                
            }
        }
        check = new boolean[mirror.size()];
        go();

        
    }
    static boolean go() {
        PriorityQueue<Mirror> q = new PriorityQueue<>();
        isVisited = new boolean[4][N][N];
        isVisited[0][door[0].y][door[0].x] = true;
        isVisited[1][door[0].y][door[0].x] = true;
        isVisited[2][door[0].y][door[0].x] = true;
        isVisited[3][door[0].y][door[0].x] = true;
        q.offer(new Mirror(door[0].x, door[0].y, 0, 0));
        q.offer(new Mirror(door[0].x, door[0].y, 1, 0));
        q.offer(new Mirror(door[0].x, door[0].y, 2, 0));
        q.offer(new Mirror(door[0].x, door[0].y, 3, 0));

        while (!q.isEmpty()) {
            Mirror d = q.poll();
            if (d.x == door[1].x && d.y == door[1].y) {
                System.out.println(d.cnt);
                return true;
            }

            int nx = d.x + dx[d.dir];
            int ny = d.y + dy[d.dir];
            int ndir = d.dir;
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !isVisited[d.dir][ny][nx] && map[ny][nx] != '*') {
                if (map[ny][nx] == '!') {
                    q.offer(new Mirror(nx, ny, (d.dir + 1) % 4, d.cnt + 1));
                    q.offer(new Mirror(nx, ny, (d.dir + 3) % 4, d.cnt + 1));
                } 
                 
                isVisited[d.dir][ny][nx] = true;
                q.offer(new Mirror(nx, ny, ndir, d.cnt));
                
                
            }
        }

        return false;
    }

    static int dir_converter (int dir) {
        switch (dir) {
            // ↙ ↗ ↘ ↖
            case 0 : return 2;
            case 1 : return 3;
            case 2 : return 1;
            case 3 : return 0;
            default : return -1;
        }
    }
}
class Mirror implements Comparable<Mirror>{
    int x;
    int y;
    int dir;
    int cnt;
    Mirror(int x, int y, int dir, int cnt) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(Mirror o) {
        return this.cnt - o.cnt;
    }
}