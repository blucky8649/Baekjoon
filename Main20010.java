import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main20010 {
    static int N, K, max = Integer.MIN_VALUE, leaf = 0;
    static boolean[] isVisited;
    static PriorityQueue<Vil> q = new PriorityQueue<>();
    static PriorityQueue<Vil> q2 = new PriorityQueue<>();
    static int[] parents;
    static ArrayList<Vil>[] list; //인접 리스트 구성
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine() , " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parents = new int[N];
        list = new ArrayList[N];
        isVisited = new boolean[N];
        for (int i = 0 ; i < N ; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1 ; i < N ; i++) {
            parents[i] = i;
        }
        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            q.offer(new Vil(start, end, weight));
            q2.offer(new Vil(start, end, weight));
        }
        
        int ans = 0;
        int cnt = 0;
        while (!q.isEmpty()) {
            Vil d = q.poll();
            int a = find(d.start);
            int b = find(d.end);

            if (a == b) continue;
            cnt ++;
            union(a, b);
            ans += d.weight;
            list[d.start].add(new Vil(d.end, d.weight));
            list[d.end].add(new Vil(d.start, d.weight));
            if (cnt == N - 1) break;
        }
        System.out.println(ans);
        isVisited[0] = true;
        DFS(0, 0);

        isVisited = new boolean[N];
        isVisited[leaf] = true;
        DFS(leaf, 0);

        System.out.println(max);
    }
    static void DFS(int node, int sum) {
        if (max < sum) {
            max = sum;
            leaf = node;
        }

        for (Vil d : list[node]) {
            if (isVisited[d.end]) continue;
            isVisited[d.end] = true;
            DFS(d.end, sum + d.weight);
        }
    }
    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A != B){
            parents[B] = A;
        }
        return;
    }
}
class Vil implements Comparable<Vil>{
    int start;
    int end;
    int weight;
    Vil(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    Vil(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(Vil o) {
        return this.weight - o.weight;
    }
}