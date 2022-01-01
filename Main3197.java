import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3197 {
    static int R, C;
    static char[][] water;
    static D3197 swans[];
    static boolean[][] isVisited;
    static Queue<D3197> waterQ = new LinkedList<>();
    static Queue<D3197> swanQ = new LinkedList<>();
    
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        water = new char[R][C];
        isVisited = new boolean[R][C];
        swans = new D3197[2]; // 두 마리의 백조
        int swan_idx = 0;
        for (int i = 0 ; i < R ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < C ; j++) {
                water[i][j] = str.charAt(j);
                if (water[i][j] == 'L') {
                    swans[swan_idx++] = new D3197(j, i);
                }

                if (water[i][j] != 'X') {
                    waterQ.offer(new D3197(j, i));
                }
            }
        }
        swanQ.offer(new D3197(swans[0].x, swans[0].y));
        int day = 0;

        while (true) {
            if (move_swan()) break; // 백조 이동
            melt_water();
            day++;
            
        }
        System.out.println(day);


    }
    static void melt_water() {
        int size = waterQ.size();
        for (int idx = 0 ; idx < size ; idx++) {
            D3197 d = waterQ.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < C && ny >= 0 && ny < R) {
                    if (water[ny][nx] == 'X') {
                        water[ny][nx] = '.';
                        waterQ.offer(new D3197(nx, ny));
                    }
                }
            }
        }
    }
    static boolean move_swan() {
        Queue<D3197> tomorrowQ = new LinkedList<>(); // 다음 날 Queue
        while (!swanQ.isEmpty()) {
            D3197 d = swanQ.poll();

            if (d.y == swans[1].y && d.x == swans[1].x) {
                return true;
            }

            if (isVisited[d.y][d.x]) continue;
            isVisited[d.y][d.x] = true;

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < C && ny >= 0 && ny < R && !isVisited[ny][nx]) {
                    if (water[ny][nx] == 'X') {
                        tomorrowQ.offer(new D3197(nx, ny));
                        continue;
                    }
                    swanQ.offer(new D3197(nx, ny));
                }
            }
        }
        swanQ = tomorrowQ;
        return false;
    }
}
class D3197 {
    int x;
    int y;
    D3197(int x, int y) {
        this.x = x;
        this.y = y;
    }
}