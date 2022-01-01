import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1068 {
    static int n, d, ans = 0;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[] parents = new int[n];
        list = new ArrayList[n];
        for (int i = 0 ; i < n ; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int start_node = 0;
        for (int i = 0 ; i < n ; i++) {
            int p = Integer.parseInt(st.nextToken());
            parents[i] = p;
            if (p == -1) {
                start_node = i;
                continue;
            }
            list[p].add(i);
        }
        d = Integer.parseInt(br.readLine()); // 지울 노드
        if (d == start_node) {
            System.out.println(0);
            return;
        }
        list[d].clear();
        list[parents[d]].remove(Integer.valueOf(d));

        BFS(start_node);
        System.out.println(ans);
    }
    static void BFS(int start) {
        boolean[] isVisited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int d = q.poll();
            if (list[d].size() == 0) {
                ans++;
                continue;
            }
            for (int next : list[d]) {
                q.offer(next);
                isVisited[next] = true;
            }
        }
    }
}
