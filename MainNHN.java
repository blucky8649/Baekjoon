import java.io.*;
import java.util.*;

class Waiter{
    int idx;
    int start;
    int end;
    Waiter(int idx, int start, int end){
        this.idx = idx;
        this.start = start;
        this.end = end;
    }
}


public class MainNHN {
    static int N, K, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Waiter> wait = new ArrayList<>();
        ArrayList<Waiter> on = new ArrayList<>();

        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            wait.add(new Waiter(i, start, end));
        }

        int cnt = 0;
        int floor = 1;
        boolean up = true;

        while(!on.isEmpty() || !wait.isEmpty()){
            
            for(int i = 0 ; i < wait.size() ; i++){
                Waiter d = wait.get(i);

                if(d.start == floor && on.size() < M){
                    wait.remove(i);
                    on.add(d);
                    i--;
                }
            }

            for(int i = 0 ; i < on.size() ; i++){
                Waiter d = on.get(i);

                if(d.end == floor){
                    on.remove(i);
                    i--;
                }
            }

            if(floor == K) {
                up = false;
            }else if (floor == 1){
                up = true;
            }

            if (up) {
                floor ++;
            } else{
                floor --;
            }
            cnt++;
            
        }
        System.out.println(cnt);
        
    }    
}
