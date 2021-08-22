import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1926 {
    static int n, m, map[][], cnt = 0, cnt2 = 0;
    static int [] dy = {-1, 0, 1, 0};
    static int [] dx = {0, 1, 0, -1};
    static boolean isVisited[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isVisited = new boolean[n][m];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(map[i][j] == 1 && !isVisited[i][j]){
                    BFS(j, i);
                    cnt ++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(cnt2);
    }
    static void BFS(int x, int y){
        Queue<Spott> q = new LinkedList<>();
        q.offer(new Spott(x, y));
        isVisited[y][x] = true;
        int cnt1 = 1;

        while(!q.isEmpty()){
            Spott d = q.poll();

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n || isVisited[ny][nx] || map[ny][nx] == 0) continue;
                q.offer(new Spott(nx, ny));
                isVisited[ny][nx] = true;
                cnt1++;
            }
        }
        cnt2 = Math.max(cnt2, cnt1);

    }    
}
class Spott {
    int x;
    int y;
    Spott(int x, int y){
        this.x =x;
        this.y =y;
    }
}
