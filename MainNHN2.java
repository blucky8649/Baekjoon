import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class NHN {
    int x; 
    int y;
    int dir; // CCTV 감시 방향 정보
    int cnt;
    NHN(int x, int y, int dir, int cnt){
        this.x =x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }

}
public class MainNHN2 {
    static int n;
    static int[][] map;
    static boolean[][] isVisited;
    
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        isVisited = new boolean[n][n];

        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move(0, 0));

    }
    static int move(int x, int y){
        Queue<NHN> q = new LinkedList<>();
        q.offer(new NHN(x, y, 1, 0));
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            NHN d= q.poll();
            if(d.x == n - 1 && d.y == n - 1){
                return d.cnt;
            }
            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(!movable(d.x, d.y, d.dir)){
                    q.offer(new NHN(d.x, d.y, d.dir % 4 + 1, d.cnt + 1)); // 가만히 서 있기.
                    System.out.println(d.x + " " + d.y + " cnt : " + d.cnt + " dir :" + d.dir);
                    continue;
                }
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && !isVisited[ny][nx] && map[ny][nx] != 1){
                    q.offer(new NHN(nx, ny, d.dir % 4 + 1, d.cnt + 1));
                    isVisited[ny][nx] = true;
                }
            }
        }
        return -1;
    }
    
    static boolean movable(int x, int y, int dir) {
        int area = n / 2; //범위

        //1 : 좌측 상단, 2: 우측 상단, 3 : 우측 하단, 4 : 좌측 하단
        // 아래 4가지 조건에 위배되면 이동 "불가"
        switch(dir){
            case 1: if (x >= 0 && x < area && y >= 0 && y < area) return false;
            break;    
            case 2: if (x >= n - area && x < n && y >= 0 && y < area) return false;
            break;
            case 3: if (x >= n - area && x < n && y >= n - area && y < n) return false;
            break;
            case 4: if (x >= 0 && x < area && y >= n - area && y < n)  return false;
            break;
        }
        return true;
    }
}
