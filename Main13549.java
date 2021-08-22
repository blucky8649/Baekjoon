import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main13549 {
    public static int answer = (int)1e9;
    public static int N,K;
    public static boolean[] isVisited = new boolean[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        BFS(N, 0);
        System.out.println(answer);
    }

    public static void BFS(int now, int seconds){
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(now, seconds));
        isVisited[now] = true;
 
        while(!q.isEmpty()){
            Location d = q.poll();
            int go = d.now + 1;
            int back = d.now - 1;
            int teleport = d.now * 2;
            
            if(d.now == K){
                answer = Math.min(answer, d.seconds);
                return;
            }
            if(teleport>= 0 && teleport<100001 && !isVisited[teleport]){
                q.offer(new Location(teleport, d.seconds));
                isVisited[teleport] = true;
            }

            if(go>= 0 && go<100001 && !isVisited[go]){
                q.offer(new Location(go, d.seconds + 1));
                isVisited[go] = true;
            }
            
            if(back>= 0 && back<100001 && !isVisited[back]){
                q.offer(new Location(back, d.seconds + 1));
                isVisited[back] = true;
                
            }
            
           
        }

    }
}

class Location{
    int now;
    int seconds;
    Location(int now, int seconds){
        this.now = now;
        this.seconds = seconds;
    }

}
