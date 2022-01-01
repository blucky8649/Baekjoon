import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Chesss {
    int num;
    int x;
    int y;
    int dir;
    Chesss(int num, int x, int y, int dir) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
public class Main17837 {
    static int n, k;
    static Chesss[] chess;
    static int[][] map;
    static ArrayList<Integer>[][] arr;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0}; 

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        arr = new ArrayList[n][n];
        chess = new Chesss[k + 1];
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0 ; i < n  ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1 ; i <= k ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            chess[i] = new Chesss(i, x, y, dir);
            arr[y][x].add(i);
        }
        // 1000 초 까지
        for (int i = 1 ; i <= 1000 ; i++) {
            // 1. 1번 말 부터 순서대로 이동한다.
            int ans = move_chess(i);

            if (ans > 0) {
                System.out.println(ans);
                return;
            }
        }
        System.out.println(-1);
        
    }
    static int move_chess(int i) {
        ArrayList<Integer> sub;
        for (int a = 1 ; a < chess.length ; a++) {
            Chesss d = chess[a];
            int nx = d.x + dx[d.dir];
            int ny = d.y + dy[d.dir];
            // 파란색이거나, 체스판을 벗어나는 경우
            if ((nx < 0 || nx >= n || ny < 0 || ny >= n) || map[ny][nx] == 2) {
                switch (d.dir) {
                    case 0 : d.dir = 1 ; break;
                    case 1 : d.dir = 0 ; break;
                    case 2 : d.dir = 3 ; break;
                    case 3 : d.dir = 2 ; break;
                }
                nx = d.x + dx[d.dir];
                ny = d.y + dy[d.dir];
                chess[d.num].dir = d.dir;
                sub = search_map(d.x, d.y, d.num);
                // 만약 방향을 바꾸고 이동했는데 그위치도 파란색이거나 범위를 벗어났다면 제자리에서 방향만 바꿈
                if ((nx < 0 || nx >= n || ny < 0 || ny >= n) || map[ny][nx] == 2) {
                    for (int n : sub) {
                        arr[d.y][d.x].add(n);
                        chess[n].dir = chess[n].dir;
                    }
                } else {
                    if (map[ny][nx] == 1) {
                        Collections.reverse(sub);
                    }
                    // 아니라면 이동한다
                    for (int n : sub) {
                        arr[ny][nx].add(n);
                        chess[n].x = nx;
                        chess[n].y = ny;
                        chess[n].dir = chess[n].dir;
                        if (arr[ny][nx].size() >= 4) {
                            return i;
                        }
                    }
                }
                
                // 뻘건색일 경우 : 순서를 반대로 바꾼다.
            } else if (map[ny][nx] == 1) {
                sub = search_map(d.x, d.y, d.num);
                Collections.reverse(sub);
                for (int n : sub) {
                    arr[ny][nx].add(n);
                    chess[n].x = nx;
                    chess[n].y = ny;
                    chess[n].dir = chess[n].dir;
                    if (arr[ny][nx].size() >= 4) {
                        return i;
                    }
                }
                // 흰색일 경우 : 그냥 이동
            } else {
                sub = search_map(d.x, d.y, d.num);
                for (int n : sub) {
                    arr[ny][nx].add(n);
                    chess[n].x = nx;
                    chess[n].y = ny;
                    chess[n].dir = chess[n].dir;
                    if (arr[ny][nx].size() >= 4) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    static ArrayList<Integer> search_map(int x, int y, int num) {
        ArrayList<Integer> sub = new ArrayList<>();
        int idx= 0;
        for (int i = 0 ; i < arr[y][x].size() ; i++) {
            if (arr[y][x].get(i)==(num)) {
                idx =  i;
                break;
            }
        }
        for (int i = idx ; i < arr[y][x].size() ; i++) {
            sub.add(arr[y][x].get(i));
            arr[y][x].remove(i);
            i--;
        }
        return sub;
    }
    static void print_matrix() {
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
