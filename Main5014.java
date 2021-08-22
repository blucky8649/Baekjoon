import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main5014 {
    static int[] button = new int[2];
    static int F, S, G, U, D;
    static int[] building;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        building = new int[F + 1];
        isVisited   = new boolean[F + 1];

        BFS(S);
        if(S == G){
            System.out.println(0);
        }else{
            if(building[G] ==0){
                System.out.println("use the stairs");
            }else{
                System.out.print(building[G]);
            }
        }
        
        
    }

    static void BFS(int now){
        Queue<Me> q = new LinkedList<>();
        isVisited[now] = true;
        q.offer(new Me(now));

        while(!q.isEmpty()){
            Me d = q.poll();

            button[0] = d.x + U;
            button[1] = d.x - D;
            for(int i = 0 ; i < button.length ; i++){
                if(button[i] >= 1 && button[i] <= F){
                    if(isVisited[button[i]]|| building[button[i]] > 0 ) continue;
                    isVisited[button[i]] = true;
                    q.offer(new Me(button[i]));
                    building[button[i]] = building[d.x] + 1;
                }
            }
        }
        
    }
}
class Me{
    int x;
    Me(int x){
        this.x = x;
    }
}
