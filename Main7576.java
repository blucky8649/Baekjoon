import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7576 {
    static int result=Integer.MIN_VALUE;
    static int M, N;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<Tomato> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         M = Integer.parseInt(st.nextToken());
         N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j  = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if(map[i][j] == 1){
                    q.offer(new Tomato(j, i));
                    isVisited[i][j] = true;
                }
            }
        }

        System.out.println(BFS());
       
    }
    static int BFS(){
        while(!q.isEmpty()){
            Tomato t = q.poll();

            for(int i = 0 ; i < dx.length ; i++){
                int X = t.x + dx[i];
                int Y = t.y + dy[i];

                if(X >= 0 && Y >= 0 && X < M && Y < N){
                    
                    if(map[Y][X] == 0 && !isVisited[Y][X]){
                        
                        map[Y][X] = map[t.y][t.x] + 1;
                        q.offer(new Tomato(X, Y));
                        isVisited[Y][X] = true;
                    }
                }
            }
            
           
        }
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 0){
                    return -1;
                }
                    result = Math.max(result, map[i][j]);
                    
                
                

            }
        }
        if(result == 1){
            return 0;
        } 
        else{
            return result -1;
        }
    }
}
class Tomato{
    int x;
    int y;
    Tomato(int x, int y){
        this.x = x;
        this.y = y;
    }
}