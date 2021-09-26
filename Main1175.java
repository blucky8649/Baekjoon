import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1175 {
    static String[][] map;
    static boolean isVisited[][][][];
    static int N, M, min = Integer.MAX_VALUE;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];
        isVisited = new boolean[N][M][4][3];
        
        int mx = 0;
        int my = 0;

        for(int i = 0 ; i < N ; i++){
            String s = br.readLine();
            for(int j = 0 ; j < M ; j++){
                map[i][j] = String.valueOf(s.charAt(j));
                if(map[i][j].equals("S")){
                    mx = j;
                    my = i;
                }
            }
        }
        BFS(mx, my);

    }
    static void BFS(int x, int y){
        Queue<Minsik> q = new LinkedList<>();
        isVisited[y][x][0][0] = isVisited[y][x][1][0] = isVisited[y][x][2][0] = isVisited[y][x][3][0] =true;
        q.offer(new Minsik(x, y, -1, 0, 0));

        while(!q.isEmpty()){
            Minsik d = q.poll();
            
            if(d.delivered == 2){
                System.out.println(d.moved);
                return;
            }
            
            for(int i = 0 ; i < 4 ; i++){
                if(d.dir == i) continue;
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if(nx >= 0 && nx < M && ny >= 0 && ny < N && (!isVisited[ny][nx][i][d.delivered]) && (!map[ny][nx].equals("#"))){
                    isVisited[ny][nx][i][d.delivered] = true;
                    if(map[ny][nx].equals("C")){
                        q.add(new Minsik(nx, ny, i, d.moved + 1, d.delivered + 1));
                    }else{
                        q.add(new Minsik(nx, ny, i, d.moved + 1, d.delivered));
                    }
                    
                }
            }
        }
    }
    
}
class Minsik{
    int x;
    int y;
    int dir;
    int moved;
    int delivered;
    Minsik(int x, int y, int dir, int moved, int delivered){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.delivered = delivered;
        this.moved = moved;
    }
}