import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7562 {
    public static int[] dy = {1, 2, -2, -1, 1, 2, -1, -2};
    public static int[] dx = {2, 1, 1, 2, -2, -1, -2, -1};
    public static int N,x_Srt,x_End,y_End,y_Srt;
    public static int[][] map;
    public static boolean[][] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < t ; i++){
             N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            isVisited = new boolean[N][N];
            StringTokenizer st= new StringTokenizer(br.readLine());
            y_Srt = Integer.parseInt(st.nextToken());
            x_Srt = Integer.parseInt(st.nextToken());
            st= new StringTokenizer(br.readLine());
            y_End = Integer.parseInt(st.nextToken());
            x_End = Integer.parseInt(st.nextToken());


            BFS(x_Srt, y_Srt);
            sb.append(map[y_End][x_End]).append("\n");
        }
        System.out.print(sb.toString());
        
    }
    public static void BFS(int x, int y){
        Queue<Dot11> q = new LinkedList<>();
        q.offer(new Dot11(x, y));
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            Dot11 d = q.poll();
            for(int i = 0 ; i < dx.length ; i++){
                int X = d.x + dx[i];
                int Y = d.y + dy[i];
                if(X >= 0 && Y >= 0 && X < N && Y < N){
                    if(isVisited[Y][X] || map[Y][X] > 0) continue;
                    isVisited[Y][X] = true;
                    q.offer(new Dot11(X, Y));
                    map[Y][X] = map[d.y][d.x] +1;

                }
            }
        }

    }
}
class Dot11{
    int x;
    int y;
    Dot11(int x, int y){
        this.x = x;
        this.y = y;
    }
}