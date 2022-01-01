import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main21611 {
    static int n, m, ans = 0;
    static int[][] snail, map;
    static ArrayList<Integer> arr = new ArrayList<>(); // 2차원 배열을 1차원화 시킨 것
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[] dy2 = {-1, 1, 0, 0};
    static int[] dx2 = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // 격자 한 변의 길이
        m = Integer.parseInt(st.nextToken()); // 블리자드 사용 횟수
        snail = new int[n][n];
        make_snail();
        map = new int[n][n];
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search();
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            blizard(d, s);
        }
        System.out.println(ans);
    }
    static void blizard(int d, int s) {
        int nx = n / 2;
        int ny = n / 2;
        //1. 유리파편 던지기
        for (int i = 0 ; i < s ; i++) {
            nx = nx + dx2[d];
            ny = ny + dy2[d];
            if (arr.size() > snail[ny][nx] - 1 - i){
                arr.remove(snail[ny][nx] - 1 - i);
            }
        }
        
        // 2. 겹치는 거 지우기
        remove();
        // 3. 구슬 변형
        transform();
    }
    static void transform() {
        int cnt = 0;
        boolean first = false;
        ArrayList<Integer> sub = new ArrayList<>();
        int num = 0; // 기준이 되는 수
        int max = (n * n) - 1; // 최고 용량
        for (int i = 0 ; i < arr.size() ; i++) {
            int d = arr.get(i);
            if (!first) {
                first = true;
                cnt++;
                num = d;
            } else {
                if (d == num) {
                    cnt++;
                } else {
                    if (sub.size() < max) {
                        sub.add(cnt);
                    }
                    if (sub.size() < max) {
                        sub.add(num);
                    }
                    
                    num = d;
                    cnt = 1;

                }
            }
            if (i == arr.size() - 1) {
                if (sub.size() < max) {
                    sub.add(cnt);
                }
                if (sub.size() < max) {
                    sub.add(num);
                }
            }
        }
        arr = sub;
    }
    static void remove() {
        Stack<Integer> stack = new Stack<>();
        int boom = 100;
        
        while (boom > 0) {
             boom = 0;
            int cnt = 0;
            stack = new Stack<>();
            for (int d : arr) {
                if (stack.isEmpty()) {
                    stack.push(d);
                    cnt ++;
                } else {
                    if (stack.peek() == d) {
                        stack.push(d);
                        cnt++;
                    } else {
                        if (cnt >= 4) {
                            boom++;
                            ans += stack.peek() * cnt; // 폭발구슬 점수 측정
                            for (int i = 0 ; i < cnt ; i++) {
                                stack.pop();
                            }
                            stack.push(d);
                            cnt = 1;
                        } else {
                            stack.push(d);
                            cnt = 1;
                        }
                    }
                }
                
            }
            if (boom == 0 && cnt >= 4) {
                for (int i = 0 ; i < cnt ; i++) {
                    ans += stack.peek() * cnt;
                    stack.pop();
                    cnt = 1;
                }
            }
        Integer[] m = new Integer[stack.size()];
        int midx = stack.size()-1;
        while (!stack.isEmpty()) {
            int d =stack.pop();
            m[midx--] = d; 
        }
        arr = new ArrayList<>(Arrays.asList(m));
        }
        
        
         
    }
    static void search () {
        Queue<Snail_Node> q= new LinkedList<>();
        q.offer(new Snail_Node(n/2, n/2));

        while (!q.isEmpty()) {
            Snail_Node d = q.poll();

            for (int i = 0 ; i < 4 ; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny <n && snail[ny][nx] == snail[d.y][d.x] + 1 && map[ny][nx] > 0) {
                    q.offer(new Snail_Node(nx, ny));
                    arr.add(map[ny][nx]);
                } 
            }
        }
    }
    static void make_snail() {
        int idx = (n * n) - 1;
        int dir = 1; // 동쪽으로
        boolean[][] isVisited = new boolean[n][n];
        isVisited[0][0] = true;
        snail[0][0] = idx--;
        Queue<Snail_Node> q = new LinkedList<>();
        q.offer(new Snail_Node(0, 0));

        while (!q.isEmpty()) {
            Snail_Node d = q.poll();
            int nx = d.x + dx[dir];
            int ny = d.y + dy[dir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || isVisited[ny][nx]) {
                q.offer(d);
                dir = (dir + 1) % 4; // 방향전환
                continue;
            }
            q.offer(new Snail_Node(nx, ny));
            isVisited[ny][nx] = true;
            snail[ny][nx] = idx--;
            if (idx == 0) break;
        }

    }
    static void print_matrix(int[][] arr) {
        for (int i = 0 ; i < n ; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
class Marble{
    int x;
    int y;
    int val;
    Marble (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
class Snail_Node{
    int x;
    int y;
    Snail_Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}