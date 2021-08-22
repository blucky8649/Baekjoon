import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main16234 {
    static int N, L, R, map[][], year = 0, sum= 0;
    static boolean[][] isVisited;
    static boolean flag;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1}; 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isVisited = new boolean[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while(true){
            flag = false;
            isVisited = new boolean[N][N];
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(!isVisited[i][j]){
                        move(j, i);
                    }
                }
            }

            if(!flag){
                
                System.out.println(year);
                break;
            }
            
            
            year++;
        }
        
        
    }   
    static void move (int x, int y){
        Queue<Population> q = new LinkedList<>();
        Queue<Population> q1 = new LinkedList<>();
        isVisited[y][x] = true;

        int sum = 0;
        sum += map[y][x];
        q.offer(new Population(x, y, map[y][x]));
        q1.offer(new Population(x, y, map[y][x]));
        while(!q.isEmpty()){
            
            Population d= q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(  nx >= 0 && nx<N && ny >= 0 && ny <N && !isVisited[ny][nx]){
                    if(Math.abs(map[ny][nx] - map[d.y][d.x]) >= L && Math.abs(map[ny][nx] - map[d.y][d.x]) <= R){
                        flag = true;
                        isVisited[ny][nx] = true;
                        q.offer(new Population(nx, ny, map[ny][nx]));
                        q1.offer(new Population(nx, ny,map[ny][nx]));
                        sum += map[ny][nx];
                    }
                    
               
                }
            }
        }
        
        if(q1.size() > 1){
            int val = sum / q1.size();
            while(!q1.isEmpty()){
                Population d= q1.poll();
                map[d.y][d.x] = val;
       
             }
        }
    }
        
}
class Population{
    int x;
    int y;
    int val;
    Population(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

