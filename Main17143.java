import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main17143 {
    static Queue<Shark1> q = new LinkedList<>();
    static int R, C, M, me=0;
    static TreeMap<Integer, Shark1> map = new TreeMap<>();
    static TreeMap<Integer, Shark1> map2;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int start = 1; // 주인공 시작 Position.

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map.put(x * 1000 + y, new Shark1(x, y, s, d, z)); // Key 값을 가까운 거리 순으로 나열 했음!
        }
        
        int answer = 0;
        for (int i = start ; i <= C ; i ++) { // 낚시꾼이 C를 벗어나면 낚시 종료.
            map2 = new TreeMap<>();
            
            // 제일 가까운 물고기 잡기
            for (int num : map.keySet()) {
                
                // 제일 가까운 물고기가 걸렸으면
                if ((num  / 1000) == i) { 
                    answer += map.get(num).z; // 크기 합 계산
                    map.remove(num); // 해당 물고기는 잡힘
                    break;
                }
            }
            // 물고기 이동
            for (int num : map.keySet()) {
                Shark1 d = map.get(num);
                Shark1 m = move(d.y, d.x, d.s, d.d, d.z);
                
                int key = m.x * 1000 + m.y;
                
                if (!map2.containsKey(key)) {
                    map2.put(key, new Shark1(m.x, m.y, d.s, m.d, m.z));
                } else {
                    if (m.z > map2.get(key).z) {
                        map2.put(key, new Shark1(m.x, m.y, d.s, m.d, m.z));
                    }
                }
            }
            map = new TreeMap<>(map2); // 맵 옮김

            
        }
        System.out.println(answer);
        

    }
    static Shark1 move(int y, int x, int s, int d, int z) {
        Shark1 m = new Shark1(x, y, s, d, z);
        int spd = m.s;
        switch(m.d) {
            // 1 : 위
            // 2 : 아래
            // 3 : 오른쪽
            // 4 : 왼쪽
            case 1 :
                if (spd > 0) {
                    int turn_point = m.y - 1; // 턴 포인트 이 지점에서 spd 가 남아있다면 방향을 바꿔야됨.
                    if (spd <= turn_point) { 
                        // 턴 포인트 까지 갈 스피드가 부족하다면 방향 그대로, 현재 위치에서 y - spd 값 해주면 됨
                        m = new Shark1(m.x, m.y - spd, m.s, m.d, m.z);
                        return m;
                    } else {
                        // 턴 포인트 찍고 턴 할 여유까지 되면 턴 하면 됨
                        m = move(y - turn_point, x, s - turn_point, 2, z);
                    }
                } 
            break; 
            case 2 : 
                if (spd > 0) {
                    int turn_point = R - m.y; // 턴 포인트 이 지점에서 spd 가 남아있다면 방향을 바꿔야됨.
                    if (spd <= turn_point) { 
                        // 턴 포인트 까지 갈 스피드가 부족하다면 방향 그대로, 현재 위치에서 y - spd 값 해주면 됨
                        m = new Shark1(m.x, m.y + spd, m.s, m.d, m.z);
                        return m;
                    } else {
                        // 턴 포인트 찍고 턴 할 여유까지 되면 턴 하면 됨
                        m = move(y + turn_point, x, s - turn_point, 1, z);
                    }
                } 
                break; 
            case 3 :
                if (spd > 0) {
                    int turn_point = C - m.x; // 턴 포인트 이 지점에서 spd 가 남아있다면 방향을 바꿔야됨.
                    if (spd <= turn_point) { 
                        // 턴 포인트 까지 갈 스피드가 부족하다면 방향 그대로, 현재 위치에서 y - spd 값 해주면 됨
                        m = new Shark1(m.x + spd, m.y, m.s, m.d, m.z);
                        return m;
                    } else {
                        // 턴 포인트 찍고 턴 할 여유까지 되면 턴 하면 됨
                        m = move(y, x + turn_point, s - turn_point, 4, z);
                    }
                } 
                break;
            case 4 : 
                if (spd > 0) {
                    int turn_point = m.x - 1; // 턴 포인트 이 지점에서 spd 가 남아있다면 방향을 바꿔야됨.
                    if (spd <= turn_point) { 
                        // 턴 포인트 까지 갈 스피드가 부족하다면 방향 그대로, 현재 위치에서 y - spd 값 해주면 됨
                        m = new Shark1(m.x - spd, m.y, m.s, m.d, m.z);
                        return m;
                    } else {
                        // 턴 포인트 찍고 턴 할 여유까지 되면 턴 하면 됨
                        m = move(y, x - turn_point, s - turn_point, 3, z);
                    }
                } 
                break;
        }
        return m;
    }
}
class Shark1{
    int x;
    int y;
    int s;
    int d;
    int z;
    Shark1(int x, int y, int s, int d, int z){
        this.x =x;
        this.y =y;
        this.s =s;
        this.d =d;
        this.z =z;
    }
}