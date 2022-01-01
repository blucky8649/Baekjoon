package P2307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static final int INF = 1_000_000_000;
    static int N, M;
    static int dst[];
    static ArrayList<Node>[] list;
    static int[] route;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0 ; i <= N ; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }
        int ans = 0;
        int org = dijkstra(1);
        for (int i = N ; i > 0 ; i = route[i]) {
            int max_dst = dijkstra2(route[i], i);
            if (max_dst == -1) {
                System.out.println(-1);
                return;
            }
            ans = Math.max(ans, max_dst - org);
        }
        System.out.println(ans);
    }
    static int dijkstra(int start) {
        route = new int[N + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];
        dst = new int[N + 1];
        Arrays.fill(dst, INF);
        dst[start] = 0;
        q.offer(new Node(start, 0));

        while (!q.isEmpty()) {
            Node d = q.poll();

            if (isVisited[d.end]) continue;
            isVisited[d.end] = true;

            for (Node node : list[d.end]) {
                if (dst[node.end] > dst[d.end] + node.weight) {
                    route[node.end] = d.end; // 경로 선정
                    dst[node.end] = dst[d.end] + node.weight;
                    q.offer(new Node(node.end, dst[node.end]));
                }
            }
        }
        return dst[N] == INF ? -1 : dst[N];
    }
    static int dijkstra2(int from, int to) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];
        dst = new int[N + 1];
        Arrays.fill(dst, INF);
        dst[1] = 0;
        q.offer(new Node(1, 0));

        while (!q.isEmpty()) {
            Node d = q.poll();

            if (isVisited[d.end]) continue;
            isVisited[d.end] = true;

            for (Node node : list[d.end]) {
                if (from == d.end && to == node.end) continue;
                if (dst[node.end] > dst[d.end] + node.weight) {
                    dst[node.end] = dst[d.end] + node.weight;
                    q.offer(new Node(node.end, dst[node.end]));
                }
            }
        }
        return dst[N] == INF ? -1 : dst[N];
    }
}
class Node implements Comparable<Node>{
    int start;
    int end;
    int weight;
    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    Node(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}