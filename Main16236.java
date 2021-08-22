import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16236 {
    static int rst = Integer.MAX_VALUE;
    static int N, map[][];
    static ArrayList<Integer> fish  = new ArrayList<>();
    static Queue<Shark> shark  = new LinkedList<>();
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 6){
                    fish.add(map[i][j]);
                }else if(map[i][j] == 9){
                    shark.offer(new Shark(j, i, 2, 0, 0)); // lv.2 상어 생성
                }
            }
        }
        Collections.sort(fish);

        BFS();
        System.out.println(rst);
        
    }
    static void BFS(){
        
        while(!shark.isEmpty()){
            
            Shark d = shark.poll();
            int exp = d.exp;
            int level = d.lv;
            if(fish.isEmpty() || level <= fish.get(0)){
                rst = Math.min(rst, d.cnt);
                
                return;
            }

            if(level == exp){
                exp = 0 ;
                level ++ ;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny <N){
                    if(map[ny][nx] <= d.lv){
                        if(map[ny][nx] < d.lv && map[ny][nx] >0){
                            map[ny][nx] = 0;
                            shark.offer(new Shark(nx, ny, level, exp + 1, d.cnt +1));
                            fish.remove(0);
                        }else if(map[ny][nx] == d.lv || map[ny][nx] == 0){
                            shark.offer(new Shark(nx, ny, level, exp, d.cnt + 1));
                        }
                    }
                }
            }


        }


        
    }
}
class Shark{
    int x;
    int y;
    int lv;
    int exp;
    int cnt;
    Shark(int x, int y, int lv, int exp, int cnt){
        this.x = x;
        this.y = y;
        this.lv = lv;
        this.exp = exp;
        this.cnt = cnt;
    }
}