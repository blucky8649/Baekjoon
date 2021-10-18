import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15685 {
    static int[] dy = {0, -1, 0, 1}; // 동 북 서 남
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] map = new boolean[101][101];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken()); // x축
            int y = Integer.parseInt(st.nextToken()); // y축
            int d = Integer.parseInt(st.nextToken()); // 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            makeCurve(x, y, d, g);
        }
        int answer = 0;
        for (int i = 0 ; i < map.length ; i++) {
            for (int j = 0 ; j < map[0].length ; j++) {
                if (map[i][j]) {
                    if(checkCurve(j, i)) {
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
    static void makeCurve(int x, int y, int d, int g) {
        ArrayList<Integer> curve = new ArrayList<>();
        curve.add(d); // 0세대 커브
        for (int i = 1 ; i <= g ; i++) {
            for (int j = curve.size()-1 ; j >= 0 ; j--) {
                curve.add((curve.get(j) + 1) % 4);
            }
        }
        map[y][x] = true;
        for (int dir : curve) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            map[ny][nx] = true;
            x = nx;
            y = ny;
        }
        
    }
    static boolean checkCurve(int x, int y) {
        // 세 꼭지점 중 하나라도 false 가 있으면
        if (x == 100 || y == 100 || (!map[y][x+1]) || (!map[y+1][x]) || (!map[y+1][x+1])) {
            return false;
        }
        
        return true;
    }
}
