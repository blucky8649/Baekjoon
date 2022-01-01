import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main10282 {
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0 ; tc < t ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ArrayList<Noh>[] list = new ArrayList[n + 1];

            for (int i = 0 ; i <= n ; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0 ; i < d ; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                list[end].add(new Noh(start, weight));
            }

            dijkstra(n, c, list);
        }
        
        
    }
    static void dijkstra(int n, int c, ArrayList<Noh>[] list) {
        int cnt = 0;
        int max = 0;
        boolean[] isVisited = new boolean[n + 1];
        PriorityQueue<Noh> q = new PriorityQueue<>();
        q.offer(new Noh(c, -1));
        int[] dst = new int[n + 1];
        Arrays.fill(dst, INF);
        dst[c] = 0;

        while (!q.isEmpty()) {
            Noh d = q.poll();
            if (isVisited[d.end]) continue;
            isVisited[d.end] = true;

            for (Noh node : list[d.end]) {
                if (dst[node.end] > dst[d.end] + node.weight) {
                    dst[node.end] = dst[d.end] + node.weight;
                    q.offer(new Noh(node.end, dst[node.end]));
                }
            }
        }
        for (int i = 1 ; i <= n ; i++) {
            if (dst[i] != INF) {
                cnt++;
                max = Math.max(max, dst[i]);
            }
        }
        System.out.println(cnt + " " + max);
    }
}
class Noh implements Comparable<Noh>{
    int end;
    int weight;
    Noh(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(Noh o) {
        return this.weight - o.weight;
    }
}