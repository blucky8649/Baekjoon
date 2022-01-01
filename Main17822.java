import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17822 {
    static int n, m, t;
    static int[][] circle;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); // 원의 개수
        m = Integer.parseInt(st.nextToken()); // 정수의 개수
        t = Integer.parseInt(st.nextToken()); // Testcase
        
        circle = new int[n][m];

        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < m ; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < t ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 배수
            int d = Integer.parseInt(st.nextToken()); // 방향 : 0은 시게, 1은 반시계
            int k = Integer.parseInt(st.nextToken()); // 회전 횟수
            
            // 1. 조건에 맞는 원판을 회전시킨다.
            rotate_circle(x, d, k);
            // 2. 인접한 숫자를 찾고 없앤다.
            search_adj_remove();
        }
        System.out.println(sum());
    }
    static void search_adj_remove() {
        ArrayList<Score> arr = new ArrayList<>();
        // 원 안에 있는 인접한 수 부터 비교한 뒤 좌표를 리스트에 담고 한번에 없앤다.
        // 1. 원 안에 있는 수 부터 비교
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (circle[i][j] != 0 && circle[i][j] == circle[i][(j + 1) % m]) {
                    arr.add(new Score(j, i));
                    arr.add(new Score((j + 1) % m, i));
                }
            }
        }
        // 원 밖에 인접한 수를 비교
        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n - 1 ; j++) {
                if (circle[j][i] != 0 && circle[j][i] == circle[j + 1][i]) {
                    arr.add(new Score(i, j));
                    arr.add(new Score(i, (j + 1)));
                }
            }
        }
        if (!arr.isEmpty()) {
            for (Score d : arr) { 
                circle[d.y][d.x] = 0; // 0 으로 바꿈
            }
        } else {
            double avg = avg();
            for (int i = 0 ; i < n ; i++) {
                for (int j = 0 ; j < m ; j++) {
                    if (circle[i][j] != 0 && circle[i][j] < avg) {
                        circle[i][j]++;
                    } else if (circle[i][j] != 0 && circle[i][j] > avg) {
                        circle[i][j]--;
                    }
                }
            }
        }
        
    }
    static void rotate_circle(int x, int d, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1 ; i <= n ; i++) {
            if (i % x == 0) {
                // 만약 x 배수면 리스트에 추가
                arr.add(i-1);
            }
        }
        
        switch (d) {
            case 0 : 
                // 시계 방향
                // k번 반복
                for (int a = 0 ; a < k ; a++) {
                    for (int num : arr) {
                        int tmp = circle[num][m - 1];
                        for (int i = m - 1 ; i > 0 ; i--) {
                            circle[num][i] = circle[num][i-1];
                        }
                        circle[num][0] = tmp;
                    }
                }
                break;
            case 1 : 
                for (int a = 0 ; a < k ; a++) {
                    for (int num : arr) {
                        int tmp = circle[num][0];
                        for (int i = 0 ; i < m - 1; i++) {
                            circle[num][i] = circle[num][i+1];
                        }
                        circle[num][m - 1] = tmp;
                    }
                }
                break;
        }
    }
    static void print_circle() {
        for (int i = 0 ; i < n ; i++) {
            System.out.println(Arrays.toString(circle[i]));
        }
    }
    static double avg() {
        double sum = 0.0;
        int cnt = 0;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (circle[i][j] != 0) {
                    sum += circle[i][j];
                    cnt++;
                }
               
            }
        }
        return sum / cnt;
    }
    static int sum() {
        int sum = 0;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                sum += circle[i][j];
            }
        }
        return sum;
    }
}
class Score {
    int x;
    int y;
    Score (int x, int y) {
        this.x = x;
        this.y = y;
    }
}