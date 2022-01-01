import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4095 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine() , " ");
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if (R+C == 0) break;

            int map[][] = new int[R + 1][C + 1];
            for (int i = 1 ; i <= R ; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 1 ; j <= C ; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = 0;
            for (int i = 1 ; i <= R ; i++) {
                for (int j = 1 ; j <= C ; j++) {
                    if (map[i][j] == 0) continue;
                    map[i][j] = Math.min(map[i-1][j], Math.min(map[i][j-1], map[i-1][j-1])) + 1;
                    ans = Math.max(ans, map[i][j]);
                }
            }
            System.out.println(ans);


        }
    }
}
