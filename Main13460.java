import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460 {
    static boolean[][][][] isVisited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[][] map;
    static int N, M;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isVisited = new boolean[N][M][N][M];
        map = new char[N][M];
        int rx = 0, ry = 0,  bx = 0, by = 0; 
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'R'){
                    rx = j;
                    ry = i;
                }
                else if(map[i][j] == 'B'){
                    by = i;
                    bx = j;
                }
            }
        }
        


        System.out.println(BFS(rx, ry, bx, by));

    }
    static int BFS(int rx, int ry, int bx, int by){
        Queue<ball> q = new LinkedList<>();
        isVisited[ry][rx][by][bx] = true;
        q.offer(new ball(rx, ry, bx, by, 1));
        while(!q.isEmpty()){
            ball d = q.poll();

            if(d.cnt > 10) return -1;
            
            for(int i = 0 ; i < 4 ; i++){
                
                int RedX = d.rx + dx[i];
                int RedY = d.ry + dy[i];
                int BlueX = d.bx + dx[i];
                int BlueY = d.by + dy[i];

                boolean blueCanMove = true;
                //파란공 굴리기
            
                while(map[BlueY][BlueX] != '#'){
                    if(map[BlueY][BlueX] == 'O'){
                        blueCanMove = false;
                        break;
                    }
                    

                    BlueX += dx[i];
                    BlueY += dy[i];
                }
                BlueX -= dx[i];
                BlueY -= dy[i];

                if(!blueCanMove){
                    continue;
                }

                 //뻘건공 굴리기

                 while(map[RedY][RedX] != '#'){
                    if(map[RedY][RedX] == 'O'){
                        return d.cnt;
                    }
                    
                    RedX += dx[i];
                    RedY += dy[i];
                }
                RedX -= dx[i];
                RedY -= dy[i];

                //둘이 겹칠때 떼어내야댐
                if(BlueX == RedX && BlueY == RedY && map[RedY][RedX] != 'O' ){
                    if(map[RedY][RedX] == 0) continue;
                    int Rdst = Math.abs(d.ry - RedY) + Math.abs(d.rx - RedX);
                    int Bdst = Math.abs(d.by - BlueY) + Math.abs(d.bx - BlueX);

                    if(Rdst > Bdst){
                        RedX -= dx[i];
                        RedY -= dy[i];
                    }else{
                        BlueX -= dx[i];
                        BlueY -= dy[i];
                    }
                }


                if(!isVisited[RedY][RedX][BlueY][BlueX]){
                    isVisited[RedY][RedX][BlueY][BlueX] = true;
                    q.offer(new ball(RedX, RedY, BlueX, BlueY, d.cnt + 1));
                }
            }
        }


        return -1;
    }
}

class ball{
    int rx;
    int ry;
    int bx;
    int by;
    int cnt;
    ball(int rx, int ry, int bx, int by, int cnt){
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.cnt = cnt;
    }

}