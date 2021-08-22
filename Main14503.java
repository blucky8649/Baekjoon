import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503 {

    // --------------<백준 14503번 : 로봇 청소기>--------------------------------
    // 1. DFS : 로봇 청소기를 움직인다 (왼쪽으로 회전하며 가는 모든 경우를 계산한다.)
    // 2. 4방이 막혀있는 곳이면 바라보는 방향을 유지한채 한칸 후진을 한다. 
    // ------------------------------------------------------------------------


    static int n, m, map[][], cnt = 0;
    static int[] dy = {-1, 0, 1, 0}; // 북동남서 : 왼쪽으로 회전을 하려면 (dir + 3) % 4 를! 해야한다.  
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine(), " ");
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < n ;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(x, y, dir);
        System.out.println(cnt);

    }
    static void clean(int x, int y, int dir){
        
        // 현재 위치를 먼저 청소한다.
        if(map[y][x] == 0){
            map[y][x] = 2;
            cnt++; //청소한 횟수를 세어준다.
        }
        int org = dir;
        for(int i = 0 ; i < 4 ; i++){
            int ndir = (dir + 3) % 4;
            int nx = x + dx[ndir];
            int ny = y + dy[ndir];
            

            if(nx > 0 && nx < m && ny > 0 && ny < n){
                if(map[ny][nx] == 0){ //청소가 안된 지역은 청소를 해야됩니다.
                    clean(nx, ny, ndir);
                    return ; 
                }
            }
            dir = (dir + 3) % 4; // 만약 진행하는곳이 막다른길이면 로봇청소기 고개를 돌려줘야함
        }
        // 이 코드를 지나간다라는 것은 4방에 청소할 곳이 없다는 말이 됨. 현재위치를 유지하면서 뒤로 가주어야함.
     
            int back = (dir + 2) % 4;
            int bx = x + dx[back];
            int by = y + dy[back];
            if(bx > 0 && bx < m && by > 0 && by < n){
                if(map[by][bx] != 1){
                    clean(x + dx[back], y + dy[back], org);
                }
                
            }
        
        
        
    }
}
