import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1012 {
    public static int cnt = 0;
    public static int N,M,K;
    public static int[][] map;
    public static boolean[][] isVisited;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0 ; i < n ; i++){
            cnt = 0;
            st = new StringTokenizer(br.readLine(), " ");
             M = Integer.parseInt(st.nextToken());
             N = Integer.parseInt(st.nextToken());
             K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            isVisited = new boolean[N][M];            
            for(int j = 0 ; j < K ; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = 1;
            }

            for(int y = 0 ; y < N ; y++){
                for(int x = 0 ; x < M ; x++){
                    if(!isVisited[y][x] && map[y][x] == 1){
                        System.out.println("좌표 : " + y + " , " + x);
                        BFS(x, y);
                        cnt++;
                        
                    }
                    
                    
                }
            }
            System.out.println(cnt);
        }


        
    }
    public static void BFS(int x, int y){
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{y, x});
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            int curY = q.peek()[0];
            int curX = q.peek()[1];
            q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int X = curX + dx[i];
                int Y = curY + dy[i];
                if(X >= 0 && Y >= 0 && X < M && Y < N){
                    if(isVisited[Y][X] || map[Y][X] == 0) continue;
                    q.add(new int[] {Y, X});
                    isVisited[Y][X] = true;
                }
            }
        }
    }

    public static void DFS(int x, int y){
        isVisited[y][x] = true;

        for(int i = 0 ; i < 4 ; i++){
            int X = x + dx[i];
            int Y = y + dy[i];
            if(X >= 0 && Y >=0 && X < M && Y < N){
                if(isVisited[Y][X] || map[Y][X] == 0) continue;
                DFS(X, Y);
            }
        }

    }
    
}
