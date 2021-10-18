import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main21608 {
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] check = new int[n][n]; // 자리가 사용중인지 확인
        int[][] prior = new int[n][n];
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                prior[i][j] = 4;
                if (i - 1 < 0 || i + 1 >= n) prior[i][j]--;
                if (j - 1 < 0 || j + 1 >= n) prior[i][j]--;
            }
        }
        Queue<std2> std2 = new LinkedList<>();
       for (int i = 0 ; i < n * n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int p3 = Integer.parseInt(st.nextToken());
            int p4 = Integer.parseInt(st.nextToken());
            int love[] = {p1, p2, p3, p4};
            
            // 1. 인접한 학생이 가장 많은 칸을 탐색
            int[][] adj = new int[n][n]; 
            // 1-1. 우선순위에 따라 가중치를 부여한다.
            int max = 0;
            for (int a = 0 ; a < n ; a++) {
                for (int b = 0 ; b < n ; b++) {
                    if (check[a][b] == p1 || check[a][b] == p4 || check[a][b] == p3 || check[a][b] == p2) {
                        for (int z = 0 ; z < 4 ; z++) {
                            int nx = b + dx[z];
                            int ny = a + dy[z];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && check[ny][nx] == 0) {
                                adj[ny][nx]++;
                                if (max < adj[ny][nx]) {
                                    max = adj[ny][nx];
                                }
                            }
                        }
                    }
                }
            }
            int max2 = 0;
            PriorityQueue<std> q = new PriorityQueue<>();
            for (int a = 0 ; a < n ; a++) {
                for (int b = 0 ; b < n ; b++) {
                    if (adj[a][b] == max && check[a][b] == 0) {
                        q.offer(new std(b, a, prior[a][b] + adj[a][b])); // 자리 후보 지정
                        if (max2 < prior[a][b] + adj[a][b]) {
                            max2 = prior[a][b] + adj[a][b];
                        }
                    }

                }
            }
            // 2. 1번을 만족하는 칸이 여러개면 그 중에서 비어있는 칸이 많은 칸 탐색
            PriorityQueue<std> q2 = new PriorityQueue<>();
            if (q.size() >= 2) {
                while (!q.isEmpty()) {
                    std d = q.poll();
                    if (d.num == max2) {
                        q2.offer(d);
                    }
                }
                q = q2;
            }
            
           
           // 3. 2번도 만족한다면, 행 번호 가장 작은칸으로, 그러한 칸도 여러개면 열의 번호가 가장 적은 칸 탐색.
            std peek = q.poll();

            check[peek.y][peek.x] = num; // 자리 선정!
            std2.offer(new std2(peek.x, peek.y, num, love));
            for (int a = 0 ; a < 4 ; a++) {
                int nx = peek.x + dx[a];
                int ny = peek.y + dy[a];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && check[ny][nx] == 0) {
                    prior[ny][nx]--; // 빈칸 가중치 감소
                }
            }
         
       }
       
        int ans = 0;
        while (!std2.isEmpty()) {
            std2 d = std2.poll();
            int cnt = 0;
            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (check[ny][nx] == d.flist[0] || check[ny][nx] == d.flist[1] || check[ny][nx] == d.flist[2]|| check[ny][nx] == d.flist[3]) {
                        cnt++;
                    }
                }
                
            }
                if (cnt == 1) ans += 1;
                if (cnt == 2) ans += 10;
                if (cnt == 3) ans += 100;
                if (cnt == 4) ans += 1000;
        }
        System.out.println(ans);
    }

}
class std implements Comparable<std>{
    int x;
    int y;
    int num;
    std(int x, int y, int num) {
        this.x =x;
        this.y =y;
        this.num = num;
    }
    @Override
    public int compareTo(std o) {
        if (this.y == o.y) {
            return this.x - o.x;
        }
        return this.y -o.y;
    }
}

class std2 {
    int x;
    int y;
    int num;
    int[] flist;
    std2(int x, int y, int num, int[] flist) {
        this.x =x;
        this.y =y;
        this.num = num;
        this.flist = flist;
    }
}