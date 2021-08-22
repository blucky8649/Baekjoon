import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main15683 {

    // 1. 맵을 깔고 CCTV를 Queue 에 저장한다.
    // 2. 첫번째 함수 (process) : CCTV 방향의 경우의 수 생성
    // 3, 두번째 함수 (see) : 첫번째 함수에서 받아온 방향정보를 이용하여 보는 방향에 '=1'을 찍는다.
    static int N, M, map[][], result = Integer.MAX_VALUE;
    static Queue<CCTV> q = new LinkedList<>();
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][][] cctv_dir = {
                {{0}}, 
                {{0}, {1}, {2}, {3}}, //1번 cctv 
                {{1, 3}, {0, 2}},       //2번 cctv 
                {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, //3번 cctv
                {{0,1,3}, {0,1,2}, {1, 2, 3}, {2, 3, 0}}, //2번 cctv 
                {{0, 1, 2, 3}} //5번 ttcv

    };             

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int remain = N * M;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5){ // CCTV
                    q.offer(new CCTV(map[i][j], j, i)); // CCTV정보 Queue에 저장
                    remain--; // 전체에서 빼준다.
                }
                if(map[i][j] == 6) remain--; // 벽
            }
        }
        
        process(remain, map);
        System.out.println(result);

    }
    static void process(int remain, int[][] map){
            
        if(q.isEmpty()){ // Queue 가 비었으면 최소 사각지대 리턴
            result = Math.min(result, remain);
            
            return;
        }

        int [][] virtual_map = new int[N][M];
        copy(virtual_map, map);
            CCTV d = q.poll();
            for(int j = 0 ; j < cctv_dir[d.type].length ; j++){
                int check = 0;
                for(int k = 0 ; k < cctv_dir[d.type][j].length ; k++){
                    int dir = cctv_dir[d.type][j][k];
                    check += see(d.x, d.y, dir, virtual_map);
                }
                process(remain - check, virtual_map);
                copy(virtual_map, map);
                
            }
            q.offer(new CCTV(d.type, d.x, d.y)); //이미 썼던 수는 제일 뒤로 보내고 다음 수를 꺼내야함.
    }

    static int see (int x, int y, int dir, int[][] map){
        int cnt = 0; //감시된 구역의 갯수를 세기 위함. : 전체 구역 - CCTV 갯수 - 벽 개수 - 감시된 구역 = 사각지대

        while(true){
            //한쪽 방향으로만 벽이나 맵끝까지 가야함

            x += dx[dir];
            y += dy[dir];

            if(x < 0 || y < 0 || x >= M || y >= N || map[y][x] == 6){
                //벽을 만나거나 맵 밖에 나가려고 하거든
                return cnt; // 가시영역들을 리턴
            }
            if(map[y][x] >= 1 && map[y][x] <= 5 || map[y][x] == -1){
                // CCTV를 만났거나, 이미 다른 CCTV가 바라봤던 영역이 나왔을 때.
                continue ; //건너뛰고 찍는다.
            }
            map[y][x] = -1;
            cnt++;
        }
    }

    private static void copy(int[][] newMap, int[][] map) {
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        
    }


}
class CCTV{
    int type;
    int x;
    int y;
    CCTV(int type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
