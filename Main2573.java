import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2573 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N,M;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        int ans = 0;
        while((cnt = cntIsland()) < 2){
            if(cnt == 0){
                System.out.println(0);
                return;
            }
            BFS();
            ans ++;
        }
        System.out.println(ans);

        
    }
    static int cntIsland(){
        boolean[][] isVisited = new boolean[N][M];
        int cnt = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(!isVisited[i][j] && map[i][j] != 0){
                    DFS(j, i, isVisited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void DFS(int x, int y, boolean[][] isVisited){
        isVisited[y][x] = true;

        for(int i = 0 ; i < 4 ; i++){
            int X = x + dx[i];
            int Y = y + dy[i];
            if(X >= 0 && Y >= 0 && X<M && Y<N){
                if(isVisited[Y][X] || map[Y][X] == 0) continue;
                DFS(X, Y, isVisited);
            }
        }
    }

    static void BFS(){
        Queue<Ice> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[N][M];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] != 0){
                    isVisited[i][j] = true;
                    q.offer(new Ice(j, i));
                }
            }
        }

        while(!q.isEmpty()){
            Ice d = q.poll();
            int zeroSide = 0;
            for(int i = 0 ; i < 4 ; i++){
                int X = d.x + dx[i];
                int Y = d.y + dy[i];

                if(!(X>= 0 && Y>= 0 && X<M && Y<N))continue;
                if(isVisited[Y][X] || map[Y][X] != 0) continue;
                zeroSide++;

            }

            if(map[d.y][d.x] - zeroSide < 0){
                map[d.y][d.x] = 0;
            }else{
                map[d.y][d.x] -= zeroSide;
            }
        }
    }
}
class Ice{
    int x;
    int y;
    Ice(int x, int y){
        this.x = x;
        this.y = y;
    }
}