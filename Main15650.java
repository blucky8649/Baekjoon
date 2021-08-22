import java.io.*;
import java.util.StringTokenizer;

public class Main15650 {
    public static int[] arr;
    public static boolean[] isVisited;
    public static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[10];
        isVisited = new boolean[10];
        DFS(1, 0);
    }

    public static void DFS(int at, int depth){
        if(depth == M){
            for(int i = 0; i < M ; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = at ; i<=N ; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                arr[depth] = i;
                DFS(i + 1,depth +1);
                isVisited[i] = false;

        }
    }
}
}