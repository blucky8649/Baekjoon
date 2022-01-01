import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main5212 {
    static int n, m;
    static char[][] map;
    static boolean[][] isVisited;
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        isVisited = new boolean[n][m];

        for (int i = 0 ; i < n ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < m ; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        Queue<Island> q = new LinkedList<>();
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                if (map[i][j] == 'X') {
                    int cnt = 0;
                    for (int a = 0 ; a < 4 ; a++) {
                        int nx = j + dx[a];
                        int ny = i + dy[a];
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n || map[ny][nx] == '.') {
                            cnt++;
                        }
                    }
                    if (cnt >= 3) {
                        q.offer(new Island(j, i));
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            Island d = q.poll();
            map[d.y][d.x] = '.';
        }
        int max_y = 0;
        int min_y = 0;
        int max_x = 0;
        int min_x = 0;
        boolean selected_maxy = false;
        boolean selected_miny = false;
        boolean selected_maxx = false;
        boolean selected_minx = false;
        for (int i = 0 ; i < n ; i++) {

            for (int j = 0 ; j < m ; j++) {
                if (map[i][j] == 'X' && !selected_miny) {
                    min_y = i;
                    selected_miny = true;
                }
                if (map[n - 1 - i][j] == 'X' && !selected_maxy) {
                    max_y = n - 1 - i;
                    selected_maxy = true;
                }

            }
        }

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (map[j][i] == 'X' && !selected_minx) {
                    min_x = i;
                    selected_minx = true;
                }
                if (map[j][m - 1 - i] == 'X' && !selected_maxx) {
                    max_x = m-1-i;
                    selected_maxx = true;
                }

            }
        }
        for (int i = min_y ; i <= max_y ; i++) {
            for (int j = min_x ; j <= max_x ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }
    
}
class Island {
    int x;
    int y;
    Island(int x, int y) {
        this.x = x;
        this.y = y;
    }
}