import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14940 {
    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];
        Pos start = new Pos(-1, -1);
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2){
                    start = new Pos(j, i);
                    map[i][j] = 0;
                }else if (map[i][j] == 1){
                    map[i][j] = -1;
                }
            }
        }
        BFS(start.x, start.y);
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    static void BFS(int x, int y){
        Queue<Pos> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.offer(new Pos(x, y));

        while(!q.isEmpty()){
            Pos d = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= m || ny >= n || isVisited[ny][nx] || map[ny][nx] == 0) continue;

                q.offer(new Pos(nx, ny));
                map[ny][nx] = map[d.y][d.x] + 1;
                isVisited[ny][nx] = true;
            }
        }

    }
    
}
class Pos {
    int x;
    int y;
    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}