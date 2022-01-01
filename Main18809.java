import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main18809 {
    static int N, M, G, R, ans = 0;
    static boolean[] check;
    static int[][] map;
    static boolean[][] isVisited;
    static ArrayList<Bae> ground = new ArrayList<>(); // 배양액을 뿌릴 수 있는 땅의 좌표를 담은 List
    static ArrayList<Bae> selected_liquid = new ArrayList<>();

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        
        // 0은 호수, 1은 배양액을 뿌릴 수 없는 땅, 2는 배양액을 뿌릴 수 있는 땅을 의미한다.
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    ground.add(new Bae(j, i, 'N', 0));
                }
            }
        }
        check = new boolean[ground.size()];
        // 1. 처음에는 배양액을 뿌릴 수 있는 땅 어디에 배양액을 둘지 선택한다. 
        select_liquid(0, 0, R, G);
        System.out.println(ans);

    }
    static void spread_Liquid() {
        Queue<Bae> liquid = new LinkedList<>();
        char[][] color_map = new char[N][M];
        int[][] clone_map = new int[N][M];
        int[][] time_map = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0 ; i < N ; i++) {
            clone_map[i] = map[i].clone();
        }
        for (Bae d : selected_liquid) {
            liquid.add(new Bae(d.x, d.y, d.color, 1));
            color_map[d.y][d.x] = d.color;
            time_map[d.y][d.x] = 1;
            isVisited[d.y][d.x] = true;
        }
        int cnt = 0;
        while (!liquid.isEmpty()) {
            int size = liquid.size();
            for (int a = 0 ; a < size ; a++) {
                Bae d = liquid.poll();
                if (color_map[d.y][d.x] == 'F') continue; // 이미 꽃이 된 곳은 배양액을 전달할 수 없음
                for (int i = 0 ; i < 4 ; i++) {
                    int nx = d.x + dx[i];
                    int ny = d.y + dy[i];
                    if (nx >= 0 && nx < M && ny >= 0 && ny < N && clone_map[ny][nx] != 0 && color_map[ny][nx] != 'F') {
                        
                        if (!isVisited[ny][nx]) {
                            isVisited[ny][nx] = true;
                            color_map[ny][nx] = d.color;
                            time_map[ny][nx] = d.time + 1;
                            liquid.offer(new Bae(nx, ny, d.color, d.time + 1));
                            continue;
                        } 
                        if (isVisited[ny][nx] && color_map[ny][nx] != d.color && d.time + 1 == time_map[ny][nx]) {
                            
                            cnt++;
                            isVisited[ny][nx] = true;
                            color_map[ny][nx] = 'F';
                            time_map[ny][nx] = d.time + 1;
                            clone_map[ny][nx] = 0; // 꽃이 생성된 곳은 바꿔준다.
                            continue;
                        }
                    }
                }
            }
        }
        ans = Math.max(ans, cnt);
         
    }
    static void select_liquid(int start, int k, int r, int g) {
        if (k == R + G) {
            spread_Liquid();
            return;
        }

        for (int i = start ; i < ground.size() ; i++) {
            if (!check[i]) {
                if (r > 0) {
                    check[i] = true;
                    selected_liquid.add(new Bae(ground.get(i).x, ground.get(i).y, 'R', 0));
                    select_liquid(i + 1, k + 1, r-1, g);
                    selected_liquid.remove(selected_liquid.size() - 1);
                    check[i] = false;
                }
                if (g > 0) {
                    check[i] = true;
                    selected_liquid.add(new Bae(ground.get(i).x, ground.get(i).y, 'G', 0));
                    select_liquid(i + 1, k + 1, r, g - 1);
                    selected_liquid.remove(selected_liquid.size() - 1);
                    check[i] = false;
                }
                
            }
        }
    }    
}
class Bae {
    int x;
    int y;
    char color;
    int time;
    Bae(int x, int y, char color, int time) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.time = time;
    }
}