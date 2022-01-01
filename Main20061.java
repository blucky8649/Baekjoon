import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main20061 {
    static int[][] map = new int[10][10];
    static int ans = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // t 가 1이면 1 * 1(x, y), 2면 1 * 2(x, y), (x, y+1), 3이면 2 * 1(x,y), (x+1, y)
            // 1. 빨간색 칸에 블럭을 놓는다.
            add_bloc(t, y, x);
            // 2. 한줄 꽉차서 없어질 행이 있는지 탐색하고 땡김
            check_line();
            // 3. (4, 5 번 줄에 블럭이 있으면 끝 줄 제거)
            check_safezone();
        }
        System.out.println(ans);
        System.out.println(count_bloc());
    }
    static void check_safezone() {
        // 4, 5 번 라인에 블럭이 있는지 확인하는 메서드.
        // 5번 라인에 하나라도 있으면 땡기고 땡겨도 하나 더 있으면 또땡김
        // 퍼런칸
        for (int i = 0 ; i < 2 ; i++) {
            if (map[0][5] == 1 || map[1][5] == 1 || map[2][5] == 1 || map[3][5] == 1) {
                pull(1, 9);
            }
        }
        // 초록칸
        for (int i = 0 ; i < 2 ; i++) {
            if (map[5][0] == 1 || map[5][1] == 1 || map[5][2] == 1 || map[5][3] == 1) {
                pull(2, 9);
            }
        }

    }
    static void check_line() {
        // 퍼런칸
        for (int i = 6 ; i <= 9 ; i++) {
            // 한 줄이 꽉차 있으면
            if (map[0][i] == 1 && map[1][i] == 1 && map[2][i] == 1 && map[3][i] == 1) {
                // 땡긴다.
                pull(1, i);
                ans++;
            }
        }
        // 초록칸
        for (int i = 6 ; i <= 9 ; i++) {
            // 한 줄이 꽉차 있으면
            if (map[i][0] == 1 && map[i][1] == 1 && map[i][2] == 1 && map[i][3] == 1) {
                // 땡긴다.
                pull(2, i);
                ans++;
            }
        }

        
    }
    static void pull(int color, int idx) {
        // 1이면 파란색, 2면 초록색
        if (color == 1) {
            for (int i = idx ; i >= 4 ; i--) {
                map[0][i] = map[0][i - 1];
                map[1][i] = map[1][i - 1];
                map[2][i] = map[2][i - 1];
                map[3][i] = map[3][i - 1];
            }
                
        } else {
            for (int i = idx ; i >= 4 ; i--) {
                map[i][0] = map[i - 1][0];
                map[i][1] = map[i - 1][1];
                map[i][2] = map[i - 1][2];
                map[i][3] = map[i - 1][3];
            }  
        }
        
        
    }
    static void add_bloc(int t, int x, int y) {
        switch (t) {
            case 1 : 
                for (int i = 0 ; i <= 9 ; i++) {
                    // 파란색 칸 (y는 그대로고 x축만 움직임)
                    if (i == 9) {
                        map[y][i] = 1;
                        break;
                    }
                    if (map[y][i + 1] != 0) {
                        map[y][i] = 1;
                        break;
                    }
                }
                for (int i = 0 ; i <= 9 ; i++) {
                    // 초록색 칸 (x는 그대로고 y축만 움직임)
                    if (i == 9) {
                        map[i][x] = 1;
                        break;
                    }
                    if (map[i + 1][x] != 0) {
                        map[i][x] = 1;
                        break;
                    }
                }
                break;
            case 3 :
                // ㅣ 모양
                for (int i = 0 ; i <= 9 ; i++) {
                    // 파란색 칸
                    if (i == 9) {
                        map[y][i] = 1;
                        map[y+1][i] = 1;
                        break;
                    }
                    if (map[y][i + 1] != 0 || map[y + 1][i + 1] != 0) {
                        map[y][i] = 1;
                        map[y+1][i] = 1;
                        break;
                    }
                }
                for (int i = 0 ; i <= 9 ; i++) {
                    // 초록색 칸
                    if (i == 9) {
                        map[i][x] = 1;
                        map[i - 1][x] = 1;
                        break;
                    }
                    if (map[i + 1][x] != 0) {
                        map[i][x] = 1;
                        map[i - 1][x] = 1;
                        break;
                    }
                }
                 break;
            case 2 : 
                // ㅡ 모양
                for (int i = 0 ; i <= 9 ; i++) {
                    // 파란색 칸
                    if (i == 9) {
                        map[y][i] = 1;
                        map[y][i-1] = 1;
                        break;
                    }
                    if (map[y][i+1] != 0) {
                        map[y][i] = 1;
                        map[y][i-1] = 1;
                        break;
                    }
                }
                for (int i = 0 ; i <= 9 ; i++) {
                    if (i == 9) {
                        map[i][x] = 1;
                        map[i][x + 1] = 1;
                        break;
                    }
                    // 초록색 칸
                    if (map[i + 1][x] != 0 || map[i + 1][x + 1] != 0) {
                        map[i][x] = 1;
                        map[i][x + 1] = 1;
                        break;
                    }
                }
                break;
        }
    }
    static void print_matrix() {
        for (int i = 0 ; i < 10 ; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static int count_bloc() {
        int cnt = 0;
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 10 ; j++) {
                cnt += map[i][j];    
            }
        }
        return cnt;
    }
}
class Lego {
    int x;
    int y;
    Lego (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
