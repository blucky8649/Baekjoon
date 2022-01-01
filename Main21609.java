import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main21609 {
    static int N, M, ans = 0;
    static int[][] map;
    static boolean[][] isVisited;
    static PriorityQueue<Momo> arr;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 맵 한 변의 크기
        M = Integer.parseInt(st.nextToken()); // 색상의 갯수
        map = new int[N][N];
        isVisited = new boolean[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            arr = new PriorityQueue<>();
            isVisited = new boolean[N][N];
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < N ; j++) {
                    if (!isVisited[i][j] && map[i][j] > 0) {
                        Momo d = search_bloc(j, i, map[i][j]);
                        if (d.sum >=2) {
                            arr.add(d);
                        }
                    }
                }
            }
            if (arr.isEmpty()) break;
            remove(arr.peek()); // 우선순위 높은 칸 삭제.
            ans += (int)Math.pow(arr.peek().bloc.size(), 2);
            gravity();
            map = rotate();
            gravity();
            
        }
        System.out.println(ans);
    }
    
    static void gravity() {
        for (int x = 0 ; x < N ; x++) {
            for (int i = 0 ; i < N ; i++) {
                for (int y = N - 1 ; y > 0 ; y--) {
                    if (map[y][x] == -2 && map[y][x] != -1) {
                        if (map[y - 1][x] == -1) {
                            map[y][x] = -2;
                        }else {
                            map[y][x] = map[y-1][x];
                            map[y-1][x] = -2;
                        }
                    }
                }
            }
            
        }
    }
    static void remove(Momo m) {
        for (Block d : m.bloc) {
            map[d.y][d.x] = -2; // 삭제처리
        }
    }
    static Momo search_bloc(int x, int y, int block_color) {
        PriorityQueue<Block> list = new PriorityQueue<>(); 
        Queue<Block> q = new LinkedList<>();
        isVisited[y][x] = true;
        q.offer(new Block(map[y][x], x, y));
        list.offer(new Block(map[y][x], x, y));
        int cnt = 1;
        int rainbow = 0;

        while (!q.isEmpty()) {
            Block d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !isVisited[ny][nx] && 
                (map[ny][nx] == block_color || map[ny][nx] == 0)) {
                    q.offer(new Block(map[ny][nx], nx, ny));
                    list.add(new Block(map[ny][nx], nx, ny)); // 모든 좌표를 리스트에 담는다.
                    cnt++;
                    isVisited[ny][nx] = true;
                    if (map[ny][nx] == 0) {
                        rainbow++;
                    }
                }
            }
        }
        remove_isVisited();
        return new Momo(cnt, rainbow, list);
    }
    static int[][] rotate() {
        int result[][] = new int[N][N];
        for (int i = 0 ; i < N; i++) {
            for (int j = 0 ; j < N ; j++) {
                result[N - j - 1][i] = map[i][j];
            }
        }
        return result;
    }
    static void remove_isVisited() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j]== 0 && isVisited[i][j]) {
                    isVisited[i][j] = false;
                }
            }
        }
    }
    static void print_matrix() {
        for (int i = 0 ; i < N ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
class Momo implements Comparable<Momo>{
    int sum; // 총 블럭의 갯수
    int rainbow; // 무지개 블럭의 갯수
    PriorityQueue<Block> bloc = new PriorityQueue<>();
    Momo(int sum, int rainbow, PriorityQueue<Block> b) {
        this.sum = sum;
        this.rainbow = rainbow;
        this.bloc = b;
    }
    @Override
    public int compareTo(Momo o) {
        if (this.sum == o.sum) {
            if (this.rainbow == o.rainbow && !(this.bloc.isEmpty() && o.bloc.isEmpty())) {
                if (this.bloc.peek().y == o.bloc.peek().y) {
                    return o.bloc.peek().x - this.bloc.peek().x;
                }
                return o.bloc.peek().y - this.bloc.peek().y;
            }
            return o.rainbow - this.rainbow;
        }
        return o.sum - this.sum;
    }
}
class Block implements Comparable<Block>{
    int color;
    int x;
    int y;
    Block (int color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    @Override
    public int compareTo(Block o) {
        if (this.color == o.color) {
            if (this.y == o.y) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
        return o.color - this.color;
    }
}