import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <야구를 위해 필요한 것>
 * 1. 선발 타자
 * 2. 주자들의 포지션
 * 3. 점수
 * 4. 각 선발 타자들의 성과
 * 참고사항 : 1번 선수는 4번타자임 
 */


public class Main17281 {
    static int n, ans = 0;
    static int[] player = new int[9];
    static boolean[] isPositioned = new boolean[9];
    static int[][] game;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        game = new int[n][9];
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < 9 ; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        player[3] = 0; // 4번 타자는 1번 선수
        isPositioned[0] = true; // 1번 선수는 선정되었다 이말이여
        select_player(0);
        System.out.println(ans);
    }
    static void select_player(int k) {
        if (k == 9) {
            ans = Math.max(game(), ans);
            return;
        }

        for (int i = 0 ; i < 9 ; i++) {
            if (!isPositioned[i]) {
                if (k == 2) {
                    isPositioned[i] = true;
                    player[k] = i;
                    select_player(k + 2);
                    isPositioned[i] = false;
                    continue;
                }
                isPositioned[i] = true;
                player[k] = i;
                select_player(k + 1);
                isPositioned[i] = false;
            }
        }
    }
    static int game() {
        int score = 0;
        int pos = 0; // 현재 타자의 포지션(1번 타자 대기중)
        int out_cnt = 0; // out 카운트
        int base[] = new int[4]; // 베이스의 상태
        // 1번 타자부터 순서대로 공을 친다.
        int  inning = 0;
        while (inning < n) {
            switch (game[inning][player[pos]]) {
                // 순서대로 아웃, 1, 2, 3루, 홈런
                case 0 : 
                    out_cnt++;
                    // 3아웃일 때 베이스는 초기화되어야 함
                    if (out_cnt == 3) {
                        base = new int[4];
                        out_cnt = 0;
                        inning++; // 이닝 넘어감
                    }
                    break;
                case 1 : 
                    base[0] = base[3];
                    base[3] = base[2];
                    base[2] = base[1];
                    base[1] = 1;
                    break;
                case 2 : 
                    base[0] = base[3] + base[2];
                    base[3] = base[1];
                    base[2] = 1;
                    base[1] = 0;
                    break;
                case 3 : 
                    base[0] = base[3] + base[2] + base[1];
                    base[3] = 1;
                    base[2] = 0;
                    base[1] = 0;
                    break;
                case 4 : 
                    base[0] = base[3] + base[2] + base[1] + 1;
                    base[3] = 0;
                    base[2] = 0;
                    base[1] = 0;
                    break;
            }
            pos = (pos + 1) % 9;
            if (base[0] > 0) {
                score += base[0];
                base[0] = 0;
            }
        }
        
        return score;
    }
}
