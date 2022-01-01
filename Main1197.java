import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1197 {
    static int V, E;
    static int edges[][];
    static int parents[];
    static PriorityQueue<MST> q = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V + 1];
        for (int i = 1 ; i <= V ; i++ ) {
            parents[i] = i;
        }
        for (int i = 0 ; i < E ; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            q.offer(new MST(start, end, weight));
        }
        int ans = 0;
        int cnt = 0;
        while (!q.isEmpty()) {
            MST d = q.poll();

            int a = find(d.start);
            int b = find(d.end);

            if (a==b) continue;

            union(a, b);
            ans += d.weight;
            cnt++;
            if (cnt == V - 1) break;
        }
        System.out.println(ans);
        
        


    }
    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union (int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
        }
        return ;

    }
}
class MST implements Comparable<MST>{
    int start;
    int end;
    int weight;
    MST (int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(MST o) {
        return this.weight - o.weight;
    }
}