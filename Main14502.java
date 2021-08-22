import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main14502 {
    /*
    * 1. DFS로 벽을 세운다.(백트래킹 완전탐색)
    * 2. 벽을 세개 세웠으면 BFS로 바이러스를 뿌린다.
    * 3. 뿌리고 난 뒤의 안전영역의 갯수를 비교하여 출력
    */
    static int N, M, map[][], virusMap[][], result = Integer.MIN_VALUE;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        virusMap = new int[N][M];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
              
            }
        }
       
        DFS(0);
        System.out.println(result);
    }
    static void DFS(int cnt){
        // 벽 세우기

        if(cnt == 3){
            BFS();
            return;
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1; // 벽을 세운다.
                    DFS(cnt + 1);
                    map[i][j] = 0; // 백트래킹
                }
            }
        }
    }
    static void BFS(){
        int sum = 0;
        
        virusMap = new int [N][M];
        for(int i = 0 ; i < map.length ; i++){
            virusMap[i] = map[i].clone();
        }
        Queue<Virus> q = new LinkedList<>();
        for(int i =0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(virusMap[i][j] == 2){
                    q.offer(new Virus(j, i));
                }
            }
        }

        while(!q.isEmpty()){
            Virus d = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if(nx >= 0 && nx < M && ny >= 0 && ny < N){
                    if(virusMap[ny][nx] == 0){
                        virusMap[ny][nx] = 2;
                        q.offer(new Virus(nx, ny));
                    }
                }
                
                
            }

        }

        for(int i =0 ; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                if(virusMap[i][j] == 0){
                    sum ++;
                }
            }
        }
        result = Math.max(result, sum);

    }

}
class Virus {
    int x;
    int y;
    Virus(int x, int y){
        this.x = x;
        this.y = y;
    }
}
