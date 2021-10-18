import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11724 {
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 정점
        int m = Integer.parseInt(st.nextToken()); // 간선
        isVisited = new boolean[n + 1];

        for (int i = 0 ; i < n + 1 ; i++) {
            arr.add(new ArrayList<Integer>());
        }

        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            arr.get(start).add(end);
            arr.get(end).add(start);
        }
        int cnt = 0;
        for (int i = 1 ; i <= n ; i++) {
            if (!isVisited[i]) {
                BFS(i);
                cnt ++;
            }
        }

        System.out.println(cnt);
    }
    static void BFS(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        isVisited[n] = true;

        while (!q.isEmpty()) {
            int d = q.poll();

            for (int i = 0 ; i < arr.get(d).size() ; i++) {
                int y = arr.get(d).get(i);
                
                if (!isVisited[y]) {
                    isVisited[y]= true;
                    q.offer(y);
                }
            }
        }
    }
    
}
