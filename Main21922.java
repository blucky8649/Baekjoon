import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main21922 {
    static int n, m, cnt;
    static int[][] map;
    static boolean isVisited[][][];

    static Queue<Air> q = new LinkedList<Air>();

    static int[] dx = {0, 1, 0, -1};	
	static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isVisited = new boolean[n][m][4];
        map = new int[n][m];
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < m ; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 9) {
                    for (int dir = 0 ; dir < 4; dir ++) {
                        isVisited[i][j][dir] = true;
                        q.offer(new Air(j, i, dir));
                    }
                    
                }
                map[i][j] = temp; 
            }
        }
        BFS();
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int dir = 0; dir < 4; dir++) {
					if(isVisited[i][j][dir]) {
						cnt++;
						break;
					}
				}
			}
		}
        System.out.println(cnt);
    }
    static void BFS() {
        while (!q.isEmpty()) {
            Air d = q.poll();

            int nx = d.x + dx[d.dir];
            int ny = d.y + dy[d.dir];
            
            if (nx < 0 || nx >= m || ny < 0 || ny >= n ) continue;
            if (isVisited[ny][nx][d.dir]) continue;
            isVisited[ny][nx][d.dir] = true;
            
            switch(map[ny][nx]) {
                case 1 :
                    if(d.dir == 1 || d.dir == 3) continue;
                    break;
                case 2 :
                    if(d.dir == 0 || d.dir == 2) continue;
                    break;
                case 3 :
                    if(d.dir == 0) d.dir = 1;
                    else if(d.dir == 1) d.dir = 0;
                    else if(d.dir == 2) d.dir = 3;
                    else if(d.dir == 3) d.dir = 2;
                    break;
                case 4 :
                    if(d.dir == 0) d.dir = 3;
                    else if(d.dir == 1) d.dir = 2;
                    else if(d.dir == 2) d.dir = 1;
                    else if(d.dir == 3) d.dir = 0;
                    break;
                }
                

                q.add(new Air(nx, ny, d.dir));
        }
    }
}
class Air {
    int x;
    int y;
    int dir;
    Air(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}