import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

import java.util.StringTokenizer;

public class Main1261 {
    static int result = Integer.MAX_VALUE;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        isVisited = new boolean[M][N];
        for(int i = 0 ; i < M ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        BFS(0, 0);
        System.out.println(result);
    }
    static void BFS(int x, int y){
        Deque<Spot> q = new LinkedList<>();
        isVisited[x][y] = true;
        q.addLast(new Spot(x, y, 0));

        while(!q.isEmpty()){
            Spot d = q.pollLast();
            int Z = d.drill;
           
            if(d.x == N - 1 && d.y == M - 1){
                
                result = Math.min(result, d.drill);
              
                
            }
            for(int i = 0 ; i < 4 ; i++){
                int X = d.x + dx[i];
                int Y = d.y + dy[i];
                if(X >= 0 && Y >= 0 && X < N && Y < M && Z < M*N - 1) {
                    if(!isVisited[Y][X] && map[Y][X]==0){
                        q.addLast(new Spot(X, Y, Z));
                        isVisited[Y][X] = true;
                        
                    }
                    else if(!isVisited[Y][X] && map[Y][X]==1){
                        q.addFirst(new Spot(X, Y, Z + 1));
                        isVisited[Y][X] = true;
                    }
                }
            }
        }

    }
}
class Spot{
    int x;
    int y;
    int drill;
    Spot(int x, int y, int drill){
        this.x = x;
        this.y = y;
        this.drill = drill;
    }
}
