import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main3190 {
    static int N, K, map[][];
    static HashMap<Integer, String> dir = new HashMap<>(); // 뱀의 시간에 따른 방향정보 저장
    static Deque<Dummy> dummy = new LinkedList<>(); //뱀 몽뚱이 생성
    static int[] dy = {1, 0, -1, 0}; //북동남서
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for(int i = 0 ; i < K ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        for(int i = 0 ; i <L ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            dir.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }
        dummy.addFirst(new Dummy(1, 1)); // 뱀의 초기 상태
        Solution();

    }
    static void Solution(){
        //-------<Guide>--------------------------------------------------
        // 1. 뱀의 경과 시간에 따른 이동경로 지정
        // 2. 뱀이 자기 몸에 닿거나, 벽에 닿으면 게임 종료.
        // 3. 뱀의 이동 (이동 시 꼬리 끝자락을 짤라주어야함. 단, 사과를 먹으면 유지)
        //----------------------------------------------------------------


        int second = 0; // Dummy게임의 시간. 이동할 때 마다 1초씩 증가.
        int direction = 1; // 뱀의 초기방향은 동쪽임.
    
        while(true){
            // 만약 해시맵에 저장되어있는 시간대값에 값이 저장되어있는경우.
            // 방향값이 D냐 L이냐에 따라 direction의 값은 달라진다.
            if(!(dir.get(second) == null)){
                direction = dir.get(second).equals("D") ? (direction + 3) % 4 : (direction + 1) % 4;
                // 삼항연산자 : D면 오른쪽으로 회전, 아니면 왼쪽으로 회전한다.
            }
            Dummy head = dummy.peekFirst(); // 뱀의 머리
            Dummy tail = dummy.peekLast(); // 뱀의 꼬리
            // 뱀의 진행방향 설정
            int nx = head.x + dx[direction];
            int ny = head.y + dy[direction];
            
            // Game Over : 벽에 닿을 때
            if(nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1){
                System.out.println(second + 1);
                
                return;
            }
            // Game Over : 뱀의 진행방향이 뱀의 몸의 일부분일 때
            if(map[ny][nx] == 9){
                System.out.println(second + 1);
                return;
            }
            //사과를 만나면? 
            if(map[ny][nx] == 1){
                map[ny][nx] = 9;
                dummy.addFirst(new Dummy(nx, ny));
            }else{
                // 이동 
                 map[ny][nx] = 9;
                 dummy.addFirst(new Dummy(nx, ny));
                 map[tail.y][tail.x] = 0;
                 dummy.pollLast();
            }


           
            
            second++;
        }
        

    }

}
class Dummy{
    int x;
    int y;
    Dummy(int x, int y){
        this.x = x;
        this.y = y;
    }
}
