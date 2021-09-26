import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15651_2 {
    static int n, m;
    static boolean isVisited[];
    static String[] num;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isVisited = new boolean[m];
        num = new String[m];
        DFS(0);
        System.out.print(sb.toString());

    }
    static void DFS(int depth){
        
        if(depth == m){
            String str = "";
            for(int i = 0 ; i < m ; i++){
                sb.append(num[i] + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i = 1; i <= n ; i++){
            if(!isVisited[depth]){
                isVisited[depth] = true;
                num[depth] = String.valueOf(i);
                DFS(depth + 1);
                isVisited[depth] = false;
                num[depth] = "";
            }
                

        }
        
        

    }
}
