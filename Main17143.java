import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17143 {
    static Queue<Shark1> q = new LinkedList<>();
    static int R, C, M, map[][], me=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            q.offer(new Shark1(x, y, s, d, z));
            map[y][x] = z;
        }
        

    }
    static void getFish(int me){
        for(int i = 0 ; i < C ; i++){
           
        }
    }
}
class Shark1{
    int x;
    int y;
    int s;
    int d;
    int z;
    Shark1(int x, int y, int s, int d, int z){
        this.x =x;
        this.y =y;
        this.s =s;
        this.d =d;
        this.z =z;
    }
}