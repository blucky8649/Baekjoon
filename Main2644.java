import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2644 {
    static int result= -1;
    static int n, a, b; 
    static boolean[] isVisited;
    static int[][] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br. readLine());
        graph  = new int[n + 1][n + 1];
        isVisited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        int r = Integer.parseInt(br.readLine());

        for(int i = 1 ; i <= r ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = graph[y][x] = 1;
        }   
        
       
        DFS(a, -1, 0);
            System.out.println(result);
        
        
    }
    static void DFS(int start, int before, int cnt){
        if(start == b){
            result = cnt;
            
       }
        isVisited[start] = true;

        for(int i = 1 ; i <= n ; i++){
            if(graph[start][i] == 0) continue;
            if(i == before) continue;
            DFS(i, start, cnt + 1);

        }
        
    }
}
