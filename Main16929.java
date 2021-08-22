import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main16929 {
    static int n, m;
    static char[][] map;
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result= "";
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        
        for(int i = 0 ; i < n ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < m ; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                isVisited = new boolean[n][m];
                if(DFS(j, i, -1, -1, map[i][j])){
                    result = "Yes";
                    System.out.println(result);
                    return;
                }
            }
        }
        System.out.println("No");


    }

    static boolean DFS(int x, int y,int px, int py, int alp){
        
        if(isVisited[y][x]) return true;

        isVisited[y][x] = true;

        for(int i = 0 ; i < 4 ; i++){
            int X = x + dx[i];
            int Y = y + dy[i];
            if(!(X >= 0 && Y >= 0 && X < m && Y < n)) continue;
            if(map[Y][X] != alp) continue;
            if(X == px && Y == py) continue;
            if(DFS(X, Y, x, y, alp)){
                return true;
            }
            
        }

        return false;


    }
}
