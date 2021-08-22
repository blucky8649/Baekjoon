import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main13913 {
    static int[] status= new int[3];
    static int N, K;
    static int[] map;
    static boolean[] isVisited;
    static int[] parent = new int[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br. readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[100001];
        isVisited = new boolean[100001];
        BFS(N);


        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        while (index != N) {
            stack.push(parent[index]);
            index = parent[index];
        }
        sb.append(map[K]).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }
    static void BFS(int now){
        Queue<subin> q = new LinkedList<>();
        
        isVisited[now] = true;
        
        
        q.offer(new subin(now));
        
        while(!q.isEmpty()){
            subin d = q.poll();
            status[0] = d.now + 1;
            status[1] = d.now - 1;
            status[2] = d.now * 2;
            
        
            if(d.now == K){
                
                
                return;
            }
            for(int i = 0 ; i < status.length ; i ++){
                if(status[i] >= 0 && status[i] <100001){
                    if(isVisited[status[i]] || map[status[i]] > 0) continue;
    
                    q.offer(new subin(status[i]));
                    map[status[i]] = map[d.now] + 1;
                    isVisited[status[i]] = true;
                    parent[status[i]] = d.now;
                }
            }
        }
    }
}
class subin{
    int now;


    subin(int now){
        this.now = now;

    }
}