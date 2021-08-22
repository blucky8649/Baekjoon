import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1260 {
    public static boolean[] isVisited;
    public static int[][] graph;
    public static int N,M,V;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
         V = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        isVisited = new boolean[N + 1];
        for(int i = 1 ; i <= M ; i++){
            st= new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            graph[y][x] = graph[x][y] = 1;

        }
        DFS(V);
        isVisited = new boolean [N +1];
        System.out.println(sb.toString());
        sb = new StringBuilder();
        BFS(V);
        System.out.println(sb.toString());

    }
    public static void DFS(int k){
        isVisited[k] =true;
        sb.append(k + " ");

        for(int i = 1 ; i <= N ; i ++ ){
            if(isVisited[i] || graph[k][i] == 0) continue;
            DFS(i);

        }
        
    }

    public static void BFS(int k){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        isVisited[k] = true;

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            sb.append(tmp + " ");
            
            for(int i = 1 ; i <= N ; i++){
                if(graph[tmp][i] == 0 || isVisited[i]) continue;
                queue.offer(i);
                isVisited[i] = true;
            }
        }

    }
}
