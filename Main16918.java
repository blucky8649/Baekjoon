import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bomb{
    int x;
    int y;
    int sec;
    Bomb(int x, int y, int sec){
        this.x = x;
        this.y = y;
        this.sec = sec;
    }
}
public class Main16918 {
    static int N, M, S;
    static int[][] map;
    static int ss = 0;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        map= new int[N][M];
        Queue<Bomb> q = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                int cr = (str.charAt(j)); 
                if(cr == 'O'){
                    map[i][j] = 1;
                    q.offer(new Bomb(j, i, 1));
                }
                if(cr == '.'){
                    map[i][j] = 0;
                };
            }
        }
        for(int i = 0 ; i < N ; i++){
            System.out.println(Arrays.toString(map[i]));
        }


        for(int sec = 2 ; sec <= S ; sec++){
            switch(sec % 2){
                //폭탄 깔기
                case 0 :
                    for(int i = 0 ; i < N ; i++){
                        for(int j = 0 ; j < M ; j++){
                            if(map[i][j]== 0){
                                map[i][j] = sec;
                                q.offer(new Bomb(j, i, sec));
                            }
                        }
                    }
                    
                    for(int i = 0 ; i < N ; i++){
                        System.out.println(Arrays.toString(map[i]));
                    }
                    break;
                //폭탄 터뜨리기  
                case 1 : 
                    for(int i = 0 ; i < N ; i++){
                        for(int j = 0 ; j < M ; j++){
                        Bomb d = q.poll();
                        
                        if(d.sec <= sec - 2){
                            //상하좌우 폭
                            for(int z = 0 ; z < 4;  z++){
                               int nx = d.x + dx[z];
                               int ny = d.y + dy[z];
                               if(nx >= 0 && nx < M && ny >= 0 && ny < N){
                                    map[ny][nx] = 0;
                              }
                            
                           }
                            map[d.y][d.x] = 0;
                        }
                        
                     }
                 }
                    System.out.println(q.size());
                    for(int i = 0 ; i < N ; i++){
                        System.out.println(Arrays.toString(map[i]));
                    }
            }
        }
        
    }
    
}
