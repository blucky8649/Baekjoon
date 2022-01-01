import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main22865 {
    static int N, A, B, C, M;
    static long[] dst;
    static ArrayList<Ode>[] adj;
    static long min_dst[];
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        min_dst = new long[N + 1];
        Arrays.fill(min_dst, INF);
        adj = new ArrayList[N + 1];
        dst = new long[N + 1];
        for (int i = 0 ; i <= N ; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            adj[D].add(new Ode(E, L));
            adj[E].add(new Ode(D, L));
        }
        for (int i = 1 ; i <= N ; i++) {
            if (i == A || i == B || i == C) {
                dijkstra(i);
            }
        }
        long ans = 0;
        int num = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (min_dst[i] > ans) {
                num = i;
                ans = min_dst[i];
            }
        }
        System.out.println(num);
    }
    static void dijkstra(int k) {
        boolean[] check = new boolean[N + 1];
        Arrays.fill(dst, INF);
        PriorityQueue<Ode> q = new PriorityQueue<>();
        q.offer(new Ode(k, 0));
        dst[k] = 0;

        while (!q.isEmpty()) {
            Ode cur = q.poll();

            if (check[cur.end]) continue;
            check[cur.end] = true;

            for (Ode next : adj[cur.end]) {
                if (!check[next.end] && dst[next.end] > dst[cur.end] + next.weight) {
                    dst[next.end] = dst[cur.end] + next.weight;
                    q.offer(new Ode(next.end, dst[next.end]));
                }
            }
        }
        for (int i = 1 ; i <= N ; i++) {
            min_dst[i] = Math.min(dst[i], min_dst[i]);
        }
    }
}
class Ode implements Comparable<Ode>{
    int end;
    long weight;
    Ode(int end, long weight) {
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(Ode o) {
        return (int)(this.weight - o.weight);
    }
}
class Ground implements Comparable<Ground>{
    int num;
    long weight;
    Ground(int num, long weight) {
        this.num = num;
        this.weight = weight;
    }
    @Override
    public int compareTo(Ground o) {
        if (this.weight == o.weight) {
            return this.num - o.num;
        }
        return (int)(o.weight - this.weight);
    }
}