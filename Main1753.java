import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1753 {
    static int V, E, dst[];
    static Node map[];
    static boolean check[];
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new Node[E + 1];
        dst = new int[V + 1];
        check = new boolean[V + 1];
        list = new ArrayList[V + 1];
        for(int i = 1 ; i < V + 1 ; i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dst, Integer.MAX_VALUE);
        int K = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < E ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }
        dijkstra(K);
        for(int i = 1 ; i < dst.length ; i++){
            sb.append(dst[i] != Integer.MAX_VALUE ? dst[i] : "INF").append("\n");
        }
        System.out.print(sb.toString());
    }
    static void dijkstra(int K){
        PriorityQueue<Node> q = new PriorityQueue<>();
        dst[K] = 0;
        q.offer(new Node(K, 0));

        while(!q.isEmpty()){
            int start = q.poll().end;

            if(check[start]) continue;
            check[start] = true;

            for(Node node : list[start]){
                if(dst[node.end] > dst[start] + node.w){
                    dst[node.end] = dst[start] + node.w;
                    q.offer(new Node(node.end,dst[node.end]));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int end;
    int w;
    Node(int end, int w){
        this.end = end;
        this.w = w;
    }
    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.w, o.w);
    }
    
}
