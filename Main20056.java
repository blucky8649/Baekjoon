import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main20056 {
    static int N, M, K;
    static ArrayList<Fireball>[][] map;
    static Queue<Fireball> q = new LinkedList<>();

    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.add(new Fireball(x, y, m, s, d));
        }
        System.out.println(solve());
    }

    static int solve() {
        for (int i = 0 ; i < K ; i++) {
            move_fireball();
            change_fireball();
        }
        int answer = 0;
        while(!q.isEmpty()) {
            Fireball d = q.poll();
            answer += d.m;
        }   
        
        
        return answer;
    }
    // 1. 파이어볼 이동
    static void move_fireball() {
        while (!q.isEmpty()) {
            Fireball d = q.poll();
            int nx = (d.x + N + dx[d.d] * (d.s % N)) % N;
            int ny = (d.y + N + dy[d.d] * (d.s % N)) % N;
            d.x = nx;
            d.y = ny;
            map[ny][nx].add(d);

        }
    }
    // 파이어볼이 합쳐지고, 나눠지고, 없어지는 과정 : 2, 3, 4번 과정
    static void change_fireball() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (map[i][j].size() > 0) {
                    if (map[i][j].size() > 1) {
                        ArrayList<Fireball> list = sum(map[i][j]);
                        for (int a = 0 ; a < list.size() ; a++) {
                            if (list.get(a).m == 0) continue;
                            else q.offer(list.get(a)); 
                        }
                    }
                    else {
                        q.offer(map[i][j].get(0));
                    }
                    map[i][j].clear();
                }
            }
        }
    }
    static ArrayList<Fireball> sum (ArrayList<Fireball> fireball) {
        ArrayList<Fireball> list = new ArrayList<>();
        int x = fireball.get(0).x;
        int y = fireball.get(0).y;
        int sumM = 0;
        int sumS = 0;
        int cnt = fireball.size();
        int odd = 0;
        int even = 0;
        for (int i = 0 ; i < fireball.size() ; i++) {
            Fireball d = fireball.get(i);
            sumM += d.m;
            sumS += d.s;
            if (d.d % 2 == 0) {
                even++;
            } else {
                odd ++;
            }
        }
        if (even == cnt || odd == cnt ) {
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 0));
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 2));
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 4));
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 6));
        } else {
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 1));
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 3));
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 5));
            list.add(new Fireball(x, y, sumM / 5, sumS / cnt, 7));
        }
        return list;
    }
}
class Fireball {
    int x;
    int y;
    int m;
    int s;
    int d;
    Fireball (int x, int y, int m, int s, int d) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}