import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2606 {
    public static int N, M;
    public static int cnt = 0;
    public static int[][] map;
    public static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         N= Integer.parseInt(br.readLine());
         M = Integer.parseInt(br.readLine());
         map = new int[N+1][N+1];
         isVisited = new boolean[N+1];

        for(int i = 1 ; i <= M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = map[x][y] = 1;
            
        }
            DFS(1);
            System.out.println(cnt);
              
    }
    public static void DFS(int k){
        
        isVisited[k] = true;
        
         
        for(int i = 1 ; i <=N ; i++){
            if(isVisited[i] || map[k][i] == 0) continue;
            cnt++;
            DFS(i);
        }
    }
    
}
