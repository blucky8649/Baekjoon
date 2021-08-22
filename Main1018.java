import java.io.*;
import java.util.StringTokenizer;
public class Main1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int min = 100;
        int cnt =0;
        boolean[][] board = new boolean[n][m];
        for (int i = 0 ; i < n ; i ++){
            String WB = br.readLine();
            for(int j = 0 ; j<m ; j++){
                if(WB.charAt(j) == 'B'){
                    board[i][j] = true;
                }
            }
        }

        
        for(int x = 0 ; x <= m-8 ; x++){
            for(int y = 0 ; y <= n-8 ; y++){
                boolean tf = board[y][x];

                cnt = 0;
                for(int i = y ; i < 8+y ; i++){
                    for(int j = x; j< 8+x ; j++){
                        
                        if(board[i][j] == tf){
                            
                            tf = (!tf);
                        }else{
                            tf = (!tf);
                            cnt ++;
                        }
                       
                    }
                    tf = (!tf);
                }
                cnt = Math.min(cnt, 64 - cnt);
                min = Math.min(cnt, min);
            }
        }
        
        System.out.println(min);

        

        
    }
}
