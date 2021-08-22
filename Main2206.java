import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2206 {
    static int result = Integer.MAX_VALUE;
    static int N, M;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] map;
    static int[][][] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new int[2][N][M];
        
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j)- '0';
                isVisited[0][i][j] = -1;
                isVisited[1][i][j] = -1;
            }
        }
        
        BFS(0, 0);
        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.print(result);
        }
        
        
    }
    static void BFS(int x, int y){
        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(x, y, 1 , 0));
        isVisited[0][y][x] = 1;
        
        while(!q.isEmpty()){
            Move m = q.poll();
            int skill = m.skill;

            if(m.x == M - 1 && m.y == N - 1){
                result = Math.min(result, m.dst);
                return;
            }

            for(int i = 0 ; i < dx.length ; i++){
                int X = m.x + dx[i];
                int Y = m.y + dy[i];
                if(X>= 0 && Y >= 0 && X < M && Y < N){
                    if(isVisited[skill][Y][X] == -1 && map[Y][X] == 0){
                        isVisited[skill][Y][X] = isVisited[skill][m.y][m.x] + 1;
                        q.offer(new Move(X, Y,m.dst + 1, skill));
                        
                    }
                    else if(skill == 0){
                        if(isVisited[skill][Y][X] == -1 && map[Y][X] == 1){
                            isVisited[skill][Y][X] = isVisited[0][m.y][m.x] + 1;
                            q.offer(new Move(X, Y, m.dst + 1,1));
                            
                        }
                    }
                    

                    
                }
            }
        }
      

    }

}
class Move{
    int x;
    int y;

    int dst;
    int skill;
    Move(int x,int y,int dst, int skill){
        this.x = x;
        this.y = y;
        this.dst = dst;
        this.skill = skill;
    }
}