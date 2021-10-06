import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16236 {
    static int rst = Integer.MAX_VALUE;
    static int N, map[][];
    static boolean isVisited[][];
    static ArrayList<Integer> fish  = new ArrayList<>();
    static Queue<Shark> shark  = new LinkedList<>();
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        

        Shark shark = new Shark(0, 0, 0, 0, 0); // 아기상어 초기화
        
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아기상어를 발견했다면?
                if (map[i][j] == 9) {
                    map[i][j] = 0; // 0 으로 바꿔준다.
                    shark = new Shark(j, i, 2, 0, 0); // 상어 시작지점 갱신
                }
            }
        }
        int answer = 0;
        // 먹을 수 있는 물고기가 없을 때 까지 반복
        while (true) {
            PriorityQueue<Fish> q = BFS(shark.x, shark.y, shark.lv, shark.exp, 0);
            if (q.isEmpty()) { // 더 이상 먹을 수 있는 물고기가 없을 경우
                System.out.println(answer); // 지금까지 움직였던 거리 출력
                return;
            }
            Fish d = q.poll(); // 제일 가까운 물고기
            
            map[d.y][d.x] = 0;
            int lv = shark.lv;
            int exp = shark.exp + 1;

            if (exp == shark.lv) { // 크기 만큼의 물고기를 먹었다면
                lv = shark.lv + 1; // 크기 1 증가
                exp = 0; // 경험치는 초기화
            }
            shark = new Shark(d.x, d.y, lv, exp, 0);
            answer += d.dst;
        }
        
        
    }
    // 제일 가까운 물고기를 Queue에 담아서 리턴
    static PriorityQueue<Fish> BFS(int x, int y, int lv, int exp, int cnt){
        isVisited = new boolean[N][N];
        Queue<Shark> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.offer(new Shark(x, y, lv, exp, cnt));
        PriorityQueue<Fish> pq = new PriorityQueue<>();

        while (!q.isEmpty()) {
            Shark d = q.poll();

            if (map[d.y][d.x] != 0 && map[d.y][d.x] < d.lv) {
                pq.offer(new Fish(d.x, d.y, map[d.y][d.x], d.cnt));
                continue;
            }

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !isVisited[ny][nx] && map[ny][nx] <= d.lv) {
                    isVisited[ny][nx] = true;
                    q.offer(new Shark(nx, ny, d.lv, d.exp, d.cnt + 1));
                }
            }
        }

        return pq;
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

class Fish implements Comparable<Fish>{
    int x;
    int y;
    int lv;
    int dst;
    Fish(int x, int y, int lv, int dst){
        this.x = x;
        this.y = y;
        this.lv = lv;
        this.dst = dst;
    }
    @Override
    public int compareTo(Fish o) {
        if (this.dst == o.dst) {
            if (this.y == o.y) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
        return this.dst - o.dst;
    }
}