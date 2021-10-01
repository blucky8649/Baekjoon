import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1600 {
    static int K, M, N, map[][];
    static boolean[][][] isVisited;
    // 일반 이동
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    
    // 특수 이동
    static int[] ddy = {-2, -1, -2, -1, 2, 1, 2, 1};
    static int[] ddx = {-1, -2, 1, 2, -1, -2, 1, 2};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[31][N][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(BFS(0, 0));
        
    }
    static int BFS(int x, int y){
        Queue<Monkey> q = new LinkedList<>();
        q.offer(new Monkey(x, y, 0, 0));
        isVisited[0][y][x] = true;

        while(!q.isEmpty()){
            Monkey d = q.poll();

            if(d.x == M - 1 && d.y == N - 1){
                return d.cnt;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N || isVisited[d.skill][ny][nx] || map[ny][nx] == 1) continue;

                q.offer(new Monkey(nx, ny, d.skill, d.cnt + 1));
                isVisited[d.skill][ny][nx] = true;
            }

            if(d.skill < K){
                for(int i = 0 ; i < 8 ; i++){
                    int nx = d.x + ddx[i];
                    int ny = d.y + ddy[i];
                    int nk = d.skill + 1;
                    if(nx < 0 || ny < 0 || nx >= M || ny >= N || isVisited[nk][ny][nx] || map[ny][nx] == 1) continue;
                    q.offer(new Monkey(nx, ny, d.skill + 1, d.cnt +1));
                    isVisited[nk][ny][nx] = true;
                }
            }

            
        }
        return -1;
    }
}
class Monkey {
    int x;
    int y;
    int skill;
    int cnt;
    Monkey(int x, int y, int skill, int cnt){
        this.x =x;
        this.y =y;
        this.skill = skill;
        this.cnt = cnt;
    }
}