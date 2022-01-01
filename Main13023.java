import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main13023 {
    static int n, m, answer = 0;
    static ArrayList<Integer>[] list;
    static boolean isVisited[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        isVisited = new boolean[n + 1];
        for (int i = 0 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list[start].add(end);
            list[end].add(start);
        }

        for (int i = 1 ; i <= n ; i++) {
            DFS(i, 0);
            if (answer == 1) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(answer);
    }
    static void DFS(int cur, int depth) {
        if (depth == 4) {
            answer = 1;
            return;
        }
        isVisited[cur] = true;
        for (int num : list[cur]) {
            if (!isVisited[num]) {
                DFS(num, depth + 1);
            }            
        }
        isVisited[cur] = false;
    }
}
