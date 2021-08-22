import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Main2667 {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static int n;
    public static int[][] map;
    public static boolean[][] isVisited;
    public static int cnt =0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ArrayList<Integer> result = new ArrayList<>();
        int danji = 0;
        map = new int[n][n];
        isVisited = new boolean[n][n];

        for(int i = 0; i<n ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < n ; j++){
                map[i][j] =  str.charAt(j) - '0';
                
            }
            
        }
        for(int i = 0; i<n ; i++){
            for(int j = 0 ; j < n ; j++){
                if(map[i][j] == 1 && !isVisited[i][j]){
                    System.out.println(i + " + " + j + "샤랄라 ~" + isVisited[0][1]);
                    cnt = 1;
                    DFS(i, j);
                    result.add(cnt);
                    danji ++;
                    
                }
            }
        }
        System.out.println(danji);
        Collections.sort(result);
        for(int i = 0 ; i<result.size() ;i++){
            System.out.println(result.get(i));
        }

        
    }
    public static void DFS(int x, int y){
        isVisited[x][y] = true;
        
        for(int  i = 0 ; i<dx.length;i++){
            int dX = x + dx[i];
            int dY = y + dy[i];
            if(dX >= 0 && dY >= 0 && dX<n && dY < n){
                if(map[dX][dY] == 0 || isVisited[dX][dY]) continue;
                cnt++;
                DFS(dX, dY);
            }
        }
    }
    
}
