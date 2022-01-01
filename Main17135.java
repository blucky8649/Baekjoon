import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main17135 {
    static int n, m, dir, ans = 0;
    static int[][] map;
    static int[] arr = new int[3];
    static boolean[] check;
    static ArrayList<Integer> arc = new ArrayList<>(); // 궁수 조합을 담기 위한 리스트
    static PriorityQueue<Arc> enemy = new PriorityQueue<>(); // 적 리스트
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 1. DFS를 이용하여 모든 궁수 배치 조합을 얻음
        // 2. 해당 조합으로 시뮬레이션 진행 

        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        dir = Integer.parseInt(st.nextToken()); // 사거리
        check = new boolean[m];
        map = new int[n + 2][m];
        
        for (int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    enemy.add(new Arc(j, i));
                }
            }
        }
        DFS(0, 0);
        System.out.println(ans);
    }
    static void DFS(int start, int k) {
        if (k == 3) {
            ans = Math.max(fight(), ans);
            return;
        }

        for (int i = start ; i < m ; i++) {
            if (!check[i]) {
                check[i] = true;
                arc.add(i);
                DFS(i + 1, k + 1);
                check[i] = false;
                arc.remove(arc.size() - 1);
            }  
        }
    }

    static int fight() {
        ArrayList<Arc>enemy2 = new ArrayList<>(enemy);
        int kill =0;
        while (!enemy2.isEmpty()) {
            HashSet<Integer>klist = new HashSet<>(); // 지목한 적을 담아내는 Set
            // 1. 궁수는 적을 한명 지목한다.
            for (int d : arc) {
                int min_dst = Integer.MAX_VALUE;
                int dsg = 0;
                int xx = Integer.MAX_VALUE;
                for (int i = 0 ; i < enemy2.size() ; i++) {
                    Arc e = enemy2.get(i);
                    int dst = Math.abs((n+1) - e.y) + Math.abs(d - e.x);
                    // 만약 거리가 사정거리 이내에 있으면 잡는다.
                    if (dst <= dir) {
                        if ((dst == min_dst && xx > e.x) || min_dst > dst ) {
                            xx = e.x;
                            min_dst = dst;
                            dsg = e.y * 1000 + e.x;
                        }
                    }
                }
                if (dsg != 0){
                    klist.add(dsg);
                } 
            }
            ArrayList<Arc> sub = new ArrayList<>(enemy2);
            kill += klist.size();
            for (int num : klist) {
                int y = num / 1000;
                int x = num - (y * 1000);
                for (int i = 0 ; i < sub.size() ; i++) {
                    Arc d = sub.get(i);
                    if ((x == d.x && y == d.y)) {
                        sub.remove(i);
                        i--;
                    }
                }
            }
            enemy2 = new ArrayList<>(sub);
            // 2. 적들은 y축으로 한 칸 이동합니다.
            sub = new ArrayList<>();
            for (Arc d : enemy2) {
                int x = d.x;
                int y = d.y + 1;
                // 만약 적이 격자 안에 있는 경우
                if (y < n+1) {
                    sub.add(new Arc(x, y));
                }
            }
            Collections.sort(sub);
            enemy2 = new ArrayList<>(sub); 
        }
        return kill;
    }
}
class Arc implements Comparable<Arc>{
    int x;
    int y;
    Arc(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Arc o) {
        if (this.y == o.y) {
            return this.x - o.x;
        }
        return o.y - this.y;
    }
}