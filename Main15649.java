import java.io.*;
import java.util.StringTokenizer;



public class Main15649 {
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[] arr;
    public static boolean[] isVisited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[10];
        isVisited = new boolean[10];
        DFS(0);
        System.out.print(sb.toString());
    }

    public static void DFS(int depth){
        
        if(depth == M){
            for(int i = 0 ; i < M ; i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }
        
        for(int i = 1 ; i <= N ; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                arr[depth] = i;
                DFS(depth +1);
                isVisited[i] = false;
            }
        }
        
    }
}
