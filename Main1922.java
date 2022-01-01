import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1922 {
    static int N, M, ans = 0;
    static int[] parents;
    static PriorityQueue<MST2> q = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        for (int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            q.offer(new MST2(start, end, weight));

        }
        int cnt = 0;
        while (!q.isEmpty()) {
            MST2 d = q.poll();

            int a = find(d.start);
            int b = find(d.end);

            if (a == b) continue;
            union(a, b);
            ans += d.weight;
            cnt++;
            if (cnt == N - 1) break;
            
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
        int A = find(a);
        int B = find(b);

        if (A != B) {
            parents[B] = A;
        }
        return;
    }
}
class MST2 implements Comparable<MST2>{
    int start;
    int end;
    int weight;
    MST2(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(MST2 o) {
        return this.weight - o.weight;
    }
}