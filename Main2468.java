import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2468 {

    static int[] dy= {-1, 1, 0, 0};
    static int[] dx= {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = Integer.MIN_VALUE;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int max = Integer.MIN_VALUE;

        for(int i =0 ; i < N ; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        for(int h = 0 ; h< max; h++){
            isVisited = new boolean[N][N];
            int waterpark = 0;
            for(int i =0 ; i < N ; i ++){
                for(int j = 0 ; j < N ; j++){
                    if(!isVisited[i][j] && map[i][j] > h){
                        DFS(j, i, h);
                        waterpark ++;
                    }
                           
                }
            }
            
            result = Math.max(result, waterpark);
        }
        
        System.out.print(result);
    }
    static void DFS(int x,int y, int rain){
        isVisited[y][x] = true;

        for(int i = 0 ; i < dx.length ; i++){
            int X = x + dx[i];
            int Y = y + dy[i];

            if(X>= 0 && Y>= 0 && X< N && Y<N){
                if(isVisited[Y][X] || map[Y][X] <= rain) continue;
                isVisited[Y][X] = true;
                DFS(X, Y, rain);

            }
        }


    }
}
