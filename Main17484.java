import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17484 {
    static int N, M, res = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dy = {1, 1, 1}; //↙ ↓ ↘
    static int[] dx = {-1, 0, 1};
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
        for(int i = 0 ; i < M ; i++){
            DFS(i, 0, 0, i, -1);
        }

        System.out.println(res);

    }
    static void DFS(int x, int depth, int sum, int start, int skill){
        isVisited[depth][x] = true;

        if(depth == N-1){
            res = Math.min(res, sum + map[0][start]);
            //System.out.println(sum);
            return;
        }
        for(int i = 0 ; i < 3 ; i++){
            int nx = x + dx[i];
            if(nx >= 0 && nx < M && !isVisited[depth+1][nx] && i != skill){
                isVisited[depth+1][nx] = true;
                //System.out.println( "("+depth + "," + x +")" + "에서" + nx + "로 " + sum);
                DFS(nx, depth + 1, sum + map[depth+1][nx], start, i);
                isVisited[depth+1][nx] = false;
            }
            
        }
    }
}
