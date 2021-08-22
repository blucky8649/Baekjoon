import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2178 {
    public static int n, m;
    public static int[][] map;
    public static boolean[][] isVisited;
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];
        
        
        for(int i = 0 ; i < n ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < m ; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        isVisited[0][0] = true;
        BFS(0, 0);
        System.out.println(map[n-1][m-1]);
    }
    public static void BFS(int x, int y){
        Queue<Dot1> q = new LinkedList<>();
        q.add(new Dot1(x, y));
        

        while(!q.isEmpty()){
            Dot1 d = q.poll();

            for(int i = 0 ; i < dx.length ; i++){
                int X = d.x + dx[i];
                int Y = d.y + dy[i];

                if(X >= 0 && Y >= 0 && X < m && Y < n ){
                    if(isVisited[Y][X] || map[Y][X] == 0) continue;
                    q.add(new Dot1(X, Y));
                    map[Y][X] = map[d.y][d.x] + 1;
                    isVisited[Y][X] = true;
                }
            }
        }
        
    }
}
class Dot1{
    int x;
    int y;
    Dot1(int x, int y){
        this.x = x;
        this.y = y;
    }
}
