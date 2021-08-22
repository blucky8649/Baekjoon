import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4963 {
    public static int w,h;
    public static int cnt = 0;
    public static int[][] map;
    public static boolean[][] isVisited;
    public static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    public static int[] dx = {0, 0, -1, 1, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            cnt = 0;
            if(w == 0 && h == 0) break;

            map = new int[h][w];
            isVisited = new boolean[h][w];
            for(int i = 0 ; i < h ; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0 ; j < w ; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int y = 0 ; y < h ; y++){
                for(int x = 0 ; x < w ; x++){
                    if(isVisited[y][x] || map[y][x] == 0) continue;
                    System.out.println("좌표 : " + y + " , " + x);
                    BFS(x, y);
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
    public static void BFS(int x, int y) {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(x, y));
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            Dot d = q.poll();
            for(int i = 0 ; i < 8 ; i++){
                int X = d.x + dx[i];
                int Y = d.y + dy[i];
                if(X >= 0 && Y >= 0 && X < w && Y < h){
                    if(isVisited[Y][X] || map[Y][X] == 0) continue;
                    isVisited[Y][X] = true;
                    q.add(new Dot(X, Y));
                }
            }
            

        }
        
        
    }
    public static void DFS(int x, int y){
        isVisited[y][x] = true;

        for(int i = 0 ; i < dx.length ; i++){
            int X = x + dx[i];
            int Y = y + dy[i];

            if(X >= 0 && Y >= 0 && X < w && Y < h){
                if(isVisited[Y][X] || map[Y][X] == 0) continue;
                DFS(X, Y)
                ;
            }
        }
    }


}
class Dot{
    int x;
    int y;
    Dot(int x, int y){
        this.x = x;
        this.y = y;
    }
}
