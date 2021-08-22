import java.io.*;
import java.util.StringTokenizer;

public class Main15651 {
    public static int N,M;
    public static int[] arr;
    public static boolean[] isVisited = new boolean[10];
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader ( new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        DFS(1, 0);
        System.out.print(sb.toString());
        
    }
    public static void DFS(int at, int depth){
       
        if (depth == M){
            for(int i = 0 ; i< M ; i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = at ; i <= N ; i++){

                arr[depth] = i;
                DFS(i, depth +1);

                
           
          
                
      

        }
    }
}
