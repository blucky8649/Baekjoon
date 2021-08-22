import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1697 {
    public static int N,K;
    public static int[] map;
    public static boolean[] isVisited;
    public static int[] status = new int[3];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[100001];
        isVisited = new boolean[100001];
        BFS(N);
            System.out.print(map[K]);

        
    }

    public static void BFS(int n){
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        isVisited[n] = true;
        

        while(!q.isEmpty()){
            
            int tmp = q.poll();
            status[0] = tmp + 1;
            status[1] = tmp - 1;
            status[2] = tmp * 2;
            if(tmp == K){
                break;
            }
            for(int i = 0 ; i < 3 ; i++){
                if(status[i] >= 0 && status[i] < 100001){
                    System.out.println(status[i]);
                    if(map[status[i]] == 0 && !isVisited[status[i]]) {
                        q.add(status[i]);
                        map[status[i]] = map[tmp] + 1;
                        isVisited[status[i]] = true;
                    }
                    
                    
                }
            }

        }
        

    }
    
}
