package P1774;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Point[] point;
    static int[] parents;
    static PriorityQueue<Node> q = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        point = new Point[N];
        parents = new int[N];
        for (int i = 0 ; i < N ; i++) {
            parents[i] = i;
        }
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            point[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0 ; i < N -1 ; i++) {
            for (int j = i + 1 ; j < N ; j++) {
                double weight = calc_dst(point[i], point[j]);
                q.offer(new Node(i, j, weight));
            }
        }
        int cnt = 0;
        double ans = 0.0;
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = find(Integer.parseInt(st.nextToken()) - 1);
            int b = find(Integer.parseInt(st.nextToken()) - 1);
            union(a, b);
        }


        while (!q.isEmpty()) {
            Node d = q.poll();
            int a = find(d.start);
            int b = find(d.end);

            if (a == b) continue;
            union(a, b);
            cnt++;
            ans += d.weight;
            if (cnt == N - 1) break;
        }
        System.out.println(String.format("%.2f", ans));
        

        
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parents[b] = a;
        }
    }
    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    static double calc_dst(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Node implements Comparable<Node>{
    int start;
    int end;
    double weight;
    Node(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight < o.weight ? -1 : 1;
    }
}
