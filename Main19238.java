import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main19238 {
    static int n, m,fuel, answer = -1, aX = 0, aY = 0;
    static int[][] map, map2;
    static boolean[][] isVisited;
    static Info[] infos;


    static int[] dy = {-1, 0, 0, 1}; // 진행 방향 (북 동 서 남) : 우선순위가 행이 낮은 순이 우선이 되어야함 .
    static int[] dx = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); // 맵 크기
        m = Integer.parseInt(st.nextToken()); // 최대 인원
        fuel = Integer.parseInt(st.nextToken()); // 초기 연료

        map = new int[n][n];
        

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++){
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a == 1 ? -1 : a;
            }   
        }

        st = new StringTokenizer(br.readLine(), " ");

        // 초기 차량 위치 및 연료 정보
        int carY = Integer.parseInt(st.nextToken()) -1;
        int carX = Integer.parseInt(st.nextToken()) -1;
        Car start = new Car(carX, carY, fuel);
        map2 = new int[n][n];

        // 도착지 탐색용 맵 복사
        for(int i = 0 ; i < map.length ; i++){
            map2[i] = map[i].clone();
        }
        infos = new Info[m + 1]; //승객정보 저장할 배열
        for(int i = 1 ; i <= m ; i++){
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1; // 출발 Y좌표
            int sx = Integer.parseInt(st.nextToken()) - 1; // 출발 X좌표
            int ey = Integer.parseInt(st.nextToken()) - 1; // 도착 Y좌표
            int ex = Integer.parseInt(st.nextToken()) - 1; // 도착 Y좌표
            
            map[sy][sx] = i; // 지도에 손님 출발지 표시 ex) 1, 2, 3... 
            map2[ey][ex] = i; // 지도에 손님 목적지 표시 ex) 1, 2, 3...
            infos[i] = new Info(sx, sy, ex, ey);
            
        }

        for(int i = 0 ; i < m ; i++){
            PriorityQueue<Passenger> q = getQueue(start.x, start.y, start.fuel);
            if(q.isEmpty()){
                System.out.println(-1); return;
            }

            Passenger d = q.poll();
            start = new Car(d.x, d.y, start.fuel - d.dst);
            
            map[d.y][d.x] = 0; // 손님을 태웠으니 빈칸으로 변경
            int move = arrive(start.x, start.y, start.fuel, d.num);
            if(move == -1){
                
                System.out.println(-1); return;
            }
            
            start = new Car(aX, aY, move);

        }
        System.out.println(start.fuel);

    }

    static PriorityQueue<Passenger> getQueue(int x, int y, int fuel2){
        isVisited = new boolean[n][n];
        Queue<Car> q = new LinkedList<>();
        q.offer(new Car(x, y, fuel2));
        isVisited[y][x] = true;
        PriorityQueue<Passenger> pq = new PriorityQueue<>();

        while(!q.isEmpty()){
            Car d = q.poll();

            //연료가 바닥나면 도착지까지 갈 수 없음
            if(d.fuel == 0){
                break;
            }
            // 손님을 마주쳤으면?
            if(map[d.y][d.x] > 0){
                //태워야지
                int target = map[d.y][d.x];
                pq.offer(new Passenger(target, d.x, d.y, fuel2 - d.fuel));
                
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx >= 0 && nx < n && ny >= 0 && ny < n && !isVisited[ny][nx] && map[ny][nx] != -1){
                    isVisited[ny][nx] = true;
                    q.offer(new Car(nx, ny, d.fuel - 1));
                }
            }
        }

        return pq;


        
    }



    static int arrive(int x, int y, int fuel2, int target){
        Queue<Car> q = new LinkedList<>();
        isVisited = new boolean[n][n];
        q.offer(new Car(x, y, fuel2));
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            Car d = q.poll();

            
            // 도착지에 도착했다면 연료가 0이라도 상관없음
            if( d.x == infos[target].ex && d.y == infos[target].ey && d.fuel >= 0){
                int bonus = d.fuel + ((fuel2 - d.fuel) * 2); // 연료 두배 충전
                aX = d.x;
                aY = d.y;
                map2[d.y][d.x] = 0;
                return bonus;
                
            }

            for(int i = 0 ; i < 4 ; i++){
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];
                
                if(nx >= 0 && nx < n && ny >= 0 && ny < n && !isVisited[ny][nx] && map2[ny][nx] != -1){
                    isVisited[ny][nx] = true;
                    q.offer(new Car(nx, ny, d.fuel - 1));
                }
            }
        }
        return-1;
    }
}
// 택시
class Car{
    int x;
    int y;
    int fuel;
    Car(int x, int y, int fuel){
        this.x =x;
        this.y =y;
        this.fuel = fuel;
    }
}
// 승객 
class Passenger implements Comparable<Passenger>{
    int num;
    int x;
    int y;
    int dst;
    Passenger(int num, int x, int y, int dst){
        this.num = num;
        this.x =x;
        this.y =y;
        this.dst = dst;
    }
    @Override
    public int compareTo(Passenger o){
        if(this.dst == o.dst){
            if(this.y == o.y){
                return this.x - o.x;
            }
            return this.y - o.y;
        }
        return this.dst - o.dst;
    }    
    
}


 class Info {
    int sx,sy,ex,ey; //출발지, 도착지 좌표

    public Info(int sx, int sy, int ex, int ey) {
        super();
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
    }
    
}