import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Bomb{
    int x;
    int y;
    Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main16918 {
    static int N, M, S;
    static char[][] map;
    static boolean[][] isVisited;
    static int ss = 0;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        boolean designated = false;
        map= new char[N][M];
        isVisited = new boolean[N][M];
        // 홀수 초
        for(int i = 0 ; i < N ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < M ; j++){
                char cr = (str.charAt(j)); 
                map[i][j] = cr;
                if(cr == 'O'){
                    boom(j, i); // 지정만 해놓는거임
                }
            }
        }
        designated = true;

        for (int i = 2 ; i <= S ; i++) {
            if (!designated) {
                isVisited = new boolean[N][M];
                    for (int a = 0 ; a < N ; a++) {
                        for (int b = 0 ; b < M ; b ++) {
                            if (map[a][b] == 'O') {
                                boom(b, a);
                            }

                        }
                    }
                    designated = true;
            }
            if (i % 2 == 0) {
                for (int j = 0 ; j < N ; j++) {
                    Arrays.fill(map[j], 'O');
                }
            } else {
                for (int a = 0 ; a < N ; a++) {
                    for (int b = 0 ; b < M ; b ++) {
                        if (isVisited[a][b]) {
                            map[a][b] = '.';
                        }

                    }
                }
                designated = false;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int a = 0 ; a < N ; a++) {
            for (int b = 0 ; b < M ; b ++) {
                sb.append(map[a][b]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        
        
    }
    // 폭탄 터칠 영역 지정
    static void boom(int x, int y) {
        isVisited[y][x] = true;

        for (int i = 0 ; i < 4 ; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < M && ny >= 0 && ny < N && !isVisited[ny][nx]) {
                isVisited[ny][nx] = true;
            }
        }
    }
}
