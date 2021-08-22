import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17144 {
    /**
     * 1. BFS를 이용한 탐색
     * 2. Clone 맵을 사용하여 바람 흐름 계산 후 다시 붙여넣거
     * 3. 윗공기와 밑공기를 각각의 Queue로 계산
     * 4. 만약 nx, ny의 범위를 벗어나려거든 방향전환을 함
     */

     static int R, C, T, map[][], upAirX= 0, upAirY = 0, sum = 0, downAirX = 0, downAirY = 0, cc=0;
     static int[] dy = {0, -1, 0, 1}; // 동, 북, 서, 남
     static int[] dx = {1, 0, -1, 0};
     static Queue<MicroDust> upAir = new LinkedList<>();
     static Queue<MicroDust> downAir = new LinkedList<>();
     static Queue<MicroDust> dust = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for(int i = 0 ; i < R ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < C ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == -1){
                    downAir.offer(new MicroDust(j, i, map[i][j]));
                }else if(map[i][j] > 0){
                    sum += map[i][j];
                }
            }
        }
        
        upAir.offer(downAir.poll());
        upAirX = upAir.peek().x;
        upAirY = upAir.peek().y;
        downAirX = downAir.peek().x;
        downAirY = downAir.peek().y;

        
    
        for(; cc < T ; cc++){
            spread();
            BFS();
            BFSDOWN();
            upAir = new LinkedList<>(); upAir.offer(new MicroDust(upAirX, upAirY, map[upAirY][upAirY]));
            downAir = new LinkedList<>(); downAir.offer(new MicroDust(downAirX, downAirY, map[downAirY][downAirX]));
        }
        
            System.out.println(sum);
     
        
    }
    static void spread (){
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                if(map[i][j] > 0){
                    dust.offer(new MicroDust(j, i, map[i][j]));
                }
            }
        }
        while(!dust.isEmpty()){
            MicroDust m = dust.poll();
            int cnt = 0;
            if(m.val < 5) continue;
            int div = m.val / 5;
            for(int i = 0 ; i < 4 ; i++){
                int nx = m.x + dx[i];
                int ny = m.y + dy[i];

                if(nx >= 0 && nx < C && ny >= 0 && ny < R ){
                    if(map[ny][nx] != -1){
                       map[ny][nx] += div;
                       cnt ++; 
                    } 
                }
            }
            map[m.y][m.x] = map[m.y][m.x] - (div * cnt);
            
        }
        
    }

    static void BFS(){
  
        // 동 북 서 남
        int dir = 1; //북쪽에서 시작
         while(!upAir.isEmpty()){
            MicroDust m = upAir.poll();
            int nx = m.x + dx[dir];
            int ny = m.y + dy[dir];
            
            if(ny >= downAirY || nx >= C || nx < 0 || ny <0){
                dir = (dir + 3) % 4;
                upAir.offer(new MicroDust(m.x, m.y, 0));
                continue;
            }
            if(m.y >= R || m.x >= C || m.x < 0 || m.y < 0) continue;
            if(map[m.y][m.x] == -1){
                sum -= map[ny][nx];
                upAir.offer(new MicroDust(nx, ny, 0));
                continue;
            }
            
            if(map[ny][nx] == -1){
                return;
            }
            map[m.y][m.x] = map[ny][nx];
            map[ny][nx] = 0;
            upAir.offer(new MicroDust(nx, ny, 0));


        }
        
        
    }
    static void BFSDOWN (){
        
        int downDir = 3;
        while(!downAir.isEmpty()){
            
            MicroDust n = downAir.poll();
            
            int nx = n.x + dx[downDir];
            int ny = n.y + dy[downDir];
            if(ny >= R || nx >= C || nx < 0 || ny <downAirY){
                downDir = (downDir + 1) % 4;
                downAir.offer(new MicroDust(n.x, n.y, 0));
                continue;
            }
            if(n.y >= R || n.x >= C || n.x < 0 || n.y < 0) continue;
            if(map[n.y][n.x] == -1){
                sum -= map[ny][nx];
                downAir.offer(new MicroDust(nx, ny, 0));
                continue;
            }
            if(map[ny][nx] == -1){
                return;
            }
            map[n.y][n.x] = map[ny][nx];
            map[ny][nx] = 0;
            downAir.offer(new MicroDust(nx, ny, 0));
            


        }
    }
}
class MicroDust{
    int x;
    int y;
    int val;
    MicroDust(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}