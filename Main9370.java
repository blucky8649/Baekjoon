import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main9370 {
    static int n, m, t;
    static int[] dst;
    static final int INF = 100_000_000;
    static ArrayList<Destination>[] list;
    static ArrayList<Integer> dest;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int a = 0 ; a < T ; a++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            list = new ArrayList[n + 1];

            dst = new int[n + 1];
            Arrays.fill(dst, INF);
            
        
            for (int i = 0 ; i <= n ; i++) {
                list[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()); // 시작 위치
            int g = Integer.parseInt(st.nextToken()); 
            int h = Integer.parseInt(st.nextToken());

            for (int i = 0 ; i < m ; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                
                if ((start == g && end == h) || (start == h && end == g)) {
                    list[start].add(new Destination(end, weight * 2 - 1));
                    list[end].add(new Destination(start, weight * 2 - 1));
                } else {
                    list[start].add(new Destination(end, weight * 2));
                    list[end].add(new Destination(start, weight * 2));
                }
                
            }
            dest = new ArrayList<>();
            for (int i = 0 ; i < t ; i ++) {
                dest.add(Integer.parseInt(br.readLine()));
            }
            
            dijkstra(s);

            Collections.sort(dest);

            for (int z : dest) {
                if (dst[z] % 2 == 1) {
                    sb.append(z + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    static void dijkstra(int start) {
        boolean[] check = new boolean[n + 1];
        PriorityQueue<Destination> q = new PriorityQueue<>();
        q.offer(new Destination(start, 0));
        dst[start] = 0;

        while (!q.isEmpty()) {
            Destination cur = q.poll();

            if (check[cur.end]) continue;
            check[cur.end] = true;

            for (Destination node2 : list[cur.end]) {
                if (!check[node2.end] && dst[node2.end] > dst[cur.end] + node2.weight) {
                    dst[node2.end] = dst[cur.end] + node2.weight;
                    q.offer(new Destination(node2.end, dst[node2.end]));
                }
            }
        }
    }
}
class Destination implements Comparable<Destination>{
    int end;
    int weight;
    Destination (int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo (Destination o) {
        return this.weight - o.weight;
    }
}