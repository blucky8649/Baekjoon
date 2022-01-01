import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main22856 {
    static int n;
    static ArrayList<Integer>[] list;
    static boolean[] isVisited;
    static int[] parent;
    static int[] weight;
    static int end = 1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        parent = new int[n + 1];
        weight = new int[n + 1];
        isVisited = new boolean[n + 1];
        for (int i = 0 ; i <= n ; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            list[p].add(r);
            list[p].add(l);
            if (r != -1) {
                parent[r] = p;
            }
            if (l != -1) {
                parent[l] = p;
            }
        }
        int p = 1;
        int son = list[p].get(1);
        while (son != -1) {
            son = list[p].get(1);
            if (son == -1) break;
            end = son;
            p = son;
        }
        search(1, 0);
        System.out.println((weight[end]));

    }
    static void search(int start, int cnt) {
        Stack<DFS> stack = new Stack<>();
        stack.push(new DFS(start, 0));
        weight[start] = 0;
        isVisited[start] = true;
        if (start == end) return;
        while(!stack.isEmpty()) {
            
            DFS d = stack.pop();
            boolean flag = false;
            for (int num : list[d.end]) {
                if (num == -1) {
                    stack.add(new DFS(parent[d.end], d.cnt + 1));
                    flag = true;
                    break;
                }
                
                if (!isVisited[num]) {
                    isVisited[num] = true;
                    weight[num] = d.cnt + 1;
                    flag = true;
                    if (num == end) return;
                    stack.add(new DFS(num, d.cnt + 1));
                    break;
                }
            }
            if (!flag) {
                stack.add(new DFS(parent[d.end], d.cnt + 1));
            }
            
            

        }
    }
}

class DFS {
    int end;
    int cnt;
    DFS(int end, int cnt) {
        this.end = end;
        this.cnt = cnt;
    }
}
