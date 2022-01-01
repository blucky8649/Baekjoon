import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main14938 {
    static int n, m, r, ans = 0;
    static ArrayList<Area>[] arr;
    static int[] dst, item;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // 지역 수
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 길의 갯수

        arr = new ArrayList[n + 1];
        
        for (int i = 0 ; i <= n ; i++) {
            arr[i] = new ArrayList<>();
        }
        dst = new int[n + 1];
        item = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1 ; i <= n ; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[start].add(new Area(end, weight));
            arr[end].add(new Area(start, weight));
        }
        for (int i = 1 ; i <= n ; i++) {
            search(i);
        }
        System.out.println(ans);
    }
    static void search(int start) {
        PriorityQueue<Area> q = new PriorityQueue<>();
        q.offer(new Area(start, 0));
        dst = new int[n + 1];
        Arrays.fill(dst, Integer.MAX_VALUE);
        boolean[] check = new boolean[n + 1];
        dst[start] = 0;

        while (!q.isEmpty()) {
            Area cur = q.poll();

            if (check[cur.end]) continue;
            check[cur.end] = true;

            for (Area node : arr[cur.end]) {
                if (!check[node.end] && dst[node.end] > dst[cur.end] + node.weight) {
                    dst[node.end] = dst[cur.end] + node.weight;
                    q.offer(new Area(node.end, dst[node.end]));
                }
            }
        }
        int get_item = 0;
        for (int i = 1 ; i <= n ; i++) {
            if (dst[i] <= m) {
                get_item += item[i];
            }
        }
        ans = Math.max(ans, get_item);
    }
}

class Area implements Comparable<Area>{
    int end;
    int weight;
    Area (int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo (Area o) {
        return this.weight - o.weight;
    }
}