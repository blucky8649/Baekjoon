import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main7569 {
    static int result = Integer.MIN_VALUE;
    static int[] dy ={0, 0, -1, 0, 0, 1};
    static int[] dx ={0, -1, 0, 0, 1, 0};
    static int[] dz ={-1, 0, 0, 1, 0, 0};
    static int H, M, N;
    static int[][][] map;
    static Queue<Tomato2> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int z = 0; z < H ; z++){
                    
                    map[z][i][j] = Integer.parseInt(st.nextToken());
                    if(map[z][i][j] == 1){
                        q.offer(new Tomato2(j, i, z));
                    }            
                }
            }
        }
        
        System.out.println(BFS());
        
    }
    static int BFS(){
        while(!q.isEmpty()){
            Tomato2 t = q.poll();

            for(int i = 0 ; i<dx.length; i++){
                int X = t.x + dx[i];
                int Y = t.y + dy[i];
                int Z = t.z + dz[i];
                if(X>=0 && Y>=0 && Z>= 0 && X<M && Y<N && Z<H){
                    if(map[Z][Y][X] == 0){
                        q.offer(new Tomato2(X, Y, Z));
                        map[Z][Y][X] = map[t.z][t.y][t.x] + 1;
                    }
                }
            }
        }

        for(int i = 0 ; i < H ; i++){
            for(int j = 0 ; j < N ; j++){
                for(int z = 0 ; z < M ; z++){
                    if(map[i][j][z] == 0){
                        return -1;
                    }
                    result = Math.max(result, map[i][j][z]);
                    
                }
            }
        }
        if(result == 1){
            return 0;
        }else{
            return result -1;
        }
        
    }
}
class Tomato2{
    int x;
    int y;
    int z;
    Tomato2(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
}