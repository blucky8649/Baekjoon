import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460_2 {
    static int n, m;
    static char[][] map;
    static boolean[][][][] isVisited; //Ry, Rx, By, Bx

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map= new char[n][m];
        isVisited = new boolean[n][m][n][m];
        int start_Rx = 0;
        int start_Ry = 0;
        int start_Bx = 0;
        int start_By = 0;

        for (int i = 0 ; i < n ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < m ; j++) {
                map[i][j] =str.charAt(j);
                if (map[i][j] == 'R') {
                    start_Rx = j;
                    start_Ry = i;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    start_Bx = j;
                    start_By = i;
                    map[i][j] = '.';
                } 
            }
        }
        int ans = move_marble(start_Rx, start_Ry, start_Bx, start_By);
        System.out.println(ans > 10 ? -1 : ans);
    }
    static int move_marble(int Rx, int Ry, int Bx, int By) {
        Queue<Marb> q= new LinkedList<>();
        q.offer(new Marb(Rx, Ry, Bx, By, 1)); //cnt 는 1로 시작
        isVisited[Ry][Rx][By][Bx] = true;

        while (!q.isEmpty()) {
            Marb d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int next_Rx = d.Rx;
                int next_Ry = d.Ry;
                int next_Bx = d.Bx;
                int next_By = d.By;

                boolean red_in_hole = false;
                boolean blue_in_hole = false;
                // 빨간 구슬 굴리기
                while (map[next_Ry + dy[i]][next_Rx + dx[i]] != '#') {
                    next_Rx += dx[i];
                    next_Ry += dy[i];
                    if (map[next_Ry][next_Rx] == 'O') {
                        red_in_hole = true;
                        break; 
                    }
                }
                // 퍼런 구슬 굴리기
                while (map[next_By + dy[i]][next_Bx + dx[i]] != '#') {
                    next_Bx += dx[i];
                    next_By += dy[i];
                    if (map[next_By][next_Bx] == 'O') {
                        blue_in_hole = true;
                        break; 
                    }
                }
                // 파란색 공이 들어갔을 경우엔 게임이 종료지만 다른 성공 케이스도 있으므로 일단 대기
                if (blue_in_hole) {
                    continue;
                }
                // 빨간공 "만" 들어가야 성공임
                if (red_in_hole && !blue_in_hole) {
                    return d.cnt;
                }

                // 만약 둘다 위치가 같은 경우 : 뒤늦게 가는 구슬을뒤로 빼줘야함
                if (next_Bx == next_Rx && next_By == next_Ry) {
                    switch (i) {
                        case 0 :
                            // 시작할 때 y구슬이 더 위에 있었다면
                            if (d.Ry < d.By) next_By -= dy[i];
                            else next_Ry -= dy[i];
                            break;
                        case 1 :
                            if (d.Rx < d.Bx) next_Rx -= dx[i];
                            else next_Bx -= dx[i];
                            break;
                        case 2 :
                            if (d.Ry < d.By) next_Ry -= dy[i];
                            else next_By -= dy[i]; 
                            break;
                        case 3 :
                            if (d.Rx < d.Bx) next_Bx -= dx[i];
                            else next_Rx -= dx[i];
                            break;
                    }
                }

                if (!isVisited[next_Ry][next_Rx][next_By][next_Bx]) {
                    isVisited[next_Ry][next_Rx][next_By][next_Bx] = true;
                    q.offer(new Marb(next_Rx, next_Ry, next_Bx, next_By, d.cnt +1));
                }

            }
        }
        return -1;
    }
}
class Marb {
    int Rx;
    int Ry;
    int Bx;
    int By;
    int cnt;
    Marb (int Rx, int Ry, int Bx, int By, int cnt) {
        this.Rx = Rx;
        this.Ry = Ry;
        this.Bx = Bx;
        this.By = By;
        this.cnt = cnt;
    }
}