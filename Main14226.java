import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main14226 {
    public static int n;
    public static int[][] map;
    public static boolean[][] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[1001][1001];
        isVisited = new boolean[1001][1001];
        BFS(0, 1, 0);

        
    }
    public static void BFS(int cb, int now, int seconds){
        Queue<Emo> q = new LinkedList<>();
        isVisited[cb][now] = true;
        q.offer(new Emo(cb, now, seconds));

        while(!q.isEmpty()){
            Emo d = q.poll();
            int paste = d.now + d.cb;
            int minus = d.now - 1;

            if(d.now == n){
                System.out.println(d.seconds);
                return;
            }
            q.offer(new Emo(d.now, d.now, d.seconds + 1));
            

            if(d.cb > 0 && paste < 1001 &&!isVisited[d.cb][paste]){
                q.offer(new Emo(d.cb, paste, d.seconds + 1));
                isVisited[d.cb][paste] = true;
            }
            if(minus >= 0 && minus <= 1001 && !isVisited[d.cb][minus]){
                q.offer(new Emo(d.cb, minus, d.seconds + 1));
                isVisited[d.cb][minus] = true;
            }
            
            
        }


    }
}

class Emo{
    int cb;
    int now;
    int seconds;
    Emo(int cb, int now, int seconds){
        this.cb = cb;
        this.now = now;
        this.seconds = seconds;
    }
}