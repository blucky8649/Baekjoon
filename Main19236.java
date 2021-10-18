import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main19236 {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int ans = 0;
    static Fish19236[][] map = new Fish19236[4][4];
    static TreeMap<Integer, Shark19236> fish = new TreeMap<>(); // 물고기 정보
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        for (int i = 0 ; i < 4 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < 4 ; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] =  new Fish19236(num, dir);
                fish.put(num, new Shark19236(j, i, dir));
            }
        }
        // 1. Shark 가 (0, 0) 칸에 있는 Fish를 먹고 방향정보를 얻는다.
        int dir = map[0][0].dir;
        fish.remove(map[0][0].num);
        int eat = map[0][0].num;
        map[0][0] = new Fish19236(0, 0); // 잡아먹힘
        move_shark(0, 0, dir, eat); // 상어 이동
        
        System.out.println(ans);
         
    }

    // x, y는 상어 위치에 관한 매개변수임
    static void move_fish(int x, int y) {
        String sharkstr = x + " " + y;
        for (int key : fish.keySet()) {
            Shark19236 d = fish.get(key);
            for (int i = 0 ; i <= 7 ; i++) {
                // 이동할 수 없으면 시계 반대방향으로 돌면서 탐색하기 위함
                int ndir = (d.dir + i) % 8;
                int nx = d.x + dx[ndir];
                int ny = d.y + dy[ndir];
                String goStr = nx + " " + ny;
                // 이동할 수 있는 조건 : 맵 밖에 나가면 안되고, 이동방향에 상어가 있으면 안된다.
                if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !sharkstr.equals(goStr)) {
                    // 이동하려는 칸에 물고기가 있을 때
                    if (map[ny][nx].num != 0) {
                        int fishDir = map[ny][nx].dir;
                        int fishnum = map[ny][nx].num;
                        // 위치를 바꾼다.
                        fish.put(key, new Shark19236(nx, ny, ndir));
                        fish.put(fishnum, new Shark19236(d.x, d.y, fishDir));
                        
                        map[ny][nx] = new Fish19236(key, ndir);
                        map[d.y][d.x] = new Fish19236(fishnum, fishDir);
    
                    } else {
                        // 가려는 곳이 비어있으면 간다.
                        fish.put(key, new Shark19236(nx, ny, ndir));
                        map[ny][nx] = new Fish19236(key, ndir);
                        map[d.y][d.x] = new Fish19236(0, 0);
                    }
                    break; // 한번 갔으면 종료.
                }
            } 
        }
        
    }
    static void move_shark(int x, int y, int dir, int sum) {
        ans = Math.max(ans, sum);
        // 배열, Map 복사

        Fish19236[][] tmpMap = new Fish19236[4][4];
        for (int i = 0 ; i < 4 ; i++) {
            tmpMap[i] = map[i].clone();
        }
        TreeMap<Integer, Shark19236> tmpFish = new TreeMap<>(fish);
        
        // 물고기 이동 시킴
        move_fish(x, y);
        
        for (int i = 1 ; i <= 3 ; i++) {
            int nx = x + (i * dx[dir]);
            int ny = y + (i * dy[dir]);
            
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[ny][nx].num != 0) {
                int fishDir = map[ny][nx].dir;
                int fishnum = map[ny][nx].num;
                map[ny][nx] = new Fish19236(0, 0);
                fish.remove(fishnum);

                move_shark(nx, ny, fishDir, sum + fishnum);
                map[ny][nx] = new Fish19236(fishnum, fishDir);
                fish.put(fishnum, new Shark19236(nx, ny, fishDir)); // 백트래킹
            }
        }
        // 맵 상태 되돌리기
        for (int i = 0 ; i < 4 ; i++) {
            map[i] = tmpMap[i].clone();
        }
        fish = new TreeMap<>(tmpFish);
    }
    
}
class Fish19236 {
    int num;
    int dir;
    Fish19236 (int num, int dir) {
        this.num = num;
        this.dir = dir;
    }
}



class Shark19236 {
    int x;
    int y;
    int dir;
    Shark19236 (int x, int y, int dir) {
        this.x =x;
        this.y =y;
        this.dir =dir;
    }
}