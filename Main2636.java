import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2636 {
    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;
    static int cheese = 0;

    static int[] dy = {-1, 0, 1, 0}; // 탐색 방향 : 북동남서
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); // Y
        m = Integer.parseInt(st.nextToken()); // X
        map = new int[n][m];
        Cheese start = new Cheese(1, 1); // 첫 시작 공기 좌표
        boolean desg = false; // 첫 시작 공기 좌표 지정 여부 
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    cheese ++;
                }
                if(!desg && (i == 0 || i == n - 1) && map[i][j] == 0){
                    desg = true;
                    start = new Cheese(j, i);
                }else if (!desg && (j == 0 || j == m - 1) && map[i][j] == 0){
                    desg = true;
                    start = new Cheese(j, i);
                }
            }
        }

        int ansTime = 0;
        int ansCnt = 0;
        while(cheese != 0){
            ansTime ++;
            ansCnt = cheese;
            BFS(start.x, start.y);
        }
        System.out.println(ansTime);
        System.out.println(ansCnt);

    }
    static void BFS(int x, int y){
        Queue<Cheese> q = new LinkedList<>();
        isVisited = new boolean[n][m];
        q.offer(new Cheese(x, y));
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            Cheese d = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || isVisited[ny][nx]) continue;

                //겉 치즈 만나면 녹임
                if(map[ny][nx] == 1){
                    cheese --;
                    map[ny][nx] = 0;
                }else{
                    q.offer(new Cheese(nx, ny));
                }

                isVisited[ny][nx] = true; // 치즈가 안쪽까지 녹지 않게 방문처리
            }

        }
    }
    
}

class Cheese{
    int x;
    int y;
    Cheese(int x, int y){
        this.x = x;
        this.y = y;
    }
}