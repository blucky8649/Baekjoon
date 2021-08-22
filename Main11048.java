import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11048 {
    static int n, m;
    static int[][] map;
    static int max=Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        collect(0, 0);
        System.out.println(Arrays.deepToString(map));
    }
    
    public static int collect(int x, int y){
        map[y][x]= Math.max(collect(x-1, y), Math.max(collect(x, y-1), collect(x-1, y-1)));

        return map[y][x];
    }
}
