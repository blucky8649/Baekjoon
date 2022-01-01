import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main23289 {
    static int R, C, K;
    static int[][] map;
    static boolean[][] isVisited;
    static HashSet<String> wall = new HashSet<>();
    static Queue<Heater> heat = new LinkedList<>();
    static ArrayList<int[]> invest = new ArrayList<>(); // 조사해야할 칸

    static int[] adj_y = {0, 1};
    static int[] adj_x = {1, 0};
    static int[][] dy = {{0, 1, -1}, {0, 1, -1}, {-1, -1, -1}, {1, 1, 1}}; // 오 왼 위 아래
    static int[][] dx = {{1, 1, 1}, {-1, -1, -1}, {0, -1, 1}, {0, -1, 1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        isVisited = new boolean[R][C];
        
        for (int i = 0 ; i < R ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < C ; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) continue;
                if (num == 5) invest.add(new int[] {j, i});
                else heat.offer(new Heater(j, i, num -1, 5));

                map[i][j] = 0;
            }
        }
        int W = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < W ; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            if (t == 1) {
                wall.add(r+" "+c+":"+ r +" "+(c+1));
            } else {
                wall.add(r+" "+c+":"+(r-1)+" "+c);
            }
        }
        int choco = 0;
        while (choco <= 100) {
            // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
            for (Heater d : heat) {
                spread_air(d.x, d.y, d.dir);
            }
            //System.out.println("spread_air() 완료");
            // 2. 온도가 조절됨
            adj_temp();
            //System.out.println("adj_temp() 완료");
            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            reduce_air();
            //System.out.println("reduce_air 완료");
            // 4. 초콜릿을 먹는다.
            choco++;
            
            if (invest_air()) break;
        }
        System.out.println(choco);
         
        
    }
    static void reduce_air() {
        for (int i = 0 ; i < C ; i++) {
            map[0][i] = map[0][i] > 0 ? map[0][i] - 1 : 0;
            map[R - 1][i] = map[R - 1][i] > 0 ? map[R - 1][i] - 1 : 0;
        }

        for (int i = 1 ; i < R - 1 ; i++) {
            map[i][0] = map[i][0] > 0 ? map[i][0] - 1 : 0;
            map[i][C - 1] = map[i][C - 1] > 0 ? map[i][C - 1] - 1 : 0;
        }
    }
    static boolean invest_air() {
        for (int i = 0 ; i < invest.size() ; i++) {
            int x = invest.get(i)[0];
            int y = invest.get(i)[1];

            if (map[y][x] < K) return false;
        }
        return true;
    }
    static void print_maxrix() {
        for (int i = 0 ; i < R ; i++) {
            System.out.println( " 냥! " + Arrays.toString(map[i]));
         }
         System.out.println("@@@@@@@@@@@@@@@");
    }
    static void adj_temp() {
        int[][] clone = new int[R][C];
        for (int i = 0 ; i < R ; i++) {
            clone[i] = map[i].clone();
        }
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                for (int a = 0 ; a < 2 ; a++) {
                    int nx = j + adj_x[a];
                    int ny = i + adj_y[a];
                    if (nx >= 0 && ny >= 0 && nx < C && ny < R && check_wall(j, i, a == 0 ? 0 : 3, 0)) {
                        int diff = map[i][j] - map[ny][nx];
                        if (diff > 0) {
                            clone[i][j] -= (Math.abs(diff) / 4);
                            clone[ny][nx] += (Math.abs(diff) / 4);
                            if (clone[i][j] < 0) {
                                clone[i][j] = 0;
                            }
                        }

                        else if (diff < 0) {
                            clone[i][j] += (Math.abs(diff) / 4);
                            clone[ny][nx] -= (Math.abs(diff) / 4);
                            if (clone[ny][nx] < 0) {
                                clone[ny][nx] = 0;
                            }
                        }

                        
                        
                    }
                }
            }
        }
        for (int i = 0 ; i < R ; i++) {
            map[i] = clone[i].clone();
         }
    }
    static void spread_air(int x, int y, int dir) {
        Queue<Heater> q = new LinkedList<>();
        int X = x + dx[dir][0];
        int Y = y + dy[dir][0];
        isVisited = new boolean[R][C];
        q.offer(new Heater(X, Y, dir, 5));
        isVisited[Y][X] = true;
        map[Y][X] += 5;

        while (!q.isEmpty()) {
            Heater d = q.poll();
            if (d.power == 0) return;
            for (int i = 0 ; i < 3 ; i++) {
                int nx = d.x + dx[d.dir][i];
                int ny = d.y + dy[d.dir][i];

                if (nx >= 0 && nx < C && ny >= 0 && ny < R && !isVisited[ny][nx] && check_wall(d.x, d.y, dir, i)) {
                    map[ny][nx] += (d.power -1);
                    isVisited[ny][nx] = true;
                    q.offer(new Heater(nx, ny, d.dir, d.power - 1));
                }
            }
        }
    }
    static boolean check_wall(int x, int y, int dir, int dir2) {
        switch(dir) {
            // 오 왼 위 아래
            case 0 : 
                if (dir2 == 0) {
                    // 직진 (만약 x, y 와 x+1, y 사이에 벽이 있으면)
                    if (wall.contains(y + " " + x + ":" + y + " " + (x + 1))) {
                        return false;
                    }
                }

                if (dir2 == 1) {
                    // 오른쪽 밑
                    if (wall.contains((y + 1) + " " + x + ":" + y + " " + x) || 
                    wall.contains( (y + 1) + " " + x + ":" +  (y + 1) + " " + (x + 1))) {
                        return false;
                    }
                }

                if (dir2 == 2) {
                    // 오른쪽 위
                    if (wall.contains(y + " " + x + ":" + (y - 1) + " " + x) || 
                    wall.contains( (y - 1) + " " + x + ":" +  (y - 1) + " " + (x + 1))) {
                        return false;
                    }
                }
                break;
            case 1 : 
                //왼
                if (dir2 == 0) {
                    //왼 직
                    if (wall.contains(y + " " + (x -1) + ":" + y + " " + x)) {
                        return false;
                    }
                }
                if (dir2 == 1) {
                    // 왼 밑
                    if (wall.contains((y + 1) + " " + x + ":" + y + " " + x) ||
                    wall.contains( (y + 1) + " " + (x - 1) + ":" +  (y + 1) + " " + x)) {
                        return false;
                    }
                }
                if (dir2 == 2) {
                    // 왼 위
                    if (wall.contains(y + " " + x + ":" + (y - 1) + " " + x) ||
                    wall.contains( (y - 1) + " " + (x - 1) + ":" +  (y - 1) + " " + x)) {
                        return false;
                    }
                }
                break;
            case 2 : 
                // 위
                if (dir2 == 0) {
                    // 위 직
                    if (wall.contains(y + " " + x + ":" + (y-1) + " " + x)) return false;
                }
                if (dir2 == 1) {
                    // 위 좌
                    if (wall.contains(y + " " + (x - 1) + ":" + y + " " + x) ||
                    wall.contains(y + " " + (x - 1) + ":" + (y - 1) + " " + (x - 1))) return false;
                }
                if (dir2 == 2) {
                    // 위 오
                    if (wall.contains(y + " " + x + ":" + y + " " + (x + 1)) ||
                    wall.contains(y + " " + (x + 1) + ":" + (y - 1) + " " + (x+1))) return false;
                }
                break;
            case 3 : 
                // 아래
                if (dir2 == 0) {
                    // 아래 직
                    if (wall.contains((y+1) + " " + x + ":" + y + " " + x)) return false;
                }
                if (dir2 == 1) {
                    //아래 좌
                    if (wall.contains(y + " " + (x - 1) + ":" + y + " " + x) || 
                    wall.contains((y + 1) + " " + (x - 1) + ":" + y + " " + (x - 1))) return false;
                }
                if (dir2 == 2) {
                    // 아래 우
                    if (wall.contains(y + " " + x + ":" + y + " " + (x + 1)) || 
                    wall.contains((y + 1) + " " + (x + 1) + ":" + y + " " + (x + 1))) return false;
                }
                break;
        }
        return true;
    }
}
class Heater {
    int x;
    int y;
    int dir;
    int power;
    Heater(int x, int y, int dir, int power) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.power = power;
    }
}