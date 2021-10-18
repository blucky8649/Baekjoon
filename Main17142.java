import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main17142 {
    static int N, M, answer = Integer.MAX_VALUE, max = -1, empty = 0;
    static char[][] map;
    static boolean[][] isVisited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static Stack<Virus2> stack = new Stack<>();
    static ArrayList<Virus2> vList = new ArrayList<>();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        
        
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < N ; j++) { 
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    map[i][j] = '-';
                } else if (num  == 2) {
                    map[i][j] = '*';
                    vList.add(new Virus2(j, i, 0));
                } else {
                    empty ++;
                    map[i][j] = '@';
                }
            }
        }
        if (empty == 0) {
            System.out.println(0);
            return;
        }
        DFS(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    static void DFS(int start, int K) {
        if (K == M) {
            spreadVirus(empty);
        }

        for (int i = start ; i < vList.size() ; i++) {
            stack.push(vList.get(i));
            DFS(i + 1, K + 1);
            stack.pop();
        }
    }

    static void spreadVirus(int empty) {
        isVisited = new boolean[N][N]; 
        Queue<Virus2> q= new LinkedList<>();
        for (Virus2 v : stack) {
            q.offer(v);
            isVisited[v.y][v.x] = true;  
        }

        while (!q.isEmpty()) {
            Virus2 d = q.poll();
            max = Math.max(max, d.cnt);
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || isVisited[ny][nx] || map[ny][nx] == '-') continue;
                if (map[ny][nx] == '@') {
                    empty--;
                }

                if (empty == 0) {
                    answer = Math.min(answer, d.cnt + 1);
                    return;
                }

                isVisited[ny][nx] = true;
                q.offer(new Virus2(nx, ny, d.cnt + 1));
            }
            
        }
    }
}
class Virus2 {
    int x;
    int y;
    int cnt;
    Virus2(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}