import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_CF2 {
    static int n, a, b;
    static boolean flag = false;
    static int[] arr;
    static boolean[] isVisited;
    static PriorityQueue<Integer> aq;
    static PriorityQueue<Integer> bq;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0 ; i <  t ; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            flag = false;
            aq = new PriorityQueue<>();
            bq = new PriorityQueue<>(Collections.reverseOrder());
            isVisited = new boolean[n + 1];
            isVisited[a] = true;
            DFS(a + 1, 0);
            if (aq.size() == 0 || bq.size() == 0) {
                System.out.print(-1);
            } else {
                while (!aq.isEmpty()) {
                    System.out.print(aq.poll() + " ");
                }
                while (!bq.isEmpty()) {
                    System.out.print(bq.poll() + " ");
                }
            }
            System.out.println();
        }
    }
    static void DFS(int start, int k) {
        if (k == (n / 2) - 1) {
            if (flag) return;
            for (int i = 1 ; i < isVisited.length ; i++) {
                if (!isVisited[i] && b >= i) {
                    bq.offer(i);
                } else {
                    aq.offer(i);
                }
            }
            if (bq.size() == aq.size() && aq.peek() == a && bq.peek() == b) {
                flag = true;
            } else {
                aq = new PriorityQueue<>();
                bq = new PriorityQueue<>(Collections.reverseOrder());
            }
            return;
        }

        for (int i = start ; i <= n ; i++) {
            if (!isVisited[i] && i >= a) {
                isVisited[i] = true;
                DFS(start + 1, k + 1);
                isVisited[i] = false;
            }
        }
    } 
}
