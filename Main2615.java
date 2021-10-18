import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2615 {
    static int[] dy = {1, 0, 1, 1}; // 아래, 오른쪽,  오른쪽 대각선, 왼쪽 대각선
    static int[] dx = {0, 1, 1, -1};

    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        map = new int[19][19];
        for (int i = 0 ; i < 19 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < 19 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i < 19 ; i++) {
            for (int j = 0 ; j < 19 ; j++) {
                if (map[i][j] > 0) {
                    for (int a = 0 ; a < 4 ; a++) {
                        int cnt = BFS(j, i, a);
                        if (cnt == 5) {
                            if (a == 3) {
                                System.out.println(map[i+4][j-4]);
                                System.out.println((i+5) + " " + (j-3));
                                return;
                            }
                            System.out.println(map[i][j]);
                            System.out.println((i+1) + " " + (j+1));
                            return;
                        }
                    }
                    

                    
                }
            }
        }
        System.out.println(0);
    }   
    static int BFS(int x, int y, int dir) {
        Queue<Dong> q = new LinkedList<>();
        q.offer(new Dong(x, y, 1));
        int max = 0;

        while (!q.isEmpty()) {
            Dong d = q.poll();
            max = Math.max(d.cnt, max);
            int nx = d.x + dx[dir];
            int ny = d.y + dy[dir];

            if (nx >= 0 && nx < 19 && ny >= 0 && ny < 19 && map[ny][nx] == map[y][x]) {
                q.offer(new Dong(nx, ny, d.cnt + 1));
            } 

        }
        // 6목 검사
        if (max == 5) {
            int nx = x - dx[dir];
            int ny = y - dy[dir];

            if(nx>=0 && nx<19 && ny>=0 && ny<19 && map[ny][nx]==map[y][x]) max++;
        }
        return max;
    }
}
class Dong {
    int x;
    int y;
    int cnt;
    Dong(int x, int y, int cnt) {
        this.x =x;
        this.y = y;
        this.cnt = cnt;
    }
}