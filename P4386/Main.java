package P4386;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int parents[];
    static Point[] point;
    static PriorityQueue<Node> q = new PriorityQueue<>(); 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parents = new int[n];
        point = new Point[n];
        for (int i = 0 ; i < n ; i++) {
            parents[i] = i;
        }
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            point[i] = new Point(x, y);
        }

        for (int i = 0 ; i < n - 1 ; i++) {
            for (int j = i + 1 ; j < n ; j++) {
                double weight = calc_dst(point[i], point[j]);
                q.offer(new Node(i, j, weight));
            }
        }
        double ans = 0.0;
        int cnt = 0;
        while (!q.isEmpty()) {
            Node d = q.poll();

            int a = find(d.start);
            int b = find(d.end);

            if (a == b) continue;
            union(a, b);
            cnt ++;
            ans += d.weight;

            if (cnt == n - 1) break;
        }
        System.out.println(ans);

    }
    static void union(int a, int b) {
        int A = find(a);
        int B = find(b);

        if (A != B) {
            parents[B] = A;
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
    double x;
    double y;
    Point(double x, double y) {
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
    public int compareTo(Node o){
        return this.weight < o.weight ? -1 : 1;
    }
}
