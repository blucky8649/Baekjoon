import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2933 {
    static int R, C, sum = 0, max_y;
    static char map[][];
    static boolean isVisited[][];
    static ArrayList<cluster> arr;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        isVisited = new boolean[R][C];
        sum = 0;
        for (int i = 0 ; i < R ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < C ; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'x') {
                    sum ++;
                }
            }
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int idx = 0 ; idx < N ; idx++) {
            int height = R - Integer.parseInt(st.nextToken());
            // 1. 막대기를 던진다.
            throw_stick(idx, height);
            // 2. 공중에 떠 있는 클러스터가 있는지 확인한다.
            isVisited = new boolean[R][C];
            check_cluster();
        }
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }
    static void print_matrix() {
        for (int i = 0 ; i < R ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static void gravity() {
        for (cluster d : arr) {
            map[d.y][d.x] = '.';
        }
        int move = 1;
        outerLoop:
        while (true) {
            for (cluster d : arr) {
                if (d.y + move == R || map[d.y + move][d.x] == 'x') {
                    move--;
                    break outerLoop;
                }
            }
            move ++;
        }
        for (cluster d : arr) {
            map[d.y + move][d.x] = 'x'; //클러스터 재배치
        }
    }
    static void search_cluster(int x, int y) {
        max_y = y;
        arr = new ArrayList<cluster>();
        Queue<cluster> q = new LinkedList<>();
        q.offer(new cluster(x, y));
        isVisited[y][x] = true;
        while (!q.isEmpty()) {
            cluster d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >=0 && nx < C && ny >= 0 && ny < R && !isVisited[ny][nx] && map[ny][nx] == 'x') {
                    q.offer(new cluster(nx, ny));
                    isVisited[ny][nx] = true;
                    max_y = Math.max(max_y, ny);
                }
            }
            arr.add(d);
        }
    }
    static void check_cluster() {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                if (!isVisited[i][j] && map[i][j] == 'x') {
                    search_cluster(j, i);
                    if (max_y < R - 1) {
                        // 중력으로 끌어당긴다.
                        gravity();
                        return;
                    } 
                    
                }
            }
        }
    }
    static void throw_stick(int idx, int height) {
        switch(idx % 2) {
            case 0 :
                for (int i = 0 ; i < C ; i++) {
                    if (map[height][i] == 'x') {
                        map[height][i] = '.';
                        sum --;
                        return;
                    }
                } 
                break;
            case 1 : 
                for (int i = C - 1 ; i >= 0 ; i--) {
                    if (map[height][i] == 'x') {
                        map[height][i] = '.';
                        sum --;
                        return;
                    }
                } 
                break;
        }
    }
}
class cluster {
    int x;
    int y;
    cluster(int x, int y){
        this.x = x;
        this.y = y;
    }
}