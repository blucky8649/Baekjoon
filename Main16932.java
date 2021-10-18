import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16932 {
    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;
    
    static int[] dy = {-1, 0, 1, 0}; //네 가지 방향 : 상 하 좌 우
    static int[] dx = {0, 1, 0, -1};

    static Map<Integer, Integer> group;
    static HashSet<JUM> zero = new HashSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        
        isVisited = new boolean[n][m];
        for(int i = 0;  i < n ;  i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int groupNum = 1;
        group = new HashMap<>();
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {
                if(!isVisited[i][j] && map[i][j] == 1) {
                     group.put(groupNum, BFS(j, i, groupNum));
                     groupNum++;
                }
            }
        }
        
        System.out.print(connect());
    }
    static int BFS(int x, int y, int groupNum){
        int cnt = 1;
        isVisited[y][x] = true;
        map[y][x] = groupNum;
        Queue<JUM> q = new LinkedList<>();
        q.offer(new JUM(x, y));
        while (!q.isEmpty()) {
            JUM d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && !isVisited[ny][nx]){
                    if (map[ny][nx] > 0) {
                        isVisited[ny][nx] = true;
                        map[ny][nx] = groupNum;
                        cnt++;
                        q.offer(new JUM(nx, ny));
                    } else if (map[ny][nx] == 0) {
                        isVisited[ny][nx] = true;
                        zero.add(new JUM(nx, ny)); // 0의 좌표만 담는다..!
                    }
                    
                }
            }
        }
        return cnt;
    }

    static int connect() {
        int solution = 0;
        HashSet<Integer> set;
        for (JUM d : zero) {
            int answer = 1;
             set = new HashSet<>();
            
            
            for (int i = 0 ; i  < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && map[ny][nx] > 0 && !set.contains(map[ny][nx])) {
                    answer += group.get(map[ny][nx]);
                    set.add(map[ny][nx]);
                }
            }
            solution = Math.max(solution, answer);
        }
        return solution;
    }
}
class JUM {
    int x;
    int y;
    JUM (int x, int y){
        this.x =x;
        this.y =y;
    }
}