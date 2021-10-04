import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1913 {
    static int[] dy = {1, 0, -1, 0}; //남 동 북 서
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int num = n*n;

        int[][] map = new int[n][n];
        int x = 0;
        int y = 0;

        int dir = 0; // 방향 정보
        int i = 0;
        while(num > 0){
            map[y][x] = num--;
            
            if (x == i && y == n - (1 + i)) dir = 1;
            else if (x == n - (1 + i) && y == i) dir = 3;
            else if (x == n - (1 + i) && y == n - (1 + i) ) dir = 2;
            else if (x == i && y == i )dir = 0;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(map[ny][nx] > 0){
                dir = (dir + 1) % 4;
                i++;
                x += dx[dir];
                y += dy[dir];
            }else{
                x = nx;
                y = ny;
            }
        }
        int[] pos = new int[2];
        StringBuilder sb = new StringBuilder();
        for(int a = 0 ; a < n ; a++){
            for(int b = 0 ; b < n ; b++){
                sb.append(map[a][b]).append(" ");
                if(map[a][b] == target){
                    pos[0] = a + 1;
                    pos[1] = b + 1;
                }
            }
            sb.append("\n");
        }
        sb.append(pos[0] + " " + pos[1]);
        System.out.print(sb.toString());
    }
    
}
