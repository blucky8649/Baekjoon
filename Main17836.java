import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.WildcardType;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17836 {
    static int n, m, t;
    static int[][] map;
    static boolean[][][] isVisited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        isVisited = new boolean[n][m][2];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS(0, 0);

    }
    static void BFS(int x, int y){
        Queue<Man> q = new LinkedList<>();
        isVisited[y][x][0] = true;
        q.offer(new Man(x, y, 0, 0));

        while(!q.isEmpty()){
            Man d = q.poll();

            if(d.cnt > t){
                continue;
            }

            if(d.x == m -1 && d.y == n -1){
                System.out.println(d.cnt);
                return;
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx >= 0 && nx < m && ny >= 0 && ny < n && !isVisited[ny][nx][d.skill]){
                    // 무기(그람)를 아직 못먹었을 때
                    if(d.skill == 0){
                        if(map[ny][nx] != 1){
                            if(map[ny][nx] == 2){
                                q.add(new Man(nx, ny, 1, d.cnt + 1));
                                isVisited[ny][nx][d.skill] = true;
                            }else{
                                q.add(new Man(nx, ny, d.skill, d.cnt + 1));
                                isVisited[ny][nx][d.skill] = true;
                            }
                        }
                        
                    // 그람 겟
                    }else{
                        q.add(new Man(nx, ny, d.skill, d.cnt + 1));
                        isVisited[ny][nx][d.skill] = true;
                    }
                }
            }
        }
        System.out.println("Fail");
    }
    
}
class Man{
    int x;
    int y;
    int skill;
    int cnt;
    Man(int x, int y, int skill, int cnt){
        this.x = x;
        this.y = y;
        this.skill = skill;
        this.cnt = cnt;
    }
}