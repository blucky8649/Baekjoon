import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main16954 {
    static Queue<Chess> wall = new LinkedList<>();
    static char[][] map = new char[8][8];
    static int[] dy = {-1, 0, 1, 0, -1, -1, 1, 1, 0}; // direction to move.
    static int[] dx = {0, 1, 0, -1, -1, 1, -1, 1, 0}; 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Chess me = new Chess(0, 7); // start position.
        
        for(int i = 0 ; i < 8 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 8 ; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '#'){
                    wall.offer(new Chess(j, i));
                }
            }
        }
        System.out.println(BFS(me.x, me.y));
    }

    static int BFS(int x, int y){
        Queue<Chess> q = new LinkedList<>();
        q.offer(new Chess(x, y));

        while(!q.isEmpty()){
            int size = q.size();

            boolean[][] isVisited = new boolean[8][8];
            for(int a = 0 ; a < size ; a++){
                Chess d = q.poll();


                if(map[d.y][d.x] == '#') continue;
                if(d.y == 0) return 1;
                if(wall.isEmpty()) return 1;

                for(int i = 0 ; i < 9 ; i++){
                    int nx = d.x + dx[i];
                    int ny = d.y + dy[i];
                    if(ny < 0 || ny >= 8 || nx < 0 || nx >= 8) continue;

                    if(!isVisited[ny][nx] && map[ny][nx] != '#'){
                        q.offer(new Chess(nx, ny));
                        isVisited[ny][nx] = true;
                    }
                }
            }
            if(!wall.isEmpty()) moveWall();
        
        }
     return 0;
   }

    static void moveWall(){
        Queue<Chess> sub = new LinkedList<>();

        while(!wall.isEmpty()){
            Chess d = wall.poll();
            int y = d.y;
            int x = d.x;

            map[y][x] = '.';
            if(y < 7){
                sub.offer(new Chess(x, y+1));
            }
        }

        while(!sub.isEmpty()){
            Chess d = sub.poll();
            map[d.y][d.x] = '#';
            wall.offer(d);
        }
    }   
}
class Chess {
    int x;
    int y;
    Chess(int x, int y){
        this.x =x;
        this.y =y;
    }
}