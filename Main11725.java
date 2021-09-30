import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main11725 {
    static ArrayList<Integer>[] list;
    static boolean check[];
    static int parents[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        parents = new int[n + 1];
        check = new boolean[n + 1];

        for(int i = 1 ; i <= n ; i++){
            list[i] = new ArrayList<>();
        }


        for(int i = 1 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for(int i = 1 ; i <= n ; i++){
            if(!check[i]){
                DFS(i);
            }
        }
        for (int i = 2; i <=n ; i++) {
            System.out.println(parents[i]);
        }
    }
    static void DFS(int i){
        if(check[i]) return;
        check[i] = true;

        for(int num : list[i]){
            if(!check[num]){
                parents[num] = i;
                DFS(num);
            }
            
        }
    }
}
