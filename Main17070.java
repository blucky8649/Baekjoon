import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17070 {
    static int[][][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n][3];
        int wall[][] = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                wall[i][j]  = Integer.parseInt(st.nextToken());
            }
        }
        // 0 : 가로, 1: 세로, 2: 대각선
        // 초기값
        map[0][1][0] = 1;
        for (int i = 0 ; i < n ; i++) {
            for (int j = 1 ; j < n ; j++) {
                
                // 1. 가로 방향으로 가는 경우
                if (wall[i][j] == 1) continue;
                map[i][j][0] += map[i][j - 1][0] + map[i][j - 1][2];

                // 2. 세로 방향으로 내려가는 경우
                if (i == 0) continue; 
                map[i][j][1] += map[i - 1][j][1] + map[i - 1][j][2];
                    
                // 3. 대각선 방향으로 내려가는 경우
                if (wall[i - 1][j] == 1 || wall[i][j - 1] == 1)  continue;
                map[i][j][2] += map[i-1][j-1][0] + map[i-1][j-1][1] + map[i-1][j-1][2];
                
            }
        }
        System.out.println(map[n-1][n-1][0] + map[n-1][n-1][1] + map[n-1][n-1][2]);     
    }
}
