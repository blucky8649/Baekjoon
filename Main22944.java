import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main22944 {
    static int N, H, D, ans = Integer.MAX_VALUE;
    static char map[][];
    static boolean[] check;
    static ArrayList<DeathRain> umbList = new ArrayList<>();
    static DeathRain e = new DeathRain(0, 0);
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken()); // 현재 체력
        D = Integer.parseInt(st.nextToken()); // 우산의 내구도
        DeathRain me = new DeathRain(0, 0);
        map = new char[N][N];
        for (int i = 0 ; i < N ; i++) {
            String str = br.readLine();
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    me.x = j;
                    me.y = i;
                }
                if (map[i][j] == 'U') {
                    umbList.add(new DeathRain(j, i));
                }
                if (map[i][j] == 'E') {
                    e.x = j;
                    e.y = i;
                }
            }
        }
        check = new boolean[umbList.size()];
        DFS(0, me.x, me.y, 0, H);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }
    static void DFS(int depth, int x, int y, int umb, int HP) {
        if (Math.abs(e.y - y) + Math.abs(e.x - x) <= HP + umb) {
            ans = Math.min(ans, depth + Math.abs(e.y - y) + Math.abs(e.x - x));
        }

        for (int i = 0 ; i < umbList.size() ; i++) {
            // 이미 방문된 루트거나, 현재 체력으로 갈 수 없을 때
            int nx = umbList.get(i).x;
            int ny = umbList.get(i).y;
            int dst = Math.abs(nx - x) + Math.abs(ny - y);
            if (check[i] || dst > umb + HP) continue;
            check[i] = true;
            DFS(depth + dst, nx, ny, D, HP - (dst > umb ? dst - umb : 0));
            check[i] = false;
            
        }
    }
}

class DeathRain {
    int x;
    int y;
    DeathRain (int x, int y) {
        this.x=x;
        this.y=y;
    }
}
