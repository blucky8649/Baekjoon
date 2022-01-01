import java.io.*;
import java.util.*;


public class Main23290 {
    static int n, m;
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] dy2 = {0, -1, 0, 1, 0}; // 상어 : 상좌하우 움직임
    static int[] dx2 = {0, 0, -1, 0, 1};

    static int[][] water = new int[4][4];
    static ArrayList<Fishes> fish = new ArrayList<>();
    static Queue<Fishes> eggs = new LinkedList<>();
    static int[][] dead_fishes = new int[4][4];
    static PriorityQueue<Dead> dead = new PriorityQueue<>();
    static boolean[][] check;
    static Duck shark;
    static PriorityQueue<Routes> routes = new PriorityQueue<>();
    static HashMap<String, ArrayList<Fishes>> map= new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken()); // 물고기 수
        m = Integer.parseInt(st.nextToken()); // year
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                map.put(i + " " +j, new ArrayList<>());
            }
        }
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            map.get(y + " " + x).add(new Fishes(x, y, dir));
            //fish.add(new Fishes(x, y, dir)); // 처음 물고기를 담는다..
            water[y][x]++;
        }

        st = new StringTokenizer(br.readLine(), " ");
        int shark_y = Integer.parseInt(st.nextToken()) - 1;
        int shark_x = Integer.parseInt(st.nextToken()) - 1;
        shark = new Duck(shark_x, shark_y);
        for (int i = 1 ; i <= m ; i++) {
            // 1. 상어가 복제 마법을 시전한다.
            copy();
            // 2. 물고기들이 이동한다
            move();
            // 3. 상어가 연속해서 3칸 이동한다.
            check = new boolean[4][4];
            routes = new PriorityQueue<>();
            search_route(0, "", 0, shark.x, shark.y);
            move_shark(i);
            // 4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
            remove_smells(i);
            // 5. 1에서 사용한 복제 마법이 완료된다.
            magic_done();
        }
        System.out.println(sum());
    }
    static void print_map() {
    	for (int i = 0 ; i < 4 ; i++) {
    		for (int j = 0 ; j < 4 ; j++) {
    			System.out.print(map.get(i + " " + j).size() + " ");
    		}
    		System.out.println();
    	}
    }
    static void print_maxtrix(int[][] arr) {
        for (int i = 0 ; i < arr.length ; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    static int sum() {
        int sum = 0;
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                sum += water[i][j];
            }
        }
        return sum;
    }
    static void magic_done() {
        while (!eggs.isEmpty()) {
            Fishes d = eggs.poll();
            water[d.y][d.x] ++;
            map.get(d.y + " " + d.x).add(new Fishes(d.x, d.y, d.dir));
        }
    }
    static void remove_smells(int year) {
        int y = year - 2;
        while (!dead.isEmpty() && dead.peek().year == y) {
            Dead d = dead.poll();
            if (d.year == y) {
                dead_fishes[d.y][d.x]--;
            }
        }
    }
    static void move_shark(int year) {
        String route = String.valueOf(routes.peek().route);
        // 상어가 이동하면서 물고기를 잡아먹는다.
        for (int i = 0 ; i < 3 ; i++) {
            int dir = Integer.parseInt(String.valueOf(route.charAt(i)));
            int nx = shark.x + dx2[dir];
            int ny = shark.y + dy2[dir];
            water[ny][nx] = 0;
            for (Fishes d : map.get(ny + " " + nx)) {
                dead_fishes[ny][nx] ++;
                dead.add(new Dead(nx, ny, year));
            }
            map.get(ny + " " + nx).clear();
            shark.x = nx;
            shark.y = ny;
        }
    }
    static void search_route(int k, String route, int ate, int x, int y) {
        if (k == 3) {
            int r = Integer.parseInt(route);
            if (routes.isEmpty()) {
                routes.offer(new Routes(r, ate));
            } else if (routes.peek().ate < ate) {
                routes = new PriorityQueue<>();
                routes.offer(new Routes(r, ate));
            } else if (routes.peek().ate == ate) {
                routes.offer(new Routes(r, ate));
            }
            return;
        }
        for (int i = 1 ; i <= 4; i++) {
            int nx = x + dx2[i];
            int ny = y + dy2[i];
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                int wt = water[ny][nx];
                if (check[ny][nx]) {
                    // 이미 지나친 적 있는 곳은
                    wt = 0;
                    search_route(k + 1, route + i, ate + wt, nx, ny);
                    continue;
                }
                check[ny][nx] = true;
                search_route(k + 1, route + i, ate + wt, nx, ny);
                check[ny][nx] = false;
            } 
        }
    }
    static void move() {
        HashMap<String, ArrayList<Fishes>> sub = new HashMap<>();
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                sub.put(i + " " + j, new ArrayList<>());
            }
        }
        for (int i = 0 ; i < 4;  i++) {
            for (int j = 0 ; j < 4 ; j++) {
                if (map.get(i + " " + j).isEmpty()) continue;
                for (int z = 0 ; z < map.get(i + " " + j).size() ; z++) {
                    Fishes d = map.get(i + " " + j).get(z);
                    int nx = j + dx[d.dir];
                    int ny = i + dy[d.dir];
                    int dir = d.dir;
                    // 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
                    if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || dead_fishes[ny][nx] > 0 || (ny == shark.y && nx == shark.x)) {
                        int a = 0;
                    	for ( ; a < 7 ; a++) {
                            dir = (dir + 7) % 8; // 방향을 반시계 방향으로 회전한다.
                            nx = j + dx[dir];
                            ny = i + dy[dir];
                            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || dead_fishes[ny][nx] > 0 || (ny == shark.y && nx == shark.x)) continue;
                            water[i][j] --;
                            sub.get(ny + " " + nx).add(new Fishes(nx, ny, dir));
                            water[ny][nx] ++;
                            break;
                        }
                    	if (a == 7) {
                    		sub.get(i + " " + j).add(new Fishes(j, i, d.dir));	
                    	}
                        continue;
                    }
                    water[i][j] --;
                    sub.get(ny + " " + nx).add(new Fishes(nx, ny, dir));
                    water[ny][nx] ++;
                }
            }
        }
        map = new HashMap<>(sub);
    }
    static void copy() {
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                if (!map.get(i + " " + j).isEmpty()) {
                    for (Fishes d :map.get(i + " " + j)) {
                        eggs.offer(new Fishes(d.x, d.y, d.dir));
                    }
                }
            }
        }
        
    }
}
class Routes implements Comparable<Routes>{
    int route;
    int ate;
    Routes (int route, int ate) {
        this.route = route;
        this.ate = ate;
    }
    @Override
    public int compareTo(Routes o) {
        if (this.ate == o.ate) {
            return this.route - o.route;
        }
        return o.ate - this.ate;
    }
}
class Duck {
    int x;
    int y;
    Duck(int x, int y) {
        this.x = x;
        this.y = y;
    } 
}
class Fishes {
    int x;
    int y;
    int dir;
    Fishes (int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class Dead implements Comparable<Dead>{
    int x;
    int y;
    int year;
    Dead (int x, int y, int year) {
        this.x =x;
        this.y =y;
        this.year = year;
    }
    @Override
    public int compareTo(Dead o) {
        return this.year - o.year;
    }
}