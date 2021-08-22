import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main9205 {
    static Queue<Location2> q = new LinkedList<>();
    static int T, N;
    static Location2[] locations;
    static boolean[] isVisited;
    static boolean success;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        T = Integer.parseInt(br.readLine());
        

        for(int i = 0 ; i < T ; i++){
            N = Integer.parseInt(br.readLine());
            locations = new Location2[N+2];
            isVisited = new boolean[N+2];
            for(int y = 0 ; y < N +2 ; y++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                    
                int X = Integer.parseInt(st.nextToken());  
                int Y = Integer.parseInt(st.nextToken());  
                locations[y] = new Location2(X, Y);

                
            }
        Location2 start = locations[0];
        Location2 end = locations[N + 1];
        q.add(start);

        success = BFS(0, end);
        if(success){
            sb.append("happy").append('\n');
        }else{
            sb.append("sad").append("\n");
        }

        }
        System.out.print(sb.toString());
    }
    static boolean BFS(int k, Location2 end){
        isVisited[k] = true;
        
        while(!q.isEmpty()){
            Location2 d = q.poll();

            if(d == end){
                return true;
            }
            for(int i = 1 ; i < N +2 ; i++){
                if(!isVisited[i] && Math.abs(d.x - locations[i].x) + Math.abs(d.y - locations[i].y) <= 1000){
                    isVisited[i] = true;
                    q.offer(locations[i]);
                }
            }

            
        }
        return false;
        

    }
}
class Location2{
    int x;
    int y;
    Location2(int x, int y){
        this.x = x ;
        this.y = y ;
    }
}