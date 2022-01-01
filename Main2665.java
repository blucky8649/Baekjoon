import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2665 {
    static int n;
    static int map[][], isVisited[][];
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        isVisited = new int[n][n];
        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(isVisited[i],Integer.MAX_VALUE);
            String str = br.readLine();
            for (int j = 0 ; j < n ; j++) {
                int room_num = str.charAt(j) - '0';
                map[i][j] = room_num;
            }
        }
        BFS(0, 0);
        System.out.println(isVisited[n - 1][n - 1]);
    }
    static void BFS(int x, int y) {
        Queue<Na> q = new LinkedList<>();
        q.offer(new Na(x, y));
        isVisited[y][x] = 0;

        while (!q.isEmpty()) {
            Na d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && isVisited[ny][nx] > isVisited[d.y][d.x]) {
                    if (map[ny][nx] == 1) {
                        isVisited[ny][nx] = isVisited[d.y][d.x];
                    } else {
                        isVisited[ny][nx] = isVisited[d.y][d.x] + 1;
                    }
                    q.offer(new Na(nx, ny));
                }
            }
        }
    }
}
class Na {
    int x;
    int y;
    Na (int x, int y) {
        this.x = x;
        this.y = y;
    }
}